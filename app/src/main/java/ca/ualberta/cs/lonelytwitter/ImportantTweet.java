package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by slevinsk on 9/14/15.
 */
public class ImportantTweet extends Tweet implements Tweetable {
    public ImportantTweet(String tweet, Date date) throws IOException {
        super(tweet, date);
    }

    public ImportantTweet(String text) throws IOException {
        super(text);
        setText(text);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.TRUE;
    }

}
