package co.example.web.repository;

import co.example.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:05
 */
public interface IPostRepository extends JpaRepository<Post, Long>
{
    List<Post> findAllByUserId(Long userId);

    @Query("select distinct p.userId from Post p")
    List<Long> findDistinctUsers();
}
