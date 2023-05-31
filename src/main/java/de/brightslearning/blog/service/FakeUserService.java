package de.brightslearning.blog.service;

import de.brightslearning.blog.model.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class FakeUserService {
    private List<User> fakeUserList;

    private void setup() {

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

        this.fakeUserList.addAll(List.of(fakeUser, fakeAdmin));
    }

    public FakeUserService() {
        this.fakeUserList = new ArrayList<>();
        setup();
    }

    public void add(User user) {
        fakeUserList.add(user);
    }

    public Optional<User> getById(Integer id) {
        return this.fakeUserList.stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    public Optional<User> getByUsernameAndPassword(String username, String password) {
        return this.fakeUserList.stream()
                .filter(user ->
                        user.getUsername().equalsIgnoreCase(username)
                                && user.getPassword().equals(password)).findFirst();
    }


}
