package com.test.domain.follow.repository;

import com.test.domain.follow.domain.Follow;
import com.test.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    // 특정 사용자가 팔로우하는 모든 사용자 조회
    List<Follow> findByFollower(User follower);
}
