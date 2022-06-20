package com.wooyong.board.web.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PostDto {

    private String title;
    private String content;
    private String author;
    private String authorEmail;
    private String authorPicture;
    private String createdDate;

    @QueryProjection
    public PostDto(String title, String content, String author, String authorEmail, String authorPicture, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.authorEmail = authorEmail;
        this.authorPicture = authorPicture;
        this.createdDate = getDate(createdDate);
    }

    private String getDate(LocalDateTime createdDate) {
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

}
