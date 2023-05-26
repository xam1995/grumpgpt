package de.brightslearning.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Comment {

    private Integer id;
    private String content;
    private Integer author_id;
    private Integer blog_id;
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author_id=" + author_id +
                ", blog_id=" + blog_id +
                ", timestamp=" + timestamp +
                '}';
    }
}
