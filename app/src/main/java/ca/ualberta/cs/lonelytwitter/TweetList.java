package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by slevinsk on 9/28/15.
 */
public class TweetList {
    private ArrayList<Tweet> tweets;

    public TweetList() {
        tweets = new ArrayList<Tweet>();
    }

    public void add(Tweet tweet) {
        if (!contains(tweet)){
            tweets.add(tweet);
        } else {
            throw new IllegalArgumentException("Dupe!");
        }
    }

    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }

    public boolean contains(Tweet tweet) {
        return tweets.contains(tweet);
    }

    public int getCount() {
        return tweets.size();
    }

    public List<Tweet> getTweets() {
        List<Tweet> t = new ArrayList<Tweet>(tweets);

        Collections.sort(t);
        return t;
    }

    public boolean hasTweet(Tweet tweet2) {
        return tweets.contains(tweet2);
    }

    public void remove(Tweet t) {
        tweets.remove(t);
    }
}
