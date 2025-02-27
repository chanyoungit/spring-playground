package com.test.domain.post.controller;

import com.test.domain.post.dto.PostReqDTO;
import com.test.domain.post.dto.PostResDTO;
import com.test.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시물 추가
    @PostMapping("/post")
    public PostResDTO addPost(@RequestBody PostReqDTO request) {
        PostResDTO response = postService.addPost(request);
        return response;
    }

    // 팔로우한 사용자들의 게시물 조회
    @GetMapping("/feed/{userId}")
    public List<PostResDTO> getFollowUsersPosts(@PathVariable Long userId) {
        return postService.getFollowUsersPosts(userId);
    }

    @GetMapping("/feed/redis/{userId}")
    public List<PostResDTO> getFollowUsersPostsWithRedis(@PathVariable Long userId) {
        return postService.getFollowUsersPostsWithRedis(userId);
    }
}
