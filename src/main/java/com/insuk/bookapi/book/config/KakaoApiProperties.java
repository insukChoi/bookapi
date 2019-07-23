package com.insuk.bookapi.book.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "api.kakao.book")
@Validated
public class KakaoApiProperties {
    @Getter
    @Setter
    @NotNull
    private String uri;

    @Getter
    @Setter
    @NotNull
    private String key;
}
