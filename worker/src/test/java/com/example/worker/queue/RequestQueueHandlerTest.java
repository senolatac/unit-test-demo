package com.example.worker.queue;

import com.example.common.dto.QueueRequestPostDto;
import com.example.worker.service.IRequestHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:27
 */
@ExtendWith(SpringExtension.class)
class RequestQueueHandlerTest
{
    @Autowired
    private RequestQueueHandler requestQueueHandler;

    @MockBean
    private IRequestHandlerService service;

    @MockBean(name = "postRequestProblemQueueTemplate")
    private AmqpTemplate postRequestProblemQueueTemplate;

    @Test
    public void handlePostMessage_success()
    {
        QueueRequestPostDto dto = new QueueRequestPostDto(1L);

        requestQueueHandler.handlePostMessage(dto);

        verify(service).executeMessage(dto);
    }

    @Test
    public void handlePostMessage_fail()
    {
        QueueRequestPostDto dto = new QueueRequestPostDto(1L);

        doThrow(RuntimeException.class).when(service).executeMessage(dto);

        requestQueueHandler.handlePostMessage(dto);

        verify(postRequestProblemQueueTemplate).convertAndSend(dto);
    }
}
