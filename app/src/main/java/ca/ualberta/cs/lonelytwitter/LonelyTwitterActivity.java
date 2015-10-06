package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity implements MyObserver {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private ArrayAdapter<Tweet> adapter;


    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); //view

		bodyText = (EditText) findViewById(R.id.body); //view
		Button saveButton = (Button) findViewById(R.id.save); //view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); //view

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK); //controller
				String text = bodyText.getText().toString(); //move to controller
				tweets.add(new NormalTweet(text));	//move to controller
                saveInFile();					 // move to model
				adapter.notifyDataSetChanged();  // view
			}
		}); //controller
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
        loadFromFile(); //model

        adapter = new ArrayAdapter<Tweet>(this,
                R.layout.list_item, tweets); //view
        oldTweetsList.setAdapter(adapter); //view

	}

	/**
	 * Put me in the model
	 */
	private void loadFromFile(){


        Type type = new TypeToken<ArrayList<NormalTweet>>() {}.getType();

        try {
            Gson json = new Gson();
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            tweets = json.fromJson(in, type);

            if (tweets == null) {
                tweets = new ArrayList<Tweet>();
            }

        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>();
        }
	}

	/**
	 * put me in the model
	 */
	private void saveInFile() {

        Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            gson.toJson(tweets, outputStreamWriter);
            outputStreamWriter.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void onTweetDisplayButtonSelected(View view) { //controller
        Intent intent = new Intent(this, ListDisplayActivity.class);
        intent.putExtra("filename", FILENAME);
        startActivity(intent);
    }

	public void myNotify(MyObservable observable) {
		adapter.notifyDataSetChanged(); //view
	}
}