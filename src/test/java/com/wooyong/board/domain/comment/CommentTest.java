package com.wooyong.board.domain.comment;

import com.wooyong.board.domain.member.Member;
import com.wooyong.board.domain.post.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void deleteTest() {
        //given
        Comment comment = commentRepository.findById(1L).orElse(null);
        boolean before = comment.isDeleted();
        assertNotNull(comment);

        //when
        comment.delete();

        //then
        boolean after = comment.isDeleted();
        assertThat(before).isFalse();
        assertThat(after).isTrue();
    }

    @Test
    void findDeletableCommentWhenExistsTest() {
        //given
        Comment comment1 = commentRepository.findById(1L).orElse(null);
        Comment comment2 = commentRepository.findById(2L).orElse(null);
        Comment comment3 = commentRepository.findById(3L).orElse(null);
        Comment comment4 = commentRepository.findById(4L).orElse(null);
        Comment comment5 = commentRepository.findById(5L).orElse(null);
        assertNotNull(comment1);
        assertNotNull(comment2);
        assertNotNull(comment3);
        assertNotNull(comment4);
        assertNotNull(comment5);

        comment2.delete();
        comment3.delete();

        ReflectionTestUtils.setField(comment1, "children", List.of(comment2));
        ReflectionTestUtils.setField(comment2, "children", List.of(comment3, comment4));
        ReflectionTestUtils.setField(comment3, "children", List.of(comment5));
        ReflectionTestUtils.setField(comment4, "children", List.of());
        ReflectionTestUtils.setField(comment5, "children", List.of());

        //when
        Optional<Comment> deletableComment = comment5.findDeletableComment();

        //then
        assertThat(deletableComment).containsSame(comment3);
    }

    @Test
    void findDeletableCommentWhenNotExistsTest() {
        //given
        Comment comment1 = commentRepository.findById(1L).orElse(null);
        assertNotNull(comment1);

        Post post = comment1.getPost();
        Member member = comment1.getMember();
        Comment comment2 = Comment.builder()
                .post(post)
                .member(member)
                .content("comment2")
                .parent(comment1)
                .build();
        Comment comment3 = Comment.builder()
                .post(post)
                .member(member)
                .content("comment3")
                .parent(comment2)
                .build();

        ReflectionTestUtils.setField(comment1, "children", List.of(comment2));
        ReflectionTestUtils.setField(comment2, "children", List.of(comment3));
        ReflectionTestUtils.setField(comment3, "children", List.of());

        //when
        Optional<Comment> deletableComment = comment2.findDeletableComment();

        //then
        assertThat(deletableComment).isEmpty();
    }

}