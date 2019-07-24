package com.insuk.bookapi.book.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insuk.bookapi.book.config.KakaoApiProperties;
import com.insuk.bookapi.book.config.NaverApiProperties;
import com.insuk.bookapi.book.config.RestTemplateConfig;
import com.insuk.bookapi.book.domain.SearchBook;
import com.insuk.bookapi.book.dto.ApiDtoParents;
import com.insuk.bookapi.book.dto.EmptyStringToLongTypeAdapter;
import com.insuk.bookapi.book.dto.kakao.ApiDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class BookService {
    @Autowired
    RestTemplateConfig restTemplateConfig;

    @Autowired
    KakaoApiProperties kakaoApiProperties;

    @Autowired
    NaverApiProperties naverApiProperties;

    @HystrixCommand(fallbackMethod = "getSearchBookApiFallback")
    @HystrixProperty(name = "feign.hystrix.enabled", value = "false")
    public ApiDtoParents getSearchBookApi(SearchBook searchBook){
        /* 카카오 도서 API */
        ResponseEntity<ApiDto> responseEntity;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK "+kakaoApiProperties.getKey());
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset=UTF-8");

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = restTemplateConfig.getCustomRestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", searchBook.getSearchKeyword());
        params.add("sort", "accuracy");
        params.add("page", String.valueOf(searchBook.getPage()));
        params.add("size", String.valueOf(searchBook.getSize()));

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(kakaoApiProperties.getUri())
                .queryParams(params).build(false);
        // 카카오 책 검색 API
        responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, ApiDto.class);

        ApiDtoParents kakaoApiDto = responseEntity.getBody();

        return kakaoApiDto;
    }

    @SuppressWarnings("unused")
    public ApiDtoParents getSearchBookApiFallback(SearchBook searchBook){
        /* Fallback : 네이버 도서 API */
        log.info("Fallback Method Execute!!!");
        ResponseEntity<String> responseEntity;

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", naverApiProperties.getClientId());
        headers.add("X-Naver-Client-Secret", naverApiProperties.getClientSecret());
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = restTemplateConfig.getCustomRestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", searchBook.getSearchKeyword());
        params.add("start", String.valueOf((searchBook.getPage() -1) * searchBook.getSize() + 1));
        params.add("display", String.valueOf(searchBook.getSize()));

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(naverApiProperties.getUri())
                .queryParams(params).build(false);
        // 네이버 책 검색 API
        responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity,  String.class);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Long.class, new EmptyStringToLongTypeAdapter())
                .create();
        com.insuk.bookapi.book.dto.naver.ApiDto naverApiDto = gson.fromJson(responseEntity.getBody(), com.insuk.bookapi.book.dto.naver.ApiDto.class);

        // naver API DTO -> kakao API DTO 로 변환
        ApiDtoParents apiDto = naverApiDto.convertKakaApiDto();

        return apiDto;
    }
}

