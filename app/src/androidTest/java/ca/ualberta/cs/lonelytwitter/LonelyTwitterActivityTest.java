package ca.ualberta.cs.lonelytwitter;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private static final long TIMEOUT_IN_MS = 1000;
    private EditText bodyText;
    private Button saveButton;
    private String tweetText;
    private EditText editTweetText;
    private String newText;
    private Button saveTweetButton;
    private ListView oldTweetList;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testEditTweet() {
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        activity.getTweets().clear();

        tweetText = "Hello!";
        bodyText = activity.getBodyText();
        saveButton = activity.getSaveButton();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText(tweetText);
            }
        });

        getInstrumentation().waitForIdleSync();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();


        //get he list of weets from the view
        oldTweetList = activity.getOldTweetsList();
        Tweet newestTweet = (Tweet) oldTweetList.getItemAtPosition(0);
        assertEquals(tweetText, newestTweet.getText());



        // code from https://developer.android.com/training/activity-testing/activity-functional-testing.html
        // Date: 2015-10-16
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);



        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetList.getChildAt(0);
                oldTweetList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());




        //test that the tweet editor starts up with the correct tweet

        editTweetText = receiverActivity.getEditBox();

        assertEquals(editTweetText.getText().toString(), tweetText);

        //test that we can edit a tweet

        newText = "I changed the tweet!";

        receiverActivity.runOnUiThread(new Runnable() {
            public void run() {
                editTweetText.setText(newText);
            }
        });
        getInstrumentation().waitForIdleSync();

        assertEquals(newText, editTweetText.getText().toString());

        // test that we can push a save button for the edited tweet

        saveTweetButton = receiverActivity.getSaveButton();
        receiverActivity.runOnUiThread(new Runnable() {
            public void run() {
                saveTweetButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        // test that the modified tweet was saved
        assertEquals(newText, ApplicationState.getTweets().get(0).getText());


        //test that the modified tweet is in the tweet list
        oldTweetList = activity.getOldTweetsList();

        Tweet tweet2 = (Tweet) oldTweetList.getItemAtPosition(0);
        assertEquals(newText, tweet2.getText());


        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        receiverActivity.finish(); //close activity test is good.


    }
}