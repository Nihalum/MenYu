package com.team15.menyu;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class FoodListActivity extends ListActivity {

    private String foodName = "";

    private void dialogText(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name of food:");

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float sidedp = 20f;
        int sideMargin = (int) (metrics.density * sidedp + 0.5f);
        float topdp = 10f;
        int topMargin = (int) (metrics.density * topdp + 0.5f);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(sideMargin, topMargin, sideMargin, topMargin);

// Set up the input
        final EditText input = new EditText(this);

// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        input.setSingleLine(false);

        int maxLength = 100;
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
                foodName = input.getText().toString();
                //ADD FOOD ITEM (foodName) + RESTAURANT NAME (restaurantTitle_I) TO DB

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHelper db = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        Intent intent = getIntent();
        String restaurantTitle_I = intent.getStringExtra("RESTAURANT");
        String userEmail_I = intent.getStringExtra("EMAIL"); //ADDED USEREMAIL HERE
        TextView restaurant = (TextView) findViewById(R.id.restaurantTitle);
        restaurant.setText(restaurantTitle_I);

        ArrayList<FoodItem> values = new ArrayList<FoodItem>();
        FoodItem temp = new FoodItem();
        values.add(temp);

//        ArrayList<Food> values = db.getFood(restaurantTitle_I);
        MenuOptionArrayAdapter adapter = new MenuOptionArrayAdapter(this, values,
                restaurantTitle_I, userEmail_I);
        setListAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogText();
            }
        });

    }
}
