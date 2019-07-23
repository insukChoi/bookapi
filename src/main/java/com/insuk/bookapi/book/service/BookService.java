package com.insuk.bookapi.book.service;

import com.insuk.bookapi.book.config.KakaoApiProperties;
import com.insuk.bookapi.book.config.RestTemplateConfig;
import com.insuk.bookapi.book.domain.SearchBook;
import com.insuk.bookapi.book.dto.ApiDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "getSearchBookApiFallback")
    public ApiDto getSearchBookApi(SearchBook searchBook){
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
        responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, ApiDto.class);

        ApiDto apiDto = responseEntity.getBody();

        return apiDto;
    }

    @SuppressWarnings("unused")
    public ApiDto getSearchBookApiFallback(SearchBook searchBook){
        // TODO: 네이버 도서 API
        System.out.println("Fallback Method Execute!!!");
        return ApiDto.EMPTY;
    }

}

