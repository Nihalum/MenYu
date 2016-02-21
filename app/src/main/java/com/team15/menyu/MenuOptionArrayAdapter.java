package com.team15.menyu;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MenuOptionArrayAdapter extends ArrayAdapter<FoodItem> {
    private final Context context;
    private final ArrayList<FoodItem> values;

    public MenuOptionArrayAdapter(Context context, ArrayList<FoodItem> values) {
        super(context, R.layout.menu_option, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View tempView = inflater.inflate(R.layout.menu_option, parent, false);
        TextView foodName = (TextView) tempView.findViewById(R.id.nameOfFood);
        TextView foodReviewCount = (TextView) tempView.findViewById(R.id.reviewsOfFood);
        TextView foodRating = (TextView) tempView.findViewById(R.id.ratingOfFood);
        ImageView imageView = (ImageView) tempView.findViewById(R.id.photoOfFood);
        foodName.setText(values.get(position).name);
        foodReviewCount.setText(values.get(position).reviews);

        //TODO: CHANGE THIS TO BOTH UPVOTES AND DOWNVOTES
        foodRating.setText(values.get(position).rating);
        imageView.setImageResource(R.drawable.ic_menu_camera);
        tempView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return tempView;

    }
}