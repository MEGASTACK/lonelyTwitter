package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observer;


/**
 * Created by slevinsk on 9/28/15.
 */
public class TweetList implements MyObservable, MyObserver {
    private ArrayList<Tweet> tweets;
    private ArrayList<MyObserver> observers = new ArrayList<MyObserver>();



    public TweetList() {
        tweets = new ArrayList<Tweet>();
    }

    public void add(Tweet tweet) {
        if (!contains(tweet)){
            tweets.add(tweet);
        } else {
            throw new IllegalArgumentException("Dupe!");
        }
        tweet.addObserver(this);
        notifyAllObservers();
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

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (MyObserver o: observers){
            o.myNotify(this);
        }
    }

    public void myNotify(MyObservable observable) {
        notifyAllObservers();
    }
}
