package de.brightslearning.blog.service;


import de.brightslearning.blog.model.User;
import de.brightslearning.blog.persistance.UserDAO;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userdao;

    public UserService(UserDAO userdao) {
        this.userdao = userdao;
    }

    public List<User> findAll() {
        return this.userdao.findAll();
    }

    public void save(User user) {
        this.userdao.save(user);
    }

    public void delete(User user) {
        this.userdao.delete(user);
    }

    public User getByUsername(String username) {
        return this.userdao.findByUsername(username);
    }

    public Optional<User> getByUsernameAndPassword(String username, String password) {
        return this.userdao.getByUsernameAndPassword(username, password);
    }

    public User getByMail(String mail) {
        return this.userdao.getByEmail(mail);
    }

    public Optional<User> getUserById(Integer id) {
        return this.userdao.findById(id);
    }

    public List<User> findAdmins() {
        return this.userdao.findAll();
    }

    public List<User> findNonAdmins() {
        return this.userdao.findAll();
    }

    public void makeAdmin(String username) {
        User user = getByUsername(username);
        user.setAdmin(true);
        this.userdao.save(user);
    }

    public void removeAdmin(String username) {
        User user = getByUsername(username);
        user.setAdmin(false);
        this.userdao.save(user);
    }
}
