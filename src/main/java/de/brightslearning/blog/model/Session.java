package de.brightslearning.blog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Session {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private User user;
    private Instant expiresAt;

    public Session(User user, Instant expiresAt) {
        this.user = user;
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", user=" + user.getUsername() +
                ", expiresAt=" + expiresAt +
                '}';
    }
}