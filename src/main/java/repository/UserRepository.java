package repository;

import entity.User;

import java.sql.SQLException;

public interface UserRepository {
    User addUser(User user) throws SQLException;

    User login(User user) throws SQLException;
}
