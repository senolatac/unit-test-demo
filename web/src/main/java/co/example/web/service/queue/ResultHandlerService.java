package co.example.web.service.queue;

import co.example.web.model.Post;
import co.example.web.service.IPostService;
import com.example.common.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:10
 */
@Service
@RequiredArgsConstructor
public class ResultHandlerService implements IResultHandlerService
{
    private final IPostService postService;

    @Override
    public void executeMessage(PostDto postDto)
    {
        postService.savePost(convertFrom(postDto));
    }

    private Post convertFrom(PostDto postDto)
    {
        return Post.builder()
                .id(postDto.getId())
                .userId(postDto.getUserId())
                .title(postDto.getTitle())
                .body(postDto.getBody())
                .build();
    }
}
