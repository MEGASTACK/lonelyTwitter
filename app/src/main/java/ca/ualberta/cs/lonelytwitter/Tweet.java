package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by slevinsk on 9/14/15.
 */
public abstract class Tweet {
    private String text;
    private Date date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text.length() <=140){
            this.text = text;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();

    public Tweet(String text, Date date) {
        setText(text);
        setDate(date);
    }

    public Tweet(String text) {
        this(text, new Date());
    }
}
