package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by joshua2 on 9/16/15.
 */
public abstract class Tweet extends Object implements Tweetable, Comparable<Tweet> {
    private String text;
    protected Date date;

    public Tweet(String tweet, Date date) throws TweetTooLongException {
        this.setText(tweet);
        this.date = date;
    }

    public Tweet(String tweet) throws TweetTooLongException {
        this.setText(tweet);
        this.date = new Date();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws TweetTooLongException {
        if (text.length() <= 140) {
            this.text = text;
        } else {
            throw new TweetTooLongException();
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date.toString() + " | " + getText();
    }
    public abstract Boolean isImportant();

    public int compareTo(Tweet another) {
        return getDate().compareTo(another.getDate());
    }

    @Override
    public boolean equals(Object o) {

        if (this.getClass() != o.getClass()){
            return false;
        }
        Tweet other = (Tweet) o;

        return getDate().equals(other.getDate()) && getText().equals(other.getText());
    }
}
