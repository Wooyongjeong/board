package com.wooyong.board.service.comment;

import com.wooyong.board.domain.comment.Comment;
import com.wooyong.board.domain.comment.CommentRepository;
import com.wooyong.board.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentDto> findCommentDtoListByPostId(Long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        Map<Long, CommentDto> map = new HashMap<>();
        List<CommentDto> result = new ArrayList<>();
        comments.forEach(comment -> {
            CommentDto commentDto = CommentDto.fromEntity(comment);
            map.put(commentDto.getId(), commentDto);
            if (comment.getParent() != null) {
                map.get(comment.getParent().getId()).getChildren().add(commentDto);
            } else {
                result.add(commentDto);
            }
        });
        return result;
    }
}
