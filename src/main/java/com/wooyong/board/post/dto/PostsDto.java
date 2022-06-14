package com.wooyong.board.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class PostsDto {

    private Long id;
    private String title;
    private String author;
    private int voteNum;
    private LocalDateTime createdDate;

    @QueryProjection
    public PostsDto(Long id, String title, String author, int voteNum, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.voteNum = voteNum;
        this.createdDate = createdDate;
    }
}
