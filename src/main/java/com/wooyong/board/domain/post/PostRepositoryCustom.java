package com.wooyong.board.domain.post;

import com.wooyong.board.web.dto.post.PostDto;
import com.wooyong.board.web.dto.post.PostsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostRepositoryCustom {
    Page<PostsDto> findPostsPage(Pageable pageable);
    Optional<PostDto> findPostDtoById(Long id);
}
