package menu;

import entity.User;
import service.UserService;
import util.ApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginMenu {
    private User token = null;
    private UserService userService;


    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    public void setToken(User token) {
        this.token = token;
    }

    public void showMenu() throws SQLException {
        Scanner input = new Scanner(System.in);
        boolean condition = true;
        while (condition) {
            System.out.println("""
                    Twitter Menu : 
                    1.Login
                    2.Sign up 
                    3.Exit
                    """);
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Enter username: ");
                    String username = input.nextLine();
                    System.out.println("Enter password: ");
                    String password = input.nextLine();
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    token = userService.login(user);
                    if (token.getId() == null) {
                        System.out.println("User or password is incorrect");
                    } else {
                        return;
                    }

                    break;
                case 2:
                    System.out.println("Enter full name: ");
                    String fullName = input.nextLine();
                    System.out.println("Enter username: ");
                    username = input.next();
                    System.out.println("Enter password: ");
                    password = input.next();
                    User user1 = new User(fullName, username, password);
                    token= userService.addUser(user1);
                    return;
                case 3:
                    condition = false;
                default:
                    System.out.println("Wrong option!");
            }
        }
        input.close();
    }

    public User getToken() {
        return token;
    }

}
