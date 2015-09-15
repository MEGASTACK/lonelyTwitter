package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by slevinsk on 9/14/15.
 */
public class NormalTweet extends Tweet {

    public NormalTweet(String text, Date date) {
        super(text, date);
    }

    public NormalTweet(String text) {
        super(text);
    }
    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
