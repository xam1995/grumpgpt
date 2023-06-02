package de.brightslearning.blog.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class BlogEntry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "author_id")
    private Integer author_id;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public BlogEntry(Integer id, String title, String content, Integer author_id, LocalDateTime timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "BlogEntry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author_id=" + author_id +
                ", timestamp=" + timestamp +
                '}';
    }
}
