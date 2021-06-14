package com.example.worker.client;

import com.example.common.dto.PostDto;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:16
 */
public interface ICrawlerClient
{
    PostDto fetchPost(Long postId);
}
