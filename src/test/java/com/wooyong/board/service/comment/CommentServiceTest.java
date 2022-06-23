package com.wooyong.board.service.comment;

import com.wooyong.board.web.dto.comment.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    public void getCommentsByPostIdTest() throws Exception {
        Long postId = 1L;
        List<CommentDto> commentDtoList = commentService.findCommentDtoListByPostId(postId);

        for (CommentDto commentDto : commentDtoList) {
            printCommentDto(commentDto);
        }
    }

    private void printCommentDto(CommentDto commentDto) {
        System.out.println("commentDto = " + commentDto);
        if (commentDto.getChildren().size() == 0) {
            return;
        }
        commentDto.getChildren().forEach(this::printCommentDto);
    }

}