package com.wooyong.board.post;

import com.wooyong.board.post.dto.PostsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostsDto> findPosts();
    Page<PostsDto> findPostsPage(Pageable pageable);
}
