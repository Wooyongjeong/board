package com.wooyong.board.comment;

import com.wooyong.board.post.Post;
import com.wooyong.board.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob
    @Column(nullable = false)
    private String comment;

    @Builder
    public Comment(Post post, Member member, String comment) {
        this.post = post;
        this.member = member;
        this.comment = comment;
    }
}
