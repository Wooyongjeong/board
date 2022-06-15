package com.wooyong.board.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PostsDto {

    private Long id;
    private String title;
    private String author;
    private int voteNum;
    private String createdDate;

    @QueryProjection
    public PostsDto(Long id, String title, String author, int voteNum, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.voteNum = voteNum;
        this.createdDate = getDate(createdDate);
    }

    private String getDate(LocalDateTime createdDate) {
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

}
