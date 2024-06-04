import menu.MainMenu;
import util.ApplicationContext;

import java.sql.SQLException;

public class TwitterApplication {
    public static void main(String[] args) throws SQLException {
        MainMenu menu = new MainMenu(
                ApplicationContext.getInstance().getLoginMenu(),
                ApplicationContext.getInstance().getTweetMenu());
        menu.loginMenu();
    }
}
