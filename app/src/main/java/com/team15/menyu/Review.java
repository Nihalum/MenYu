package com.team15.menyu;

/**
 * Created by RohitRamesh on 2/20/2016.
 */
public class Review {
    int id, helpful;
    String resName, food, reviewText, author;

    public Review() {
        this.resName = "restaurantDefault";
        this.food = "foodDefault";
        this.reviewText= "reviewDefault...";
        this.author = "authorDefault";
        this.helpful = 99;
    }

    public Review(String rName, String fud, String reviewT, String auth) {
        this.resName = rName;
        this.food = fud;
        this.reviewText= reviewT;
        this.author = auth;
        this.helpful = 0;
    }

    public Review(int helpful, String reviewText, String author) {
        this.helpful = helpful;
        this.reviewText = reviewText;
        this.author = author;
    }

    public Review(String name, String author, String food) {
        this.resName = name;
        this.author = author;
        this.food = food;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }
    public int getHelpful() {
        return this.helpful;
    }
    public void setName(String name) {
        this.resName = name;
    }
    public String getName() {
        return this.resName;
    }
    public void setReview(String review) {
        this.reviewText = review;
    }
    public String getReview() {
        return this.reviewText;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setFood(String food) {
        this.food = food;
    }
    public String getFood() {
        return this.food;
    }
}