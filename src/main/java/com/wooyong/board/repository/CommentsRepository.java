package com.wooyong.board.repository;

import com.wooyong.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
