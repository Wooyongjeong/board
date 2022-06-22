package com.wooyong.board.domain.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wooyong.board.web.dto.comment.CommentDto;
import com.wooyong.board.web.dto.comment.QCommentDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.wooyong.board.domain.comment.QComment.comment;
import static com.wooyong.board.domain.post.QPost.post;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentDto> findCommentDtoListByPostId(Long postId) {
        return queryFactory
                .select(new QCommentDto(
                        comment.content,
                        comment.member.name,
                        comment.createdDate
                ))
                .from(comment)
                .join(comment.post, post).where(post.id.eq(postId))
                .fetch();
    }
}
