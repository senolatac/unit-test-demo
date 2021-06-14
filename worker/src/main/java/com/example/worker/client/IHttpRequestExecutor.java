package com.example.worker.client;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:17
 */
public interface IHttpRequestExecutor
{
    <T> T executeGetRequest(String url, Class<T> resultClass);
}
