package com.example.web.controller;

import com.example.web.service.queue.IQueueService;
import com.example.web.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author sa
 * @date 14.06.2021
 * @time 12:20
 */
@WebMvcTest(controllers = AdminController.class)//for testing the controller layer and you need to provide remaining dependencies required using Mock Objects.
@WithMockUser(roles = {SecurityUtils.ADMIN})
class AdminControllerTest extends ControllerTestsBase
{
    @MockBean
    private IQueueService queueService;

    @Test
    public void createPostQueueRequests() throws Exception
    {
        this.mockMvc.perform(post("/api/admin/queue/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(queueService).sendPostRequestForAllItems();
    }
}
