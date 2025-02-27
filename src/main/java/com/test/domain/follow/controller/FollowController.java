package com.test.domain.follow.controller;

import com.test.domain.follow.dto.FollowDTO;
import com.test.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow")
    public FollowDTO addFollow(@RequestBody FollowDTO followDTO) {
        return followService.addFollow(followDTO);
    }
}
