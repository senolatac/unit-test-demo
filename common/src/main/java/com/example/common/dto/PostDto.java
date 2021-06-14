package com.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author sa
 * @date 14.06.2021
 * @time 10:44
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto
{
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
