package com.insuk.bookapi.book.dto.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class MetaDto {
    @JsonProperty("is_end")
    private boolean isEnd;

    @JsonProperty("pageable_count")
    private Long pageableCount;

    @JsonProperty("total_count")
    private Long totalConut;
}
