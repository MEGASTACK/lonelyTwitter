package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

    private LonelyTwitterActivity activity = this;
	private EditText bodyText;
    private ListView oldTweetsList;
	private ArrayAdapter<Tweet> adapter;
    private Button saveButton;

    public ListView getOldTweetsList() {
        return oldTweetsList;
    }

    public EditText getBodyText() {
        return bodyText;
    }
    public Button getSaveButton() {
        return saveButton;
    }

    public ArrayList<Tweet> getTweets() {
        return ApplicationState.tweets;
    }
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
        saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

        oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, EditTweetActivity.class);

                Tweet t = (Tweet) parent.getAdapter().getItem(position);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

		saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                ApplicationState.tweets.add(new NormalTweet(text));
                saveInFile(); // model
                // dataObject.saveInFile() //controller
                adapter.notifyDataSetChanged(); // view
            }
        });
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, ApplicationState.tweets);
		oldTweetsList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(ApplicationState.FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			// https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
			Type arraylistType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
			ApplicationState.tweets = gson.fromJson(in, arraylistType);

		} catch (FileNotFoundException e) {
			ApplicationState.tweets = new ArrayList<Tweet>();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(ApplicationState.FILENAME, 0);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			Gson gson = new Gson();
			gson.toJson(ApplicationState.tweets, out);
			out.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
