package com.wooyong.board;

import com.wooyong.board.comment.Comment;
import com.wooyong.board.member.Member;
import com.wooyong.board.post.Post;
import com.wooyong.board.comment.CommentRepository;
import com.wooyong.board.member.MemberRepository;
import com.wooyong.board.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
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
                    .nickname("memberNicknameA")
                    .build();
            ;
            members[1] = Member.builder()
                    .email("testB@test.com")
                    .name("memberB")
                    .nickname("memberNicknameB")
                    .build();
            ;
            members[2] = Member.builder()
                    .email("testC@test.com")
                    .name("memberC")
                    .nickname("memberNicknameC")
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
