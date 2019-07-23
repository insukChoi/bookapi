package com.insuk.bookapi.book.repositroty;

import com.insuk.bookapi.book.domain.MyHistory;
import com.insuk.bookapi.book.repository.MyHistoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MyHistoryJpaTest {
    private final static String MY_SEARCH_KEYWORD = "여름";

    @Autowired
    private MyHistoryRepository myHistoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void 내검색_히스토리_저장하기_테스트(){
        MyHistory myHistory = MyHistory.builder().userId("insuk")
                .keyword(MY_SEARCH_KEYWORD).build();
        testEntityManager.persist(myHistory);

        assertThat(myHistoryRepository.getOne(myHistory.getIdx()), is(myHistory));
    }

    @Test
    public void 내검색_히스토리_저장하고_조회_테스트(){
        MyHistory myHistory1 = MyHistory.builder().userId("insuk")
                .keyword(MY_SEARCH_KEYWORD).build();
        testEntityManager.persist(myHistory1);

        MyHistory myHistory2 = MyHistory.builder().userId("insuk")
                .keyword(MY_SEARCH_KEYWORD).build();
        testEntityManager.persist(myHistory2);

        MyHistory myHistory3 = MyHistory.builder().userId("insuk")
                .keyword(MY_SEARCH_KEYWORD).build();
        testEntityManager.persist(myHistory3);

        List<MyHistory> myHistoryList = myHistoryRepository.findAll();
        assertThat(myHistoryList, hasSize(3));
        assertThat(myHistoryList, contains(myHistory1, myHistory2, myHistory3));
    }
}
