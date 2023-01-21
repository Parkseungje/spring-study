package com.realworld.springstudy.api.user.repository;

import com.realworld.springstudy.api.user.entity.Follow;
import com.realworld.springstudy.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Boolean existsByFollowee(User user);

    Follow findByFolloweeAndFollower(User followee, User follower);
}
