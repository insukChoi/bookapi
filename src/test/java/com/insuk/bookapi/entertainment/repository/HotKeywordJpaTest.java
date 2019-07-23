package com.insuk.bookapi.entertainment.repository;

import com.insuk.bookapi.entertainment.domain.HotKeyword;
import com.insuk.bookapi.entertainment.repository.HotKeywordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HotKeywordJpaTest {
    private final static String HOT_ISSUE_KEYWORD = "여름";

    @Autowired
    private HotKeywordRepository hotKeywordRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void 인기키워드_저장하기_테스트() {
        HotKeyword hotKeyword = new HotKeyword(HOT_ISSUE_KEYWORD, new AtomicInteger(10));
        testEntityManager.persist(hotKeyword);

        Optional<HotKeyword> hotKeywordOptional = hotKeywordRepository.findByKeyword(HOT_ISSUE_KEYWORD);

        assertThat(hotKeywordOptional.get().getKeyword(), is(HOT_ISSUE_KEYWORD));
    }
}