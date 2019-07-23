package com.insuk.bookapi.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "documents",
        "meta"
})
@Getter
@Setter
public class ApiDto {
    final static public ApiDto EMPTY = new ApiDto();

    @JsonProperty("documents")
    private List<BookDto> bookDtoList = new ArrayList<>();

    @JsonProperty("meta")
    private MetaDto metaDto;
}
