package com.insuk.bookapi.entertainment.Controller;

import com.insuk.bookapi.entertainment.domain.HotKeyword;
import com.insuk.bookapi.entertainment.service.HotKeywordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HotKeywordControllerTest {
    private final static String HOT_KEYWORD = "개발";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotKeywordService hotKeywordService;

    @WithMockUser("testUser")
    @Test
    public void 인기키워드_가져오기_테스트() throws Exception{
        given(hotKeywordService.getHotkeywordList())
                .willReturn(Arrays.asList(new HotKeyword(HOT_KEYWORD, new AtomicInteger(10))));

        mockMvc.perform(get("/hot/keyword/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("[0].keyword").value(HOT_KEYWORD))
                .andExpect(jsonPath("[0].count").value(10))
                .andDo(print());

        then(hotKeywordService).should(times(1)).getHotkeywordList();
    }
}