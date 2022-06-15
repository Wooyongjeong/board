package com.wooyong.board.post;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wooyong.board.post.dto.PostsDto;
import com.wooyong.board.post.dto.QPostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.wooyong.board.member.QMember.*;
import static com.wooyong.board.post.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostsDto> findPosts() {
        return queryFactory
                .select(new QPostsDto(
                        post.id,
                        post.title,
                        member.nickname,
                        post.voteNum,
                        post.createdDate))
                .from(post)
                .leftJoin(post.member, member)
                .fetch();
    }

    @Override
    public Page<PostsDto> findPostsPage(Pageable pageable) {
        List<PostsDto> content = queryFactory
                .select(new QPostsDto(
                        post.id,
                        post.title,
                        member.nickname,
                        post.voteNum,
                        post.createdDate))
                .from(post)
                .leftJoin(post.member, member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createdDate.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
