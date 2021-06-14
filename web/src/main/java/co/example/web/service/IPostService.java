package co.example.web.service;

import co.example.web.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:07
 */
public interface IPostService
{
    void savePost(Post post);

    List<Post> findPosts(Pageable pageable);

    List<Post> findAllPostsOfUser(Long userId);

    List<Long> findDistinctUsers();

    void deletePost(Long postId);
}
