package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by slevinsk on 10/16/15.
 */
public class ApplicationState {

    static final String FILENAME = "file.sav"; // model

    public static ArrayList<Tweet> getTweets() {
        return tweets;
    }

    static ArrayList<Tweet> tweets = new ArrayList<Tweet>();
}
