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
                    .picture("https://user-images.githubusercontent.com/38418028/175755775-625cf999-171e-4d85-9d35-4c1502fba0c4.jpg")
                    .role(Role.USER)
                    .build();
            members[1] = Member.builder()
                    .email("testB@test.com")
                    .name("memberB")
                    .picture("https://user-images.githubusercontent.com/38418028/175755791-f7642d8d-c1b4-43de-97f9-06885062a469.jpg")
                    .role(Role.USER)
                    .build();
            members[2] = Member.builder()
                    .email("testC@test.com")
                    .name("memberC")
                    .picture("https://user-images.githubusercontent.com/38418028/175755857-1ab8004b-ca97-4390-a087-e8b9b9983fbe.jpg")
                    .role(Role.USER)
                    .build();
            memberRepository.saveAll(Arrays.asList(members));

            Post[] posts = new Post[1000];
            String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas suscipit sem sagittis eros euismod fermentum. Donec consequat urna nec leo pellentesque, consequat volutpat urna semper. Fusce finibus congue nibh nec pharetra. Ut dignissim ut lorem a finibus. Nunc at tortor semper nisl ultricies tempor. Maecenas pharetra metus nulla, interdum tincidunt purus molestie vitae. Nunc volutpat et urna nec ullamcorper. Suspendisse posuere, nulla ac imperdiet viverra, lacus massa lobortis odio, nec mollis augue mauris ut metus. Pellentesque quis libero a metus commodo dignissim pellentesque eget nisi. Morbi vel suscipit eros, at euismod nisl. Morbi quis rutrum augue. Donec blandit cursus mi, nec venenatis lectus sagittis ac. Pellentesque nec leo quis mauris dapibus fringilla ac sit amet massa.";
            for (int i = 0; i < 1000; i++) {
                Member member = members[i % 3];
                Post post = Post.builder()
                        .member(member)
                        .title("제목 [" + (i + 1) + "]번 입니다.")
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

            for (Post post : posts) {
                Comment comment1 = Comment.builder()
                        .post(post)
                        .member(members[0])
                        .content(comments[0])
                        .parent(null)
                        .build();
                Comment comment1_1 = Comment.builder()
                        .post(post)
                        .member(members[1])
                        .content(comments[1])
                        .parent(comment1)
                        .build();
                Comment comment1_1_1 = Comment.builder()
                        .post(post)
                        .member(members[2])
                        .content(comments[2])
                        .parent(comment1_1)
                        .build();
                Comment comment2 = Comment.builder()
                        .post(post)
                        .member(members[1])
                        .content(comments[3])
                        .parent(null)
                        .build();
                Comment comment2_1 = Comment.builder()
                        .post(post)
                        .member(members[2])
                        .content(comments[4])
                        .parent(comment2)
                        .build();

                commentRepository.save(comment1);
                commentRepository.save(comment2);
                commentRepository.save(comment1_1);
                commentRepository.save(comment2_1);
                commentRepository.save(comment1_1_1);
            }
        }
    }
}
