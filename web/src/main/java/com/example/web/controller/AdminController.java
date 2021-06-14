package com.example.web.controller;

import com.example.web.service.queue.IQueueService;
import com.example.web.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:12
 */
@RestController
@Secured(SecurityUtils.ROLE_ADMIN)
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController
{
    private final IQueueService queueService;

    @PostMapping("queue/posts")
    public ResponseEntity<Boolean> createPostQueueRequests()
    {
        queueService.sendPostRequestForAllItems();

        return ResponseEntity.ok(true);
    }
}
