package com.team15.menyu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ReviewOptionArrayAdapter extends ArrayAdapter<Review> {

    DatabaseHelper db = new DatabaseHelper(this.getContext());
    private final Context context;
    private final ArrayList<Review> values;
    private final String restaurant;
    private final String foodName;
    private final String userEmail;

    public ReviewOptionArrayAdapter(Context context, ArrayList<Review> values,
                                    String a, String b, String c) {
        super(context, R.layout.review_option, values);
        this.context = context;
        this.values = values;
        this.restaurant = a;
        this.foodName = b;
        this.userEmail = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View tempView = inflater.inflate(R.layout.review_option, parent, false);
        TextView reviewTitle = (TextView) tempView.findViewById(R.id.reviewTitle);
        TextView reviewPreview = (TextView) tempView.findViewById(R.id.reviewPreview);
        TextView helpfulCounter = (TextView) tempView.findViewById(R.id.helpfulCounter);
        reviewTitle.setText(values.get(position).getAuthor() + " says...");
        reviewPreview.setText(values.get(position).getReview());
        helpfulCounter.setText(Integer.toString(values.get(position).getHelpful()));

        final String passthru = values.get(position).getReview();
        tempView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readReviewDialog(passthru);
            }
        });

        return tempView;

    }


    private void readReviewDialog(String texts){ //PUSH IN INTENT REVIEWTEXT

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Review:");

        DisplayMetrics metrics = this.getContext().getResources().getDisplayMetrics();
        float sidedp = 20f;
        int sideMargin = (int) (metrics.density * sidedp + 0.5f);
        float topdp = 10f;
        int topMargin = (int) (metrics.density * topdp + 0.5f);

        LinearLayout layout = new LinearLayout(this.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(sideMargin, topMargin, sideMargin, topMargin);

        float togdp = 80f;
        int togPix = (int) (metrics.density * togdp + 0.5f);
        float togHdp = 38f;
        int togHPix = (int) (metrics.density * togHdp + 0.5f);

//
//        final ToggleButton helpful = new ToggleButton(this.getContext());
//        helpful.setText("Helpful");
//        helpful.setTextOn("Helpful");
//        helpful.setTextOff("Helpful");
//        helpful.setBackgroundColor(Color.LTGRAY);
//        helpful.setChecked(false);
//        helpful.setTextColor(Color.BLACK);//green color
//
//        LinearLayout.LayoutParams tog = new LinearLayout.LayoutParams(togPix, togHPix);
//        tog.setMargins(sideMargin*10, topMargin, sideMargin, 0);
//
//        helpful.setLayoutParams(tog);
//
//        helpful.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (helpful.isChecked()) {
//                    helpful.setBackgroundColor(Color.rgb(22, 160, 133));//green color
//                    Review r = new Review(restaurant, userEmail, foodName);
//                    db.updateHelpful(r);
//                } else {
//                    helpful.setBackgroundColor(Color.LTGRAY);
//                    //helpful.setTextColor(Color.DKGRAY);
//                }
//            }
//        });

        TextView review = new TextView(this.getContext());
        review.setText(texts);

        builder.setView(review);
        layout.addView(review, params);
        //layout.addView(helpful);
        builder.setView(layout);

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}