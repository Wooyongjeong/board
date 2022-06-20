package com.wooyong.board.web.controller;

import com.wooyong.board.config.auth.LoginMember;
import com.wooyong.board.config.auth.dto.SessionMember;
import com.wooyong.board.domain.post.PostRepository;
import com.wooyong.board.web.dto.post.PostsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model,
                       @LoginMember SessionMember member,
                       @PageableDefault(page = 1, size = 20) Pageable pageable) {
        log.info("[GET] /");

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        Page<PostsDto> posts = postRepository.findPostsPage(pageRequest);
        model.addAttribute("member", member);
        model.addAttribute("posts", posts);
        return "home";
    }
}
