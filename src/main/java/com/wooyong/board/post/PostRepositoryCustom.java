package com.wooyong.board.post;

import com.wooyong.board.post.dto.PostsDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostsDto> findPosts();
}
