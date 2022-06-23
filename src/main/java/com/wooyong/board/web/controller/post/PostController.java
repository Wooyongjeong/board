package com.wooyong.board.web.controller.post;

import com.wooyong.board.config.auth.LoginMember;
import com.wooyong.board.config.auth.dto.SessionMember;
import com.wooyong.board.domain.post.Post;
import com.wooyong.board.domain.post.PostRepository;
import com.wooyong.board.service.post.PostService;
import com.wooyong.board.web.Result;
import com.wooyong.board.web.dto.post.PostCreateUpdateDto;
import com.wooyong.board.web.dto.post.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/posts/new")
    public String createForm(@LoginMember SessionMember member, Model model) {
        log.info("[GET] /posts/new");

        model.addAttribute("member", member);
        model.addAttribute("post", new PostCreateUpdateDto());

        return "posts/createForm";
    }

    @PostMapping("/posts/new")
    public String create(@ModelAttribute("post") @Valid PostCreateUpdateDto post,
                         BindingResult result,
                         @LoginMember SessionMember member,
                         Model model) {
        log.info("[POST] /posts/new");

        model.addAttribute("member", member);
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

        model.addAttribute("member", member);

        PostDto postDto = postService.findPostDtoById(id);
        model.addAttribute("post", postDto);

        boolean isLoginMemberAuthor = postService.isLoginMemberAuthor(member, postDto);
        model.addAttribute("isLoginMemberAuthor", isLoginMemberAuthor);

        return "posts/detail";
    }

    @ResponseBody
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Result> delete(@LoginMember SessionMember member,
                                         @PathVariable("postId") Long id,
                                         Model model) {
        log.info("[DELETE] /posts/{}", id);

        model.addAttribute("member", member);

        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            Result result = new Result(HttpStatus.NOT_FOUND.value(), "해당 게시글을 찾을 수 없습니다.");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        boolean isLoginMemberAuthor = postService.isLoginMemberAuthor(member, post);
        if (!isLoginMemberAuthor) {
            Result result = new Result(HttpStatus.FORBIDDEN.value(), "게시글은 작성자만 삭제할 수 있습니다.");
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        }

        postRepository.delete(post);
        Result result = new Result(HttpStatus.OK.value(), "삭제되었습니다.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/posts/edit/{postId}")
    public String editForm(@LoginMember SessionMember member,
                           @PathVariable("postId") Long id,
                           Model model) {
        log.info("[GET] /posts/edit/{}", id);

        model.addAttribute("member", member);
        PostDto postDto = postService.findPostDtoById(id);

        boolean isLoginMemberAuthor = postService.isLoginMemberAuthor(member, postDto);
        if (!isLoginMemberAuthor) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "게시글은 작성자만 수정할 수 있습니다.");
        }

        PostCreateUpdateDto postCreateUpdateDto = new PostCreateUpdateDto();
        postCreateUpdateDto.setTitle(postDto.getTitle());
        postCreateUpdateDto.setContent(postDto.getContent());
        model.addAttribute("post", postCreateUpdateDto);
        return "posts/updateForm";
    }

    @PostMapping("/posts/edit/{postId}")
    public String edit(@ModelAttribute("post") @Valid PostCreateUpdateDto postCreateUpdateDto,
                       BindingResult result,
                       @LoginMember SessionMember member,
                       @PathVariable("postId") Long id,
                       Model model) {
        log.info("[POST] /posts/edit/{}", id);

        model.addAttribute("member", member);
        if (result.hasErrors()) {
            return "posts/updateForm";
        }

        postService.updatePost(id, postCreateUpdateDto);
        return "redirect:/posts/" + id;
    }
}
