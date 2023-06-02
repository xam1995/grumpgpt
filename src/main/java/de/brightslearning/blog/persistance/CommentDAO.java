package de.brightslearning.blog.persistance;

import de.brightslearning.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentDAO extends CrudRepository<Comment, Integer> {
    List<Comment> findCommentsByBlogId(Integer blog_id);
}
