package com.example.worker.service;

import com.example.common.dto.PostDto;
import com.example.common.dto.QueueRequestPostDto;
import com.example.worker.client.ICrawlerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author sa
 * @date 14.06.2021
 * @time 12:58
 */
@ExtendWith(MockitoExtension.class)
class RequestHandlerServiceTest
{
    @InjectMocks
    private RequestHandlerService requestHandlerService;

    @Mock
    private ICrawlerClient crawlerClient;

    @Mock
    private AmqpTemplate postResultQueueTemplate;

    @Test
    public void test_crawlAppForAllCountries_missingApp()
    {
        PostDto post = PostDto.builder()
                .userId(1L)
                .id(1L)
                .body("body-1")
                .title("title-1")
                .build();

        when(crawlerClient.fetchPost(eq(1L))).thenReturn(post);

        QueueRequestPostDto dto = new QueueRequestPostDto(1L);

        requestHandlerService.executeMessage(dto);

        verify(crawlerClient).fetchPost(1L);
        verify(postResultQueueTemplate).convertAndSend(post);
    }
}
