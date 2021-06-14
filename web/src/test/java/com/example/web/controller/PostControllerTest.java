package com.example.web.controller;

import com.example.web.model.Post;
import com.example.web.service.IPostService;
import com.example.web.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author sa
 * @date 14.06.2021
 * @time 12:13
 */
@WebMvcTest(controllers = PostController.class)//is only going to scan the controller you've defined and the MVC infrastructure.
@WithMockUser(roles = {SecurityUtils.USER})
class PostControllerTest extends ControllerTestsBase
{
    @MockBean
    private IPostService postService;

    @Test
    public void getPosts() throws Exception
    {
        var page = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        when(postService.findPosts(page)).thenReturn(List.of(new Post()));

        this.mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(postService).findPosts(page);
    }

    @Test
    public void deletePost() throws Exception
    {
        this.mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(postService).deletePost(1L);
    }
}
