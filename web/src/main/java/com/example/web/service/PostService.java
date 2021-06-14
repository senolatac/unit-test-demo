package com.example.web.service;

import com.example.web.model.Post;
import com.example.web.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:07
 */
@Service
@RequiredArgsConstructor
public class PostService implements IPostService
{
    private final IPostRepository postRepository;

    @Override
    public void savePost(Post post)
    {
        postRepository.save(post);
    }

    @Override
    public List<Post> findPosts(Pageable pageable)
    {
        return postRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Post> findAllPostsOfUser(Long userId)
    {
        return postRepository.findAllByUserId(userId);
    }

    @Override
    public List<Long> findDistinctUsers()
    {
        return postRepository.findDistinctUsers();
    }

    @Override
    public void deletePost(Long postId)
    {
        postRepository.deleteById(postId);
    }
}
