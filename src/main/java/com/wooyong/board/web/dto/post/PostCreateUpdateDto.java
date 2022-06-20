package com.wooyong.board.web.dto.post;

import com.wooyong.board.domain.member.Member;
import com.wooyong.board.domain.post.Post;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class PostCreateUpdateDto {

    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "본문을 입력해주세요")
    private String content;

    @Builder
    public PostCreateUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(Member member) {
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }

}
