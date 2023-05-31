package de.brightslearning.blog.service;


import de.brightslearning.blog.model.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class UserService {

    @PostConstruct
    private void setup(){
        User fakeUser = new User(
                1,
                "Stani",
                "staaani123",
                "stani@gmail.com",
                "12345678",
                false
        );

        User fakeAdmin = new User(
                2,
                "Steven",
                "steven456",
                "steven@gmail.com",
                "12345678",
                true
        );

        this.users.addAll(List.of(fakeUser, fakeAdmin));
    }

    private List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return this.users;
    }

    public void save(User user) {
        this.users.add(user);
    }

    public void delete(User user) {
        this.users.remove(user);
    }

    public User getByUsername(String username) {
        return this.users.stream()
                .filter(user -> user.getUsername()
                        .equals(username)).toList().get(0);
    }

    public User getByMail(String mail) {
        return this.users.stream()
                .filter(user -> user.getEmail()
                        .equals(mail)).toList().get(0);
    }

    public List<User> findAdmins() {
        return this.users.stream()
                .filter(User::isAdmin).toList();
    }

    public List<User> findNonAdmins() {
        return this.users.stream()
                .filter(user -> !user.isAdmin()).toList();
    }

    public void makeAdmin(String username){
        getByUsername(username).setAdmin(true);
    }

    public void removeAdmin(String username){
        getByUsername(username).setAdmin(false);
    }
}
