package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListDisplayActivity extends Activity {

    private ListView tweetsList;
    private ArrayAdapter<Tweet> adapter;
    private ArrayList<Tweet> tweets;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileName = (String) getIntent().getExtras().get("filename");
        setContentView(R.layout.activity_list_display);

        tweetsList = (ListView) findViewById(R.id.lab3TweetView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();

        adapter = new ArrayAdapter<Tweet>(this,
                R.layout.list_item, tweets);
        tweetsList.setAdapter(adapter);

    }

    private void loadFromFile() {
        Gson json = new Gson();

        Type t = new TypeToken<ArrayList<NormalTweet>> () {}.getType();

        try {

            FileInputStream fis = openFileInput(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            tweets = json.fromJson(in, t);




        } catch (FileNotFoundException e) {
            tweets = new ArrayList<Tweet>();
        }


    }
}
