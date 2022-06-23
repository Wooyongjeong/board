package com.wooyong.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {
	/**
	 * TODO 게시글 목록 보여주는 controller ✅
	 * TODO 게시글 목록 paging도 해야겠지? ✅
	 * TODO 구글 로그인 OAuth2 ✅
	 * TODO 게시글 상세 보여주는 controller & view & service ✅
	 * TODO 게시글 쓰기 controller & view & service ✅
	 * TODO 게시글의 댓글 보여주는 controller & view & service
	 * TODO 게시글 댓글 작성 controller & view & service
	 * TODO 게시글 댓글 paging도 해야겠지?
	 * TODO 게시글 댓글 대댓글 무한 대댓글까지 해서 포폴로 쓰고 자소서도 작성해야겠지?
	 */

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
}
