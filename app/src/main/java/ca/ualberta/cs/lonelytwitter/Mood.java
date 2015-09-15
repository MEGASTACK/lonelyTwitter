package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by slevinsk on 9/14/15.
 */
public abstract class Mood {
    private Date date;

    public Mood(Date date){
        setDate(date);
    }

    public Mood(){
        this(new Date());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract String format();

}
