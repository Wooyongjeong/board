package com.wooyong.board.domain.post;

import com.wooyong.board.BaseTimeEntity;
import com.wooyong.board.domain.comment.Comment;
import com.wooyong.board.domain.member.Member;
import com.wooyong.board.web.dto.post.PostCreateUpdateDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 500, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private int view;

    private int voteNum;

    @Builder
    public Post(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.view = 0;
        this.voteNum = 0;
    }

    public void update(PostCreateUpdateDto postCreateUpdateDto) {
        this.title = postCreateUpdateDto.getTitle();
        this.content = postCreateUpdateDto.getContent();
    }
}
