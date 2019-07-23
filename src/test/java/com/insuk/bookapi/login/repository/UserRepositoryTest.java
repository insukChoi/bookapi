package com.insuk.bookapi.login.repository;

import com.insuk.bookapi.login.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void 사용자_등록_테스트(){
        User user = new User();
        user.setUserId("insuk");
        user.setPassword("123");
        user.setCreatedDate(LocalDateTime.now());

        testEntityManager.persist(user);
        Optional<User> savedUser = userRepository.findByUserId(user.getUserId());

        assertThat(savedUser.get(), is(user));
        assertThat(savedUser.get().getUserId(), is(user.getUserId()));
        assertThat(savedUser.get().getPassword(), is(user.getPassword()));
    }
}
