package com.wooyong.board.web.dto.comment;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString(of = {"content", "author", "createdDate"})
@Data
public class CommentDto {

    private String content;
    private String author;
    private String createdDate;

    @QueryProjection
    public CommentDto(String content, String author, LocalDateTime createdDate) {
        this.content = content;
        this.author = author;
        this.createdDate = getDate(createdDate);
    }

    private String getDate(LocalDateTime createdDate) {
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"));
    }

}
