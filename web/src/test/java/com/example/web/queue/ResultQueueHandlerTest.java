package com.example.web.queue;

import com.example.common.dto.PostDto;
import com.example.web.service.queue.IResultHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * @author sa
 * @date 14.06.2021
 * @time 12:24
 */
@ExtendWith(MockitoExtension.class)
class ResultQueueHandlerTest
{
    @InjectMocks
    private ResultQueueHandler resultQueueHandler;

    @Mock
    private IResultHandlerService service;

    @Mock(name = "postResultProblemQueueTemplate")
    private AmqpTemplate postResultProblemQueueTemplate;

    @Test
    public void handlePostMessage_success()
    {
        PostDto dto = new PostDto();

        resultQueueHandler.handlePostMessage(dto);

        verify(service).executeMessage(dto);
    }

    @Test
    public void handlePostMessage_fail()
    {
        PostDto dto = new PostDto();

        doThrow(RuntimeException.class).when(service).executeMessage(dto);

        resultQueueHandler.handlePostMessage(dto);

        verify(postResultProblemQueueTemplate).convertAndSend(dto);
    }
}
