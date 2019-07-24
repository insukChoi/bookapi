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
public class NaverApiPropertiesTest {
    @Autowired
    private NaverApiProperties naverApiProperties;

    @Test
    public void 카카오_API_정보_확인(){
        assertThat(naverApiProperties.getUri(), is("https://openapi.naver.com/v1/search/book.json"));;
        assertThat(naverApiProperties.getClientId(), is("vskdM_IkXrgtz57or75U"));
        assertThat(naverApiProperties.getClientSecret(), is("V8SuqwGUJE"));
    }
}