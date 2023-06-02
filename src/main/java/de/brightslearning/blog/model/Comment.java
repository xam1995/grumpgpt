package de.brightslearning.blog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "content")
    private String content;
    @Column(name = "author_id")
    private Integer author_id;
    @Column(name = "blogId")
    private Integer blogId;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public Comment(Integer id, String content, Integer author_id, Integer blog_id, LocalDateTime timestamp) {
        this.id = id;
        this.content = content;
        this.author_id = author_id;
        this.blogId = blog_id;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author_id=" + author_id +
                ", blog_id=" + blogId +
                ", timestamp=" + timestamp +
                '}';
    }
}
