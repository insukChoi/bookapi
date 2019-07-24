package com.insuk.bookapi.book.dto.naver;

import com.insuk.bookapi.book.dto.ApiDtoParents;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Setter
@Getter
public class ApiDto implements ApiDtoParents {
    final static public ApiDto EMPTY = new ApiDto();

    private Long total;

    private List<BookDto> items = new ArrayList<>();

    /**
     * 카카오 ApiDto 로 변환 함수
     * @return kakao.ApiDto
     */
    public com.insuk.bookapi.book.dto.kakao.ApiDto convertKakaApiDto(){
        List<com.insuk.bookapi.book.dto.kakao.BookDto> kakaoBookDtoList = new ArrayList<>();
        for (BookDto bookDto : this.items){
            com.insuk.bookapi.book.dto.kakao.BookDto kakaoBookDto = new com.insuk.bookapi.book.dto.kakao.BookDto();
            kakaoBookDto.setTitle(bookDto.getTitle());
            kakaoBookDto.setThumbnail(bookDto.getImage());
            kakaoBookDto.setContents(bookDto.getDescription());
            kakaoBookDto.setAuthors(Arrays.asList(bookDto.getAuthor()));
            kakaoBookDto.setDatetime(bookDto.getPubdate());
            kakaoBookDto.setIsbn(bookDto.getIsbn());
            kakaoBookDto.setPublisher(bookDto.getPublisher());
            kakaoBookDto.setPrice(bookDto.getPrice());
            kakaoBookDto.setSalePrice(bookDto.getDiscount());

            kakaoBookDtoList.add(kakaoBookDto);
        }

        com.insuk.bookapi.book.dto.kakao.MetaDto kakaoMetaDto = new com.insuk.bookapi.book.dto.kakao.MetaDto();
        kakaoMetaDto.setPageableCount(this.total);

        com.insuk.bookapi.book.dto.kakao.ApiDto kakaoApiDto = new com.insuk.bookapi.book.dto.kakao.ApiDto();
        kakaoApiDto.setBookDtoList(kakaoBookDtoList);
        kakaoApiDto.setMetaDto(kakaoMetaDto);

        return kakaoApiDto;
    }
}
