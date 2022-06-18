package com.wooyong.board.web.controller;

import com.wooyong.board.post.PostRepository;
import com.wooyong.board.post.dto.PostsDto;
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
                       @PageableDefault(page = 1, size = 20) Pageable pageable) {
        log.info("[GET] home");

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        Page<PostsDto> posts = postRepository.findPostsPage(pageRequest);
        model.addAttribute("posts", posts);
        return "home";
    }
}
