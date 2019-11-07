package com.example.cinema_application.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cinema_application.model.Review;

public class ReviewManager {

    // Constant variable SQL
    private static final String     TABLE_NAME   = "review";
    public static final String      KEY_ID_REVIEW    = "_id";
    public static final String      KEY_TITLE_REVIEW   = "title";
    public static final String      KEY_DATE_REVIEW   = "date_review";
    public static final String      KEY_SCORE_SCENARIO_REVIEW   = "score_scenario";
    public static final String      KEY_SCORE_PRODUCTION_REVIEW   = "score_production";
    public static final String      KEY_SCORE_MUSIC_REVIEW   = "score_music";
    public static final String      KEY_COMMENTARY_REVIEW   = "commentary";
    public static final String      CREATE_TABLE_REVIEW = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_REVIEW+" INTEGER primary key," +
            " "+KEY_TITLE_REVIEW+" TEXT" +
            " "+KEY_DATE_REVIEW+" TEXT" +
            " "+KEY_SCORE_SCENARIO_REVIEW+" INTEGER" +
            " "+KEY_SCORE_PRODUCTION_REVIEW+" INTEGER" +
            " "+KEY_SCORE_MUSIC_REVIEW+" INTEGER" +
            " "+KEY_COMMENTARY_REVIEW+" TEXT" +
            ");";
    private MySQLite mSQLite;
    private SQLiteDatabase db;

    // Constructeur
    public ReviewManager(Context context)
    {
        mSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        db = mSQLite.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }

    public long addReview(Review review) {
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE_REVIEW, review.getTitle());
        values.put(KEY_DATE_REVIEW, review.getDateReview());
        values.put(KEY_SCORE_SCENARIO_REVIEW, review.getScoreScenario());
        values.put(KEY_SCORE_PRODUCTION_REVIEW, review.getScoreProduction());
        values.put(KEY_SCORE_MUSIC_REVIEW, review.getScoreMusic());

        // Return an ID, if it's incorrect return -1
        return db.insert(TABLE_NAME,null,values);
    }

    public int editReview(Review review) {
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE_REVIEW, review.getTitle());
        values.put(KEY_DATE_REVIEW, review.getDateReview());
        values.put(KEY_SCORE_SCENARIO_REVIEW, review.getScoreScenario());
        values.put(KEY_SCORE_PRODUCTION_REVIEW, review.getScoreProduction());
        values.put(KEY_SCORE_MUSIC_REVIEW, review.getScoreMusic());

        String where = KEY_ID_REVIEW+" = ?";
        String[] whereArgs = {review.getIdReview()+""};

        // Return the number of lines affected by the request
        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int deleteReview(Review review) {
        String where = KEY_ID_REVIEW+" = ?";
        String[] whereArgs = {review.getIdReview()+""};

        // Return the number of lines affected by the request
        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Review viewReview(int id) {
        Review review = new Review(0,"", "", 0, 0, 0, "");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_REVIEW+"="+id, null);
        if (c.moveToFirst()) {
            review.setIdReview(c.getInt(c.getColumnIndex(KEY_ID_REVIEW)));
            review.setDateReview(c.getString(c.getColumnIndex(KEY_DATE_REVIEW)));
            review.setScoreScenario(c.getInt(c.getColumnIndex(KEY_SCORE_SCENARIO_REVIEW)));
            review.setScoreProduction(c.getInt(c.getColumnIndex(KEY_SCORE_PRODUCTION_REVIEW)));
            review.setScoreMusic(c.getInt(c.getColumnIndex(KEY_SCORE_MUSIC_REVIEW)));
            review.setCommentary(c.getString(c.getColumnIndex(KEY_COMMENTARY_REVIEW)));
            c.close();
        }

        return review;
    }

    public Cursor viewAllReview() {
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

}
