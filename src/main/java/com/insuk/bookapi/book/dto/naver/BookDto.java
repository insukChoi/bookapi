package com.insuk.bookapi.book.dto.naver;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BookDto {
    private String title;

    private String image;

    private String description;

    private String isbn;

    private String author;

    private String publisher;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'", timezone = "UTC")
    private Date pubdate;

    private Long price;

    private Long discount;
}