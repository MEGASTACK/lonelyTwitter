package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.Date;

/**
 * Created by slevinsk on 9/28/15.
 */
public class TweetTest extends ActivityInstrumentationTestCase2 {

    public TweetTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testEquals(){
        Tweet t = new NormalTweet("yoloswag", new Date(124));
        Tweet t2 = new NormalTweet("yoloswag", new Date(124));

        assertTrue(t.equals(t2));
    }
}