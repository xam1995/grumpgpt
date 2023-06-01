package de.brightslearning.blog.service;

import de.brightslearning.blog.model.Comment;
import jakarta.annotation.PostConstruct;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CommentService {

    private ArrayList<Comment> comments  = new ArrayList<>();




    @PostConstruct
    private void generateData(){
        this.comments.add(new Comment(1, "Erster!", 1, 1, LocalDateTime.now()));


    }

    public List<Comment> findAllForOneBlog(Integer blog_id){

      return  this.comments.stream().filter(comment -> comment.getBlog_id().equals(blog_id)).toList();

    }


    public Comment findCommentById(Integer commentID){

    return this.comments.stream().filter(comment -> comment.getId() == commentID).findFirst().orElse(null);

    }

    public void save(Comment comment) {
        this.comments.add(comment);
    }

    public void delete(Integer commentID){

        for(Comment comment : this.comments){
            if(comment.getId().equals(commentID)){
                this.comments.remove(comment);
            }
        }
    }
}
