package com.wooyong.board.domain.comment;

import com.wooyong.board.web.dto.comment.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void findCommentDtoListByPostId() {
        Long postId = 1L;
        List<CommentDto> commentDtoList = commentRepository.findCommentDtoListByPostId(postId);

        for (CommentDto commentDto : commentDtoList) {
            System.out.println("commentDto = " + commentDto);
        }
    }

}