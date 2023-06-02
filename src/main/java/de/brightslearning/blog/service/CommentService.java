package de.brightslearning.blog.service;

import de.brightslearning.blog.model.Comment;
import de.brightslearning.blog.persistance.CommentDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO){
        this.commentDAO = commentDAO;
    }
    public List<Comment> findAllForOneBlog(Integer blog_id) {
        return commentDAO.findCommentsByBlogId(blog_id);
    }
    public Comment findCommentById(Integer commentID) {
        return commentDAO.findById(commentID).orElse(null);
    }

    public void save(Comment comment) {
        this.commentDAO.save(comment);
    }

    public void delete(Integer commentID) {
        commentDAO.deleteById(commentID);
    }
}
