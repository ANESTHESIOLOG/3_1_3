package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;

import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.add(user);
    }

    @Override
    @Transactional
    public List<User> getList() {
        return userDAO.getList();
    }

    @Override
    @Transactional
    public User getUserAndRole(Integer id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userDAO.deleteUser(id);

    }

    @Override
    @Transactional
    public void editUser(User user) {
        userDAO.editUser(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userDAO.getUserAndRole(username);
        user.getRoles().size();
        return user;
    }
}
