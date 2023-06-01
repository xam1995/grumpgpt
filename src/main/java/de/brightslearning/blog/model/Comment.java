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
    @Column(name = "blog_id")
    private Integer blog_id;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public Comment(Integer id, String content, Integer author_id, Integer blog_id, LocalDateTime timestamp) {
        this.id = id;
        this.content = content;
        this.author_id = author_id;
        this.blog_id = blog_id;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public Integer getBlog_id() {
        return blog_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
