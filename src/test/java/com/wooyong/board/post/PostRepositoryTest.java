package com.wooyong.board.post;

import com.wooyong.board.post.dto.PostsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("PostsDto 가져오기 테스트")
    void findPosts() {
        List<PostsDto> posts = postRepository.findPosts();

        assertThat(posts.size()).isEqualTo(100);
    }

}