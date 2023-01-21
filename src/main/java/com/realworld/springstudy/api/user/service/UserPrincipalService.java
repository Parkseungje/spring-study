package com.realworld.springstudy.api.user.service;

import com.realworld.springstudy.api.user.entity.User;
import com.realworld.springstudy.api.user.entity.UserPrincipal;
import com.realworld.springstudy.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserPrincipalService {

    private final UserRepository userRepository;


    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }

    public Optional<UserDetails> loadUserByUsernameAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return Optional.of(userPrincipal);
    }
}
