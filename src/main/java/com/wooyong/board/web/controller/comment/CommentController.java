package com.wooyong.board.web.controller.comment;

import com.wooyong.board.domain.comment.CommentRepository;
import com.wooyong.board.service.comment.CommentService;
import com.wooyong.board.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @GetMapping("/comments")
    public List<CommentDto> comments(@RequestParam(value = "post") Long postId) {
        return commentService.findCommentDtoListByPostId(postId);
    }
}
