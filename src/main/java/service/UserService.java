package service;

import entity.User;

import java.sql.SQLException;

public interface UserService {
    User addUser(User user) throws SQLException;
    User login(User user) throws SQLException;
}
