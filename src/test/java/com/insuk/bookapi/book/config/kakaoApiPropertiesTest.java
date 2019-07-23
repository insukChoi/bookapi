package com.insuk.bookapi.book.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class kakaoApiPropertiesTest {
    @Autowired
    private KakaoApiProperties kakaoApiProperties;

    @Test
    public void 카카오_API_정보_확인(){
        assertThat(kakaoApiProperties.getUri(), is("https://dapi.kakao.com/v3/search/book?target=title"));
        assertThat(kakaoApiProperties.getKey(), is("ee496cdbb5fd7909d0b309ec6be6abc2"));
    }
}
