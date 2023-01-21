package com.realworld.springstudy.api.user.service;

import com.realworld.springstudy.api.user.dto.Profile;
import com.realworld.springstudy.api.user.dto.UserRequest;
import com.realworld.springstudy.api.user.dto.UserUpdateRequest;
import com.realworld.springstudy.api.user.entity.Follow;
import com.realworld.springstudy.api.user.entity.Follow.FollowBuilder;
import com.realworld.springstudy.api.user.entity.User;
import com.realworld.springstudy.api.user.entity.User.UserBuilder;
import com.realworld.springstudy.api.user.entity.UserPrincipal;
import com.realworld.springstudy.api.user.repository.FollowRepository;
import com.realworld.springstudy.api.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public UserService(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
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

    public Profile getProfile(String username){
        User user = userRepository.findByName(username);

        Profile profile = new Profile();

        profile.setBio(user.getBio());
        profile.setUsername(user.getName());
        profile.setImage(user.getImage());
        profile.setFollowing(followRepository.existsByUser(user));

        return profile;
    }

    public void followUsers(String username){
        
        User followee = userRepository.findByName(username);
        
        FollowBuilder builder = Follow.builder();

        builder.followee(followee);
        builder.follower(this.getCurrentUser());

        Follow build = builder.build();
        
        followRepository.save(build);
    }

    public void unfollowUsers(String username) {
        followRepository.deleteByName(username);
    }
}

