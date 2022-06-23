package com.wooyong.board.domain.comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findCommentsByPostId(Long postId);
}
