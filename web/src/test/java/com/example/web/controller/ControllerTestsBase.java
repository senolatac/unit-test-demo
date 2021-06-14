package com.example.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author sa
 * @date 14.06.2021
 * @time 12:14
 */
class ControllerTestsBase
{
    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext)
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }
}
