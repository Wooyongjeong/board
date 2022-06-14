package com.wooyong.board.repository;

import com.wooyong.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {
}
