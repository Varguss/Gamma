package ru.gamma_station.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gamma_station.domain.Post;
import ru.gamma_station.service.PostService;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Slf4j
@RestController
public class PostRestController {
    private PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        return postService.getAll();
    }

    @GetMapping("post/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPost(@PathVariable("id") Long id) {
        return postService.find(id);
    }

    @Validated
    @GetMapping("posts/{page}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPage(@PathVariable("page") @Pattern(regexp = "\\d+") @Min(0) int page,
                              @RequestParam("size") @Pattern(regexp = "\\d+") @Min(1) int size) {
        return postService.getPage(page, size);
    }
}
