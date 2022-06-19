package com.wooyong.board;

import com.wooyong.board.domain.comment.Comment;
import com.wooyong.board.domain.member.Member;
import com.wooyong.board.domain.member.Role;
import com.wooyong.board.domain.post.Post;
import com.wooyong.board.domain.comment.CommentRepository;
import com.wooyong.board.domain.member.MemberRepository;
import com.wooyong.board.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;

//@Profile("local")
@Component
@RequiredArgsConstructor
public class Init {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;
        private final PostRepository postRepository;
        private final CommentRepository commentRepository;

        @Transactional
        public void init() {
            Member[] members = new Member[3];
            members[0] = Member.builder()
                    .email("testA@test.com")
                    .name("memberA")
                    .picture("pictureA")
                    .role(Role.USER)
                    .build();
            members[1] = Member.builder()
                    .email("testB@test.com")
                    .name("memberB")
                    .picture("pictureB")
                    .role(Role.USER)
                    .build();
            members[2] = Member.builder()
                    .email("testC@test.com")
                    .name("memberC")
                    .picture("pictureC")
                    .role(Role.USER)
                    .build();
            memberRepository.saveAll(Arrays.asList(members));

            Post[] posts = new Post[1000];
            for (int i = 0; i < 1000; i++) {
                Member member = members[i % 3];
                Post post = Post.builder()
                        .member(member)
                        .title("Title " + (i + 1))
                        .content("content " + (i + 1))
                        .build();
                posts[i] = post;

                postRepository.save(post);
            }

            for (int i = 0; i < 2000; i++) {
                Member member = members[i % 3];
                Post post = posts[i % 1000];

                Comment comment = Comment.builder()
                        .post(post)
                        .member(member)
                        .comment("comment " + (i + 1))
                        .build();
                commentRepository.save(comment);
            }
        }
    }
}
