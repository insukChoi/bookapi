package com.insuk.bookapi.book.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.naver.book")
@Getter
@Setter
public class NaverApiProperties {

    private String uri;

    private String clientId;

    private String clientSecret;
}
