package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;

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

    public void testHas(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("I am a cool tweet", new Date(123));
        Tweet tweet2 = new NormalTweet("I am a cool tweet", new Date(123));

        list.add(tweet);

        assertTrue(list.hasTweet(tweet2));


    }


    public void testGetCount(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("this is tweet #1");
        list.add(tweet);
        assertTrue(list.getCount() == 1);
        Tweet tweet2 = new NormalTweet("this is tweet #2");
        list.add(tweet2);
        assertTrue(list.getCount() == 2);
        list.delete(tweet);
        list.delete(tweet2);
        assertTrue(list.getCount() == 0);
    }

    public void testGetTweets(){
        TweetList list = new TweetList();

        Tweet t = new NormalTweet("chrono_second", new Date(12345));
        Tweet t2 = new NormalTweet("chrono_first", new Date(123));

        list.add(t);
        list.add(t2);

        List<Tweet> gotList = list.getTweets();

        //Check chronoloical sorting
        assertEquals(t2, gotList.get(0));
        assertEquals(t, gotList.get(1));

    }

    public void testRemoveTweet(){
        TweetList list = new TweetList();
        Tweet t = new NormalTweet("I will be deleted, eventually");
        list.add(t);

        assertEquals(1, list.getCount());
        assertTrue(list.contains(t));

        list.remove(t);

        assertEquals(0, list.getCount());
        assertFalse(list.contains(t));
    }

    public void testAddDupe(){
        TweetList list = new TweetList();
        Tweet t = new NormalTweet("I will be duplicated!");
        list.add(t);
        try {
            list.add(t);
            assertFalse("Exception should be thrown!", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Exception was thrown! awesome", true);
        }
    }
}