package com.insuk.bookapi.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Setter
public class SearchBook {
    @Valid
    @NotEmpty(message = "keyword가 누락되었습니다.")
    private String keyword;

    @Getter
    private int page = 1;

    @Getter
    private int size = 10;

    public String getSearchKeyword(){
        return this.keyword;
    }
}
