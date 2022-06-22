package com.wooyong.board.post;

import com.wooyong.board.domain.post.PostRepository;
import com.wooyong.board.web.dto.post.PostsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("PostsDto Page 가져오기 테스트")
    public void findPostsPage() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<PostsDto> result = postRepository.findPostsDtoPage(pageRequest);

        assertThat(result.getTotalElements()).isEqualTo(100);
        assertThat(result.getContent().size()).isEqualTo(20);
    }


}