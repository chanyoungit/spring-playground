package com.test.domain.follow.service;

import com.test.domain.follow.domain.Follow;
import com.test.domain.follow.dto.FollowDTO;
import com.test.domain.follow.repository.FollowRepository;
import com.test.domain.user.domain.User;
import com.test.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public FollowDTO addFollow(FollowDTO followDTO) {
        User user = userRepository.findById(followDTO.Id())
                .orElseThrow(() -> new RuntimeException("Not Found user"));

        User targetUser = userRepository.findById(followDTO.targetId())
                .orElseThrow(() -> new RuntimeException("Not Found targetUser"));

        Follow follow = Follow.builder()
                .follower(user)
                .following(targetUser)
                .build();

        followRepository.save(follow);

        // Redis에 팔로잉 목록 업데이트
        String key = "following:" + user.getId();
        SetOperations<String, String> setOps = redisTemplate.opsForSet();
        setOps.add(key, String.valueOf(targetUser.getId()));

        return followDTO;
    }
}
