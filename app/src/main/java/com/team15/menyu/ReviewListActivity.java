package com.team15.menyu;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ListView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReviewListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHelper db = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        Intent intent = getIntent();
        final String restaurantTitle_I = intent.getStringExtra("FOODNAME");
//        String restaurantTitle_I = "ALLAHU ACKBAR";
        TextView restaurant = (TextView) findViewById(R.id.foodName);
        restaurant.setText(restaurantTitle_I);

        ArrayList<Review> values = new ArrayList<Review>();
        Review temp = new Review();
        values.add(temp);

//        ArrayList<Food> values = db.getFood(restaurantTitle_I);
        ReviewOptionArrayAdapter adapter = new ReviewOptionArrayAdapter(this, values);
        setListAdapter(adapter);

        final ToggleButton upVote = (ToggleButton) findViewById(R.id.upVote);
        final ToggleButton downVote = (ToggleButton) findViewById(R.id.downVote);

        upVote.setText("Upvote");
        upVote.setTextOff("Upvote");
        upVote.setTextOn("Voted Up");
        upVote.setBackgroundColor(Color.LTGRAY);
        upVote.setChecked(false);
        upVote.setTextColor(Color.BLACK);//green color

        downVote.setText("Downvote");
        downVote.setTextOff("Downvote");
        downVote.setTextOn("Voted Down");
        downVote.setBackgroundColor(Color.LTGRAY);
        downVote.setChecked(false);
        downVote.setTextColor(Color.BLACK);//green color

        upVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upVote.isChecked()) {
                    upVote.setBackgroundColor(Color.rgb(22, 160, 133));//green color
                    upVote.setEnabled(false);
                    downVote.setEnabled(false);
                    //db.updateVotes(restaurantTitle_I, foodTitle_I, true, false);
                } else {
                    upVote.setBackgroundColor(Color.LTGRAY);
                    //helpful.setTextColor(Color.DKGRAY);
                }
            }
        });

        downVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downVote.isChecked()) {
                    downVote.setBackgroundColor(Color.rgb(22, 160, 133));//green color
                    upVote.setEnabled(false);
                    downVote.setEnabled(false);
                    //db.updateVotes(restaurantTitle_I, foodTitle_I, false, true);
                } else {
                    downVote.setBackgroundColor(Color.LTGRAY);
                    //helpful.setTextColor(Color.DKGRAY);
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogText();
            }
        });

    }


    private void dialogText(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Write your review:");

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        float sidedp = 20f;
        int sideMargin = (int) (metrics.density * sidedp + 0.5f);
        float topdp = 10f;
        int topMargin = (int) (metrics.density * topdp + 0.5f);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(sideMargin, topMargin, sideMargin, topMargin);

        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected;
        input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        input.setSingleLine(false);
        input.setCursorVisible(true);

        int maxLength = 320;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        input.setFilters(fArray);
        // input.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        builder.setView(input);

        layout.addView(input, params);

        builder.setView(layout);

        // Set up the buttons
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                String restaurantTitle_I = intent.getStringExtra("RESTAURANT");
                String userEmail_I = intent.getStringExtra("EMAIL");
                String foodTitle_I = intent.getStringExtra("FOODNAME");
                String m_Text = input.getText().toString();
                Review newbie = new Review(restaurantTitle_I, foodTitle_I, m_Text, userEmail_I);

                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
