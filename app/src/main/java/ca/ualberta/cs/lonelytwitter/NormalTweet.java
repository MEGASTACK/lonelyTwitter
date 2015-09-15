package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by slevinsk on 9/14/15.
 */
public class NormalTweet extends Tweet implements Tweetable {

    public NormalTweet(String text, Date date) throws IOException {
        super(text, date);
    }

    public NormalTweet(String text) throws IOException {
        super(text);
    }
    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
