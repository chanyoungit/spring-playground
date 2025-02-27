package com.test.domain.post.service;

import com.test.domain.follow.repository.FollowRepository;
import com.test.domain.post.domain.Post;
import com.test.domain.post.dto.PostReqDTO;
import com.test.domain.post.dto.PostResDTO;
import com.test.domain.post.repository.PostRepository;
import com.test.domain.user.domain.User;
import com.test.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public PostResDTO addPost(PostReqDTO request) {
        User user = userRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new RuntimeException("User not found with nickname : " + request.nickname()));

        Post post = Post.builder()
                .writer(user)
                .title(request.title())
                .description(request.description())
                .build();

        postRepository.save(post);

        return new PostResDTO(post.getId(), user.getNickname(), post.getTitle(), post.getDescription());
    }

    public List<PostResDTO> getFollowUsersPosts(Long userId) {
        List<Post> posts = postRepository.findFollowingUsersPosts(userId);

        // Posts -> PostResDTO 변환 후 반환
        return posts.stream()
                .map(post -> new PostResDTO(
                        post.getId(),
                        post.getWriter().getNickname(),
                        post.getTitle(),
                        post.getDescription()
                ))
                .toList();
    }


    public List<PostResDTO> getFollowUsersPostsWithRedis(Long userId) {
        String key = "following:" + userId;
        SetOperations<String, String> setOps = redisTemplate.opsForSet();

        Set<String> followingIdsStr = setOps.members(key);

        Set<Long> followingIds = followingIdsStr.stream()
                .map(Long::parseLong)
                .collect(Collectors.toSet());


        List<Post> posts = postRepository.findFollowingUsersPostsByIds(followingIds);

        return posts.stream()
                .map(post -> new PostResDTO(
                        post.getId(),
                        post.getWriter().getNickname(),
                        post.getTitle(),
                        post.getDescription()
                ))
                .collect(Collectors.toList());
    }
}
