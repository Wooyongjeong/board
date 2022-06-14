package com.wooyong.board.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wooyong.board.post.dto.PostsDto;
import com.wooyong.board.post.dto.QPostsDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.wooyong.board.member.QMember.*;
import static com.wooyong.board.post.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

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
}
