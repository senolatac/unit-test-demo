package co.example.web.queue;

import co.example.web.service.queue.IResultHandlerService;
import com.example.common.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResultQueueHandler
{
    private final AmqpTemplate postResultProblemQueueTemplate;

    private final IResultHandlerService resultHandlerService;

    @RabbitListener(queues = "${messaging.queue.post-result}", containerFactory = "postResultQueueListener")
    public void handlePostMessage(PostDto result)
    {
        try
        {
            resultHandlerService.executeMessage(result);
        }
        catch (Exception e)
        {
            log.error("Could not handle result for postId: {}", result.getId(), e);

            postResultProblemQueueTemplate.convertAndSend(result);
        }
    }
}
