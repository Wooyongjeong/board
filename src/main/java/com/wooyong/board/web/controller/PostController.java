package com.wooyong.board.web.controller;

import com.wooyong.board.config.auth.LoginMember;
import com.wooyong.board.config.auth.dto.SessionMember;
import com.wooyong.board.service.post.PostService;
import com.wooyong.board.web.dto.post.PostCreateDto;
import com.wooyong.board.web.dto.post.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/new")
    public String createForm(@LoginMember SessionMember member, Model model) {
        log.info("[GET] /posts/new");

        model.addAttribute("member", member);
        model.addAttribute("postCreateDto", new PostCreateDto());

        return "posts/createForm";
    }

    @PostMapping("/posts/new")
    public String create(@ModelAttribute("postCreateDto") @Valid PostCreateDto post,
                         BindingResult result,
                         @LoginMember SessionMember member) {
        log.info("[POST] /posts/new");

        if (result.hasErrors()) {
            return "posts/createForm";
        }

        Long id = postService.save(member, post);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/{postId}")
    public String detail(@LoginMember SessionMember member,
                         @PathVariable("postId") Long id,
                         Model model) {
        log.info("[GET] /posts/{}", id);

        PostDto postDto = postService.findPostDtoById(id);

        model.addAttribute("member", member);
        model.addAttribute("post", postDto);

        return "posts/detail";
    }
}
