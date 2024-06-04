package service.impl;

import entity.Tweet;
import entity.User;
import repository.TweetRepository;
import repository.impl.TweetRepositoryImpl;
import service.TweetService;

import java.sql.SQLException;

public class TweetServiceImpl implements TweetService {
    private TweetRepository tweetRepository;

    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet addTweet(Tweet tweet) throws SQLException {
        return tweetRepository.addTweet(tweet);
    }

    @Override
    public Tweet[] getAllTweetsByUserId(User user) throws SQLException {
        return tweetRepository.getAllTweetsByUserId(user);
    }

    @Override
    public void delete(Tweet tweet) throws SQLException {
        tweetRepository.delete(tweet);
    }

    @Override
    public Tweet findTweetByText(User user, String tweetText) throws SQLException {
        return tweetRepository.findTweetByText(user, tweetText);
    }
}
