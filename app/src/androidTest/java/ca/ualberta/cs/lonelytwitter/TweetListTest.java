package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by slevinsk on 9/28/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testAddTweet(){
        TweetList list = new TweetList();

        Tweet tweet = new NormalTweet("yolo swag");

        list.add(tweet);
    }

    public void testDeleteTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("swagalicious");
        list.add(tweet);

        list.delete(tweet);
        assertFalse(list.contains(tweet));
    }

    public void testContains(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("lololol");
        list.add(tweet);
        assertTrue(list.contains(tweet));
    }

}