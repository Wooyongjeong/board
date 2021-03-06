package com.wooyong.board.domain.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.wooyong.board.domain.comment.QComment.comment;
import static com.wooyong.board.domain.post.QPost.*;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        return queryFactory
                .selectFrom(comment)
                .join(comment.post, post).fetchJoin()
                .where(comment.post.id.eq(postId))
                .orderBy(
                        comment.parent.id.asc().nullsFirst(),
                        comment.createdDate.asc())
                .fetch();
    }

}
