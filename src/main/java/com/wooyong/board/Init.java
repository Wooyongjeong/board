package com.wooyong.board;

import com.wooyong.board.domain.Comment;
import com.wooyong.board.domain.Member;
import com.wooyong.board.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class Init {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    static class InitService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            Member memberA = Member.builder()
                    .email("testA@test.com")
                    .name("memberA")
                    .nickname("memberNicknameA")
                    .build();
            Member memberB = Member.builder()
                    .email("testB@test.com")
                    .name("memberB")
                    .nickname("memberNicknameB")
                    .build();
            em.persist(memberA);
            em.persist(memberB);

            Post[] posts = new Post[100];
            for (int i = 0; i < 100; i++) {
                Member member = i % 2 == 0 ? memberA : memberB;
                Post post = Post.builder()
                        .member(member)
                        .title("Title " + i)
                        .content("content " + i)
                        .build();
                posts[i] = post;
                em.persist(post);
            }

            for (int i = 0; i < 200; i++) {
                Member member = i % 2 != 0 ? memberA : memberB;
                Post post = posts[i % 100];

                Comment comment = Comment.builder()
                        .post(post)
                        .member(member)
                        .comment("comment " + i)
                        .build();
                em.persist(comment);
            }

            em.flush();
            em.clear();
        }
    }
}
