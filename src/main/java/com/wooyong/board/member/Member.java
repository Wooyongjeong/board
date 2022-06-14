package com.wooyong.board.member;

import com.wooyong.board.BaseTimeEntity;
import com.wooyong.board.comment.Comment;
import com.wooyong.board.post.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Member(String email, String name, String nickname) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.role = Role.USER;
    }
}
