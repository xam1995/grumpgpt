package de.brightslearning.blog.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BlogEntry {


    private Integer id;
    private String title;
    private String content;
    private Integer author_id;
    private LocalDateTime timestamp;

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
