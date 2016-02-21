package com.team15.menyu;

/**
 * Created by RohitRamesh on 2/20/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by RohitRamesh on 2/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MenYu.db";

    // Table Names
    private static final String TABLE_FOOD = "Food";
    private static final String TABLE_REVIEW = "Review";

    // Common column names
    private static final String id = "_id";
    private static final String food = "food";
    private static final String restaurant = "resName";

    //Food Table - column nmaes
    private static final String upvotes = "upvotes";
    private static final String downvotes = "downvotes";
    private static final String noOfReviews = "noOfReviews";

    //Review Table - column names
    private static final String helpful = "helpful";
    private static final String reviewText = "reviewText";
    private static final String author = "author";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_FOOD = "CREATE TABLE "
            + TABLE_FOOD + "(" + id + " INTEGER PRIMARY KEY, " + food
            + " TEXT, " + restaurant + " TEXT, " + upvotes
            + " INTEGER, " + downvotes + " INTEGER, " + noOfReviews +
            " INTEGER" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_REVIEW = "CREATE TABLE " + TABLE_REVIEW
            + "(" + id + " INTEGER PRIMARY KEY, " + food + " TEXT, "
            + restaurant + " TEXT, " + helpful + " INTEGER, " + reviewText + " TEXT, "
            + author + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_REVIEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);

        // create new tables
        onCreate(db);
    }

    public ArrayList<Food> getFood(String restaurant) {
        ArrayList<Food> f = new ArrayList<Food>();
        db = this.getReadableDatabase();
        String query = "SELECT food, upvotes, downvotes, noOfReviews FROM " + TABLE_FOOD + " WHERE resName = " + "'" + restaurant + "'";
        Cursor c = db.rawQuery(query, null);
        Log.d("lol", c.toString());
        if (c != null) {
            c.moveToFirst();
            do {
                String food = c.getString(0);
                int upvotes = c.getInt(1);
                int downvotes = c.getInt(2);
                int noOfReviews = c.getInt(3);
                Food temp = new Food(upvotes, downvotes, noOfReviews, food);
                f.add(temp);
            } while (c.moveToNext());
            return f;
        }
        else {
            return null;
        }
    }

}