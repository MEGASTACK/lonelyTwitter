package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by slevinsk on 9/14/15.
 */
public class ImportantTweet extends Tweet {
    public ImportantTweet(String tweet, Date date) {
        super(tweet, date);
    }

    public ImportantTweet(String text){
        super(text);
        setText(text);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.TRUE;
    }
}
