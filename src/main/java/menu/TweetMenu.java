package menu;

import entity.Tweet;
import entity.User;
import service.TweetService;
import util.ApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class TweetMenu {
    TweetService tweetService;


    public TweetMenu(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    public void showTweetMenu(User token) throws SQLException {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Twitter Menu : 
                    1.Add tweet
                    2.Delete tweet 
                    3.Get All Tweet
                    4.Exit
                    """);
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1 -> {
                    System.out.println("Enter the tweet context: ");
                    String tweetText = input.nextLine();
                    Tweet tweet = new Tweet();
                    tweet.setText(tweetText);
                    tweet.setUser(token);
                    tweetService.addTweet(tweet);
                }
                case 2 -> {
                    System.out.println("Enter the context of tweet you wish to delete");
                    String text = input.nextLine();
                   Tweet tweet1 = tweetService.findTweetByText(token, text);
                    tweetService.delete(tweet1);
                }
                case 3 ->
                    //   Arrays.toString(ApplicationContext.getInstance().getTweetService().getAllTweetsByUserId(token));
                        System.out.println(Arrays.toString(tweetService.getAllTweetsByUserId(token)));
                case 4 -> {
                    return;
                }
                default -> System.out.println("Wrong");
            }
        }
    }

}
