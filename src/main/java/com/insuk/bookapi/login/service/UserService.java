package com.insuk.bookapi.login.service;

import com.insuk.bookapi.login.domain.User;
import com.insuk.bookapi.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 사용자 저장
    public User SaveUser(User user){
        if (this.duplicateUserCheck(user.getUserId())) {
            return User.EMPTY;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreatedDate(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    // 사용자 아이디로 사용자 조회
    public Optional<User> getUser(String userId){
        Optional<User> user = userRepository.findByUserId(userId);
        return user;
    }

    // 사용자 중복 체크
    private boolean duplicateUserCheck(String userId){
        Optional<User> savedUser = this.getUser(userId);
        if (savedUser.isPresent()) return true;
        else return false;
    }

}
