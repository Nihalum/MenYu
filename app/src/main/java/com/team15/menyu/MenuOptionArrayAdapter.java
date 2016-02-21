package com.team15.menyu;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MenuOptionArrayAdapter extends ArrayAdapter<Food> {
    private final Context context;
    private final ArrayList<Food> values;
    private final String restaurantTitle_I;
    private final String userEmail_I;

    public MenuOptionArrayAdapter(Context context, ArrayList<Food> values,
                                  String restaurant, String userE) {
        super(context, R.layout.menu_option, values);
        this.context = context;
        this.values = values;
        this.restaurantTitle_I = restaurant;
        this.userEmail_I = userE;
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
        foodName.setText(values.get(position).getFood());
        foodReviewCount.setText(Integer.toString(values.get(position).getNoOfReviews()));

        //TODO: CHANGE THIS TO BOTH UPVOTES AND DOWNVOTES
        int sum = values.get(position).getDownVotes() + values.get(position).getUpVotes();
        if(sum < 0)
            foodRating.setText(Integer.toString(sum));
        else
            foodRating.setText("+" + Integer.toString(sum));
        imageView.setImageResource(R.drawable.ic_menu_camera);

        final String fud = values.get(position).getFood();
        tempView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReviewListActivity.class);
                intent.putExtra("RESTAURANT", restaurantTitle_I);
                intent.putExtra("EMAIL", userEmail_I);
                intent.putExtra("FOODNAME", fud);
                view.getContext().startActivity(intent);
            }
        });

        return tempView;

    }
}