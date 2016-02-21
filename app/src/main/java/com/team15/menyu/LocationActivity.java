package com.team15.menyu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity{
    private GoogleApiClient mGoogleApiClient;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private static final String LOG_TAG = "PlacesAPIActivity";
    private static List<String> possible_places = new ArrayList<String>();
    private static int possible_places_count = 0;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Intent intent = new Intent(this, Menyu.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        possible_places_count = getIntent().getExtras().getInt("PLACES_COUNT");
        possible_places = getIntent().getStringArrayListExtra("PLACES");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //Programmatically fill in menu order options
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float dp = 70f;
        int pixels = (int) (metrics.density * dp + 0.5f);
        float dpBorder = 2f;
        int pixelBorder = (int) (metrics.density * dpBorder + 0.5f);

        LinearLayout list = (LinearLayout) findViewById(R.id.scrollingListTEMP);

        int listItems = possible_places_count; //CHANGED

        String[] placesTitles = new String[listItems];//CHANGED
        placesTitles = possible_places.toArray(placesTitles);//CHANGED

        TextView restaurant = (TextView) findViewById(R.id.pick_location);
        restaurant.setText("Pick Your Location");

        for(int i = 0; i < listItems; i++) {
            RelativeLayout listBox = new RelativeLayout(this);
            listBox.setBackgroundColor(Color.rgb(236, 240, 241));//clouds color
            RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, //width?
                    pixels);                                  //height?
            listBox.setLayoutParams(rlp);

            // Defining the layout parameters of the TextView
            RelativeLayout.LayoutParams lpTitle = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            lpTitle.addRule(RelativeLayout.CENTER_IN_PARENT);

            // Setting the RelativeLayout as our content view
            //setContentView(listBox, rlp); //IDK WHAT THIS DOES BESIDES CAUSE APP TO CRASH

            TextView title = new TextView(this);
            title.setText(placesTitles[i]);
            title.setTextSize(20);
            title.setTextColor(Color.BLACK);
            title.setPadding(8, 8, 8, 4);
            title.setId(i + 1); //setID has to have positive Int as parameter
            title.setLayoutParams(lpTitle);
            listBox.addView(title);


            RelativeLayout border = new RelativeLayout(this);
            border.setBackgroundColor(Color.rgb(241, 196, 15));//pumpkin color
            RelativeLayout.LayoutParams rlpborder = new RelativeLayout.LayoutParams(
                    (RelativeLayout.LayoutParams.MATCH_PARENT), //width?
                    pixelBorder);//height?

            border.setLayoutParams(rlpborder);

            list.addView(border);

            list.addView(listBox);
            //Need to add buttons on side
        }
    }

}