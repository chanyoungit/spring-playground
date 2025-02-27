package com.test.domain.post.repository;

import com.test.domain.post.domain.Post;
import com.test.domain.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 특정 사용자의 팔로잉 목록에 속하는 모든 게시글 조회
    List<Post> findByWriterIn(List<User> writers);

    @Query("SELECT p FROM Post p " +
            "WHERE p.writer.id IN " +
            "(SELECT f.following.id FROM Follow f WHERE f.follower.id = :userId) ")
    List<Post> findFollowingUsersPosts(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p " +
            "WHERE p.writer.id IN :followingIds ")
    List<Post> findFollowingUsersPostsByIds(@Param("followingIds") Set<Long> followingIds);

}
