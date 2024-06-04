package repository.impl;

import entity.Tweet;
import entity.User;
import repository.TweetRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TweetRepositoryImpl implements TweetRepository {
    private Connection connection;

    public TweetRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Tweet addTweet(Tweet tweet) throws SQLException {
        String query = "INSERT INTO tweet (tweet_text, user_id) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, tweet.getText());
        preparedStatement.setLong(2, tweet.getUser().getId());
        if (preparedStatement.executeUpdate() > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                tweet.setId(generatedKeys.getLong("id"));
            }
        }
        return tweet;
    }

    @Override
    public Tweet[] getAllTweetsByUserId(User user) throws SQLException {
        int count = 0;
        String query = "SELECT * FROM tweet WHERE user_id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        int size = resultSet.getFetchSize();

        Tweet[] tweets = new Tweet[size];
        int i = 0;
        while (resultSet.next()) {
            Tweet tweet = new Tweet();
            tweet.setId(resultSet.getLong("id"));
            tweet.setText(resultSet.getString("tweet_text"));
            User user1 = new User();
            user1.setId(resultSet.getLong("user_id"));
            tweet.setUser(user1);
            tweets[i] = tweet;
            i++;
        }
        return tweets;
    }

    @Override
    public void delete(Tweet tweet) throws SQLException {
        String query = "delete from tweet where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, tweet.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public Tweet findTweetByText(User user,String tweetText) throws SQLException {
        Tweet[] tweets = getAllTweetsByUserId(user);
        for(int i = 0; i < tweets.length; i++){
            if(tweets[i].getText().contains(tweetText)){
                return tweets[i];
            }
        }
        return null;

    }
}
//        String query = "SELECT * FROM tweet WHERE tweet_text LIKE %?%";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, tweetText);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        Tweet tweet = new Tweet();
//        User user = new User();
//        while(resultSet.next()){
//            tweet.setId(resultSet.getLong("id"));
//            tweet.setText(resultSet.getString("tweet_text"));
//            user.setId(resultSet.getLong("user_id"));
//            tweet.setUser(user);
//        }
//        return tweet;