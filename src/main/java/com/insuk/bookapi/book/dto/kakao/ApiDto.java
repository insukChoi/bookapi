package com.insuk.bookapi.book.dto.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.insuk.bookapi.book.dto.ApiDtoParents;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "documents",
        "meta"
})
@Getter
@Setter
public class ApiDto implements ApiDtoParents {
    final static public ApiDto EMPTY = new ApiDto();

    @JsonProperty("documents")
    private List<BookDto> bookDtoList = new ArrayList<>();

    @JsonProperty("meta")
    private MetaDto metaDto;
}
