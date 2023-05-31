package de.brightslearning.blog.service;

import de.brightslearning.blog.model.Comment;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class CommentService {

    private ArrayList<Comment> comments  = new ArrayList<>();




    @PostConstruct
    private void generateData(){
        this.comments.add(new Comment(1, "Erster!", 1, 1, LocalDateTime.now()));

    }

    public ArrayList<Comment> findAllForOneBlog(Integer blog_id){

        ArrayList<Comment> commentsForOneEntry = new ArrayList<>();
       for(Comment comment : this.comments){
           if(comment.getBlog_id().equals(blog_id)){
               commentsForOneEntry.add(comment);
           }
       }
       return commentsForOneEntry;
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
