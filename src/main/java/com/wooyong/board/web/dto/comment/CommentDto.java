package com.wooyong.board.web.dto.comment;

import com.querydsl.core.annotations.QueryProjection;
import com.wooyong.board.domain.comment.Comment;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ToString(of = {"content", "author", "createdDate"})
@Data
public class CommentDto {

    private Long id;
    private String content;
    private String author;
    private String createdDate;
    private List<CommentDto> children = new ArrayList<>();

    @QueryProjection
    public CommentDto(Long id, String content, String author, LocalDateTime createdDate) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdDate = getDate(createdDate);
    }

    private String getDate(LocalDateTime createdDate) {
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"));
    }

    public static CommentDto fromEntity(Comment comment) {
        return comment.isDeleted() ?
                new CommentDto(comment.getId(), "삭제된 댓글입니다.", null, comment.getCreatedDate()) :
                new CommentDto(comment.getId(), comment.getContent(), comment.getMember().getName(), comment.getCreatedDate());
    }
}
