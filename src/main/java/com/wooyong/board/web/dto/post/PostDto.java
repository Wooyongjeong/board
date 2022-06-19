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
    private String authorPicture;
    private LocalDateTime createdDate;

    @QueryProjection
    public PostDto(String title, String content, String author, String authorPicture, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.authorPicture = authorPicture;
        this.createdDate = createdDate;
    }

}
