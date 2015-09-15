package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by slevinsk on 9/14/15.
 */
public abstract class Tweet {
    private String text;
    private Date date;
    private ArrayList<Mood> moods;

    public ArrayList<Mood> getMoods() {
        return moods;
    }

    public void setMoods(ArrayList<Mood> moods) {
        this.moods = moods;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws IOException {
        if (text.length() <=140){
            this.text = text;
        } else {
            throw new IOException("Tweet was too long!");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();

    public Tweet(String text, Date date) throws IOException {
        setText(text);
        setDate(date);
    }

    public Tweet(String text) throws IOException {
        this(text, new Date());
    }
}
