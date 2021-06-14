package com.example.worker.queue;

import com.example.common.dto.QueueRequestPostDto;
import com.example.worker.service.IRequestHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestQueueHandler
{
    private final AmqpTemplate postRequestProblemQueueTemplate;

    private final IRequestHandlerService requestHandlerService;

    @RabbitListener(queues = "${messaging.queue.post-request}", containerFactory = "postRequestQueueListener")
    public void handlePostMessage(QueueRequestPostDto request)
    {
        try
        {
            requestHandlerService.executeMessage(request);
        }
        catch (Exception e)
        {
            log.error("Could not handle request for postId: {}", request.getPostId(), e);

            postRequestProblemQueueTemplate.convertAndSend(request);
        }
    }
}
