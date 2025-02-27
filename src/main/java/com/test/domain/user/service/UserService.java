package com.test.domain.user.service;

import com.test.domain.user.domain.User;
import com.test.domain.user.dto.UserRequest;
import com.test.domain.user.dto.UserResponse;
import com.test.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse addUser(UserRequest request) {
        User user = User.builder()
                .nickname(request.nickname())
                .build();

        userRepository.save(user);

        return new UserResponse(user.getId(), user.getNickname());
    }
}
