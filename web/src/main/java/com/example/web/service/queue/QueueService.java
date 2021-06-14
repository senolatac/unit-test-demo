package com.example.web.service.queue;

import com.example.web.service.IPostService;
import com.example.common.dto.QueueRequestPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:09
 */
@Service
@RequiredArgsConstructor
public class QueueService implements IQueueService
{
    private final AmqpTemplate postRequestQueueTemplate;

    private final IPostService postService;

    @Override
    public void sendPostRequestForAllItems()
    {
        LongStream.rangeClosed(1, 100)
                .mapToObj(QueueRequestPostDto::new)
                .forEach(postRequestQueueTemplate::convertAndSend);
    }
}
