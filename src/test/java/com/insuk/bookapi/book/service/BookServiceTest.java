package com.insuk.bookapi.book.service;

import com.insuk.bookapi.book.dto.kakao.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@JsonTest
public class BookServiceTest {

    @Autowired
    private JacksonTester<BookDto> json;

    @Test
    public void 도서검색API_json_To_Dto() throws IOException {
        String bookApicontent = "{\"title\":\"모략의 즐거움\",\"thumbnail\":\"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F518915%3Ftimestamp%3D20190123153425\",\"contents\":\"\",\"isbn\":\"8934925094 9788934925095\",\"authors\":[\"마수취안\"],\"publisher\":\"김영사\",\"datetime\":\"2007-04-22T15:00:00.000Z\",\"price\":14000,\"sale_price\":-1}";

        BookDto bookDto = new BookDto();
        bookDto.setTitle("모략의 즐거움");
        bookDto.setThumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F518915%3Ftimestamp%3D20190123153425");

        assertThat(this.json.parseObject(bookApicontent).getTitle())
                .isEqualTo(bookDto.getTitle());
        assertThat(this.json.parseObject(bookApicontent).getThumbnail()).isEqualTo(bookDto.getThumbnail());

    }

    /*@Test
    public void 도서검색API_json_To_Dto() throws IOException {ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("searchBook.json").getFile());

        JsonReader reader = new JsonReader(new FileReader(file));
        Gson gson = new Gson();
        ApiDto apiDto = gson.fromJson(reader, ApiDto.class);
        assertNotNull(apiDto);
    }*/
}