package com.team15.menyu;

/**
 * Created by RohitRamesh on 2/20/2016.
 */
public class Food {
    int id, upvotes, downvotes, noOfReviews;
    String resName,food;

    public Food() {
        this.upvotes = 0;
        this.downvotes = 0;
        this.noOfReviews = 0;
        this.food = "defaultConstructor";
    }

    public Food(int upvotes, int downvotes, int noOfReviews, String food) {
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.noOfReviews = noOfReviews;
        this.food = food;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public void setNoOfReviews(int noOfReviews) {
        this.noOfReviews = noOfReviews;
    }
    public int getNoOfReviews() {
        return this.noOfReviews;
    }
    public void setUpVotes(int upVotes) {
        this.upvotes = upVotes;
    }
    public int getUpVotes() {
        return this.upvotes;
    }
    public void setDownVotes(int downVotes) {
        this.downvotes = downVotes;
    }
    public int getDownVotes() {
        return this.downvotes;
    }
    public void setName(String name) {
        this.resName = name;
    }
    public String getName() {
        return this.resName;
    }
    public void setFood(String food) {
        this.food = food;
    }
    public String getFood() {
        return this.food;
    }
}
