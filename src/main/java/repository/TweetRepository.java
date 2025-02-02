package repository;

import entity.Tweet;
import entity.User;

import java.sql.SQLException;

public interface TweetRepository {
    Tweet addTweet(Tweet tweet) throws SQLException;
    Tweet[]  getAllTweetsByUserId(User user) throws SQLException;

    void delete(Tweet tweet) throws SQLException;

    Tweet findTweetByText(User user,String tweetText) throws SQLException;
}
