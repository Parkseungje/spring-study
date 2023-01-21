package com.realworld.springstudy.api.user.service;

import com.realworld.springstudy.api.user.dto.UserRequest;
import com.realworld.springstudy.api.user.dto.UserUpdateRequest;
import com.realworld.springstudy.api.user.entity.User;
import com.realworld.springstudy.api.user.entity.User.UserBuilder;
import com.realworld.springstudy.api.user.entity.UserPrincipal;
import com.realworld.springstudy.api.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUsers(UserRequest userRequest){
        UserBuilder builder = User.builder();

        builder.name(userRequest.getName());
        builder.email(userRequest.getEmail());
        builder.password(userRequest.getPassword());
        builder.bio(userRequest.getBio());
        builder.image(userRequest.getImage());

        User build = builder.build();

        userRepository.save(build);
    }

    @Transactional // 업데이트할때는 entity의 모든값을 가져와야한다.
    public void updateUserById(Long id, UserUpdateRequest body) {

        User user = userRepository.findById(id).get();

        UserBuilder builder = user.builder();

        builder.id(user.getId());
        builder.name(user.getName());
        builder.password(user.getPassword());

        if (body.getImage() != null) {
            builder.image(body.getImage());
        } else {
            builder.image(user.getImage());
        }

        if (body.getBio() != null) {
            builder.bio(body.getBio());
        } else {
            builder.bio(user.getBio());
        }

        if (body.getEmail() != null) {
            builder.email(body.getEmail());
        } else {
            builder.email(user.getEmail());
        }

        userRepository.save(builder.build());

    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return userRepository.findByEmail(principal.getUsername());
    }
}

