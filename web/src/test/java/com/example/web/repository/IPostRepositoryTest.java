package com.example.web.repository;

import com.example.web.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:47
 */
@DataJpaTest//only loads the JPA part of a Spring Boot application
@Sql("/data/posts.sql")
class IPostRepositoryTest
{
    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private TestEntityManager entityManager; //To write custom external queries...

    @Test
    public void should_find_initial_posts()
    {
        List<Post> posts = postRepository.findAll();

        assertThat(posts).hasSize(4);
    }

    @Test
    public void should_find_posts_of_user()
    {
        List<Post> posts = postRepository.findAllByUserId(1L);

        assertThat(posts).hasSize(2);
    }

    @Test
    public void should_store_a_post()
    {
        Post post = postRepository.save(new Post(100L, 100L, "Title-100", "Body-100"));

        assertThat(post).hasFieldOrPropertyWithValue("title", "Title-100");
    }

    @Test
    public void should_find_all_posts() {
        Post post = new Post(100L, 100L, "Title-100", "Body-100");

        entityManager.persist(post);

        List<Post> posts = postRepository.findAll();

        assertThat(posts).hasSize(5);
    }
}
