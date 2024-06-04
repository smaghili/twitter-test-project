package menu;

import entity.User;
import service.TweetService;
import service.UserService;

import java.sql.SQLException;

public class MainMenu {
    private User token;
    private final LoginMenu loginMenu;
    private final TweetMenu tweetMenu;

    public MainMenu(LoginMenu loginMenu, TweetMenu tweetMenu) {
        this.loginMenu = loginMenu;
        this.tweetMenu = tweetMenu;
    }

    public void loginMenu() throws SQLException {
        loginMenu.showMenu();
        if (loginMenu.getToken() != null) tweetMenu();
    }

    public void tweetMenu() throws SQLException {
        tweetMenu.showTweetMenu(token);
        token = null;
        loginMenu();
    }
}
