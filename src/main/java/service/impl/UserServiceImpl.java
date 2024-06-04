package service.impl;

import entity.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User login(User user) throws SQLException {
        return userRepository.login(user);
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) throws SQLException {
        return userRepository.addUser(user);
    }
}
