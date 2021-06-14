package com.example.worker.service;

import com.example.common.dto.PostDto;
import com.example.common.dto.QueueRequestPostDto;
import com.example.worker.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:18
 */
@Service
@RequiredArgsConstructor
public class RequestHandlerService implements IRequestHandlerService
{
    private final AmqpTemplate postResultQueueTemplate;

    private final ICrawlerClient crawlerClient;

    @Override
    public void executeMessage(QueueRequestPostDto request)
    {
        PostDto post = crawlerClient.fetchPost(request.getPostId());

        postResultQueueTemplate.convertAndSend(post);
    }
}
