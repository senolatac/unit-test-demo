package com.example.web.controller;

import com.example.web.model.Post;
import com.example.web.service.IPostService;
import com.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sa
 * @date 14.06.2021
 * @time 12:12
 */
@RestController
@Secured(SecurityUtils.ROLE_USER)
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController
{
    private final IPostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@PageableDefault(size = 10)
                                               @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {
        return ResponseEntity.ok(postService.findPosts(pageable));
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId)
    {
        postService.deletePost(postId);

        return ResponseEntity.ok(true);
    }
}
