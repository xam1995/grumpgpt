package de.brightslearning.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Login {
    private String username;

    private String password;
}
