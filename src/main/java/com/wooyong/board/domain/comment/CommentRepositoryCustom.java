package com.wooyong.board.domain.comment;

import com.wooyong.board.web.dto.comment.CommentDto;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findCommentsByPostId(Long postId);
    List<CommentDto> findCommentDtoListByPostId(Long postId);
}
