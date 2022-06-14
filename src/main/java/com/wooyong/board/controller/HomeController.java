package com.wooyong.board.controller;

import com.wooyong.board.post.PostRepository;
import com.wooyong.board.post.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<PostsDto> posts = postRepository.findPosts();
        model.addAttribute("posts", posts);
        return "home";
    }
}
