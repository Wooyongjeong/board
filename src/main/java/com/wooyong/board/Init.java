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
                    .picture("https://w.namu.la/s/3c515c13d120eb7de7831eb509eb50fd3371b2481559fdab3c8d21acffa84e461770a340569488999965f7a0863f1b4d1bfea58637c59de2a0df1af6d068eb4161b9477fbc5336adc99b5af08e1d8607b2b21f7e3a4001cba95a5d020901a378")
                    .role(Role.USER)
                    .build();
            members[1] = Member.builder()
                    .email("testB@test.com")
                    .name("memberB")
                    .picture("https://img.hankyung.com/photo/201904/01.19372617.1.jpg")
                    .role(Role.USER)
                    .build();
            members[2] = Member.builder()
                    .email("testC@test.com")
                    .name("memberC")
                    .picture("https://w.namu.la/s/5c93e64318ad13914708d6409de0c85768c8b07112ca3c721011796250088c0fbcb5d51f7aad441a3d2de5c6db352d1134f2bba58e9bd7a8948a0babdb24b02da4e8e879c28ae53b256cc31cb009199a213b808ce583c3a0c9c8fb546ab95315d435239afe1709c6f9e9bda3b356af4c")
                    .role(Role.USER)
                    .build();
            memberRepository.saveAll(Arrays.asList(members));

            Post[] posts = new Post[1000];
            String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas suscipit sem sagittis eros euismod fermentum. Donec consequat urna nec leo pellentesque, consequat volutpat urna semper. Fusce finibus congue nibh nec pharetra. Ut dignissim ut lorem a finibus. Nunc at tortor semper nisl ultricies tempor. Maecenas pharetra metus nulla, interdum tincidunt purus molestie vitae. Nunc volutpat et urna nec ullamcorper. Suspendisse posuere, nulla ac imperdiet viverra, lacus massa lobortis odio, nec mollis augue mauris ut metus. Pellentesque quis libero a metus commodo dignissim pellentesque eget nisi. Morbi vel suscipit eros, at euismod nisl. Morbi quis rutrum augue. Donec blandit cursus mi, nec venenatis lectus sagittis ac. Pellentesque nec leo quis mauris dapibus fringilla ac sit amet massa.";
            for (int i = 0; i < 1000; i++) {
                Member member = members[i % 3];
                Post post = Post.builder()
                        .member(member)
                        .title("Title " + (i + 1))
                        .content(content)
                        .build();
                posts[i] = post;

                postRepository.save(post);
            }

            String[] comments = {
                    "BTS brought me here💜💜",
                    "ㅅㅏ과ㅌㅣㅂㅣ 수위 실화냐???",
                    "She is Chinese",
                    "찐이다 올려",
                    "고정 부탁드려요❤"
            };
            for (int i = 0; i < 10000; i++) {
                Member member = members[i % 3];
                Post post = posts[i % 1000];

                Comment comment = Comment.builder()
                        .post(post)
                        .member(member)
                        .content(comments[i % 5])
                        .parent(null)
                        .build();
                commentRepository.save(comment);
            }
        }
    }
}
