package com.wooyong.board.domain.post;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wooyong.board.web.dto.post.PostDto;
import com.wooyong.board.web.dto.post.PostsDto;
import com.wooyong.board.web.dto.post.QPostDto;
import com.wooyong.board.web.dto.post.QPostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.wooyong.board.domain.member.QMember.*;
import static com.wooyong.board.domain.post.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostsDto> findPostsPage(Pageable pageable) {
        List<PostsDto> content = queryFactory
                .select(new QPostsDto(
                        post.id,
                        post.title,
                        member.name,
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

    @Override
    public Optional<PostDto> findPostDtoById(Long id) {
        return Optional.ofNullable(
            queryFactory
                    .select(new QPostDto(
                            post.title,
                            post.content,
                            member.name,
                            member.picture,
                            post.createdDate
                    ))
                    .from(post)
                    .leftJoin(post.member, member)
                    .where(post.id.eq(id))
                    .fetchOne());
    }
}
