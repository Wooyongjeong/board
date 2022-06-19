package com.wooyong.board.service.post;

import com.wooyong.board.config.auth.dto.SessionMember;
import com.wooyong.board.domain.member.Member;
import com.wooyong.board.domain.member.MemberRepository;
import com.wooyong.board.domain.post.Post;
import com.wooyong.board.domain.post.PostRepository;
import com.wooyong.board.web.dto.post.PostCreateDto;
import com.wooyong.board.web.dto.post.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostDto findPostDtoById(Long id) {
        log.info("findPostDtoById 실행");

        return postRepository.findPostDtoById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "해당 게시글이 존재하지 않습니다."));
    }

    @Transactional
    public Long save(SessionMember sessionMember, PostCreateDto postCreateDto) {
        log.info("save 실행");

        Member member = memberRepository.findByEmail(sessionMember.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다."));
        Post post = postCreateDto.toEntity(member);
        return postRepository.save(post).getId();
    }
}
