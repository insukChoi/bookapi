package com.insuk.bookapi.login.security;

import com.insuk.bookapi.login.domain.User;
import com.insuk.bookapi.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service("userDetailsService")
@Transactional
    public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String userId) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUserId(userId);
        if (!user.isPresent()){
            return new org.springframework.security.core.userdetails.User("","",true,true,true,true, Collections.emptyList());
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUserId(), user.get().getPassword(), true, true, true, true, Collections.emptyList());
    }
}
