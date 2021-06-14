package com.example.worker.client;

import com.example.common.dto.PostDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sa
 * @date 14.06.2021
 * @time 12:57
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Disabled
class CrawlerClientIntegrationTest
{
    @Autowired
    private ICrawlerClient crawlerClient;

    @Test
    public void fetchPost_success()
    {
        PostDto dto = crawlerClient.fetchPost(1L);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getUserId()).isEqualTo(1L);
    }
}
