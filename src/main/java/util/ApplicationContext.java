package util;

import com.sun.tools.javac.Main;
import config.DatabaseConnection;
import menu.LoginMenu;
import menu.MainMenu;
import menu.TweetMenu;
import repository.TweetRepository;
import repository.UserRepository;
import repository.impl.TweetRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.TweetService;
import service.UserService;
import service.impl.TweetServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationContext {
    private static final ApplicationContext instance = new ApplicationContext();
    private static UserService userService;
    private static TweetService tweetService;
    private static TweetMenu tweetMenu;
    private static LoginMenu loginMenu;

    static {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            UserRepository userRepository = new UserRepositoryImpl(connection);
            TweetRepository tweetRepository = new TweetRepositoryImpl(connection);
            userService = new UserServiceImpl(userRepository);
            tweetService = new TweetServiceImpl(tweetRepository);
            tweetMenu = new TweetMenu(tweetService);
            loginMenu = new LoginMenu(userService);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ApplicationContext() {
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        ApplicationContext.userService = userService;
    }

    public TweetService getTweetService() {
        return tweetService;
    }

    public void setTweetService(TweetService tweetService) {
        ApplicationContext.tweetService = tweetService;
    }

    public TweetMenu getTweetMenu() {
        return tweetMenu;
    }

    public void setTweetMenu(TweetMenu tweetMenu) {
        ApplicationContext.tweetMenu = tweetMenu;
    }

    public LoginMenu getLoginMenu() {
        return loginMenu;
    }

    public void setLoginMenu(LoginMenu loginMenu) {
        ApplicationContext.loginMenu = loginMenu;
    }

    public static ApplicationContext getInstance() {
        return instance;
    }
}
