package co.example.web.service.queue;

import com.example.common.dto.PostDto;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:10
 */
public interface IResultHandlerService
{
    void executeMessage(PostDto postDto);
}
