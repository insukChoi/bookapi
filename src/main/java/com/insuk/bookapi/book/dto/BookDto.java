package com.insuk.bookapi.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "documents"
})
@Setter
@Getter
public class BookDto {
    @JsonProperty("title")
    private String title;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("authors")
    private ArrayList<String> authors = new ArrayList<>();

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private Date datetime;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("sale_price")
    private Long salePrice;
}
