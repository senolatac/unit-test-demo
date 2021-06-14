package com.example.worker.service;

import com.example.common.dto.QueueRequestPostDto;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:18
 */
public interface IRequestHandlerService
{
    void executeMessage(QueueRequestPostDto request);
}
