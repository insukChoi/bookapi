package com.insuk.bookapi.login.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user_1;
    private User user_2;

    @Before
    public void init() throws RuntimeException{
        user_1 = new User("insuk", "123");
        user_2 = new User("leo", "123");
    }

    @Test
    public void 패스워드_인코딩_비교_테스트(){
        user_1.setPassword("123");
        user_2.setPassword(bCryptPasswordEncoder.encode("123"));
        assertThat(bCryptPasswordEncoder.matches(user_1.getPassword(), user_2.getPassword()), is(true));
    }
}
