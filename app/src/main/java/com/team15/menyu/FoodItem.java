package com.team15.menyu;

/**
 * Created by Benjamin on 2/20/2016.
 */
public class FoodItem {

    public String name;
    public String reviews;
    public String rating;

    public FoodItem(){
        name = "noNameInputted";
        reviews = "noReviewsWritten";
        rating = "no";
    }

    public FoodItem(String a, int b, int c) {
        name = a;
        reviews = Integer.toString(b) + " Reviews Written";
        if(c > 0)
            rating = "+" + Integer.toString(c);
        else
            rating = Integer.toString(c);
    }
}
