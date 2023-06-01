package de.brightslearning.blog.persistance;

import de.brightslearning.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository <Comment, Integer> {
}
