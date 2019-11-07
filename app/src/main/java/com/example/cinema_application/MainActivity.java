package com.example.cinema_application;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.cinema_application.database.ReviewManager;
import com.example.cinema_application.model.Review;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView reviewList = findViewById(R.id.reviewList);
        setSupportActionBar(toolbar);

        String reviewString = "";

        //Display Review
        ReviewManager review = new ReviewManager(this);
        review.open();
        Cursor c = review.viewAllReview();

        if (c.moveToFirst())
        {
            do {
                reviewString += c.getString(c.getColumnIndex(ReviewManager.KEY_TITLE_REVIEW))+ "\n" +
                        "Date : " + c.getString(c.getColumnIndex(ReviewManager.KEY_DATE_REVIEW))+ "\n" +
                        "Note musique : " + c.getString(c.getColumnIndex(ReviewManager.KEY_SCORE_MUSIC_REVIEW))+ "\n" +
                        "Note réalisation : " + c.getString(c.getColumnIndex(ReviewManager.KEY_SCORE_PRODUCTION_REVIEW))+ "\n" +
                        "Note scénario : " + c.getString(c.getColumnIndex(ReviewManager.KEY_SCORE_SCENARIO_REVIEW))+ "\n" +
                        "Critique : " + c.getString(c.getColumnIndex(ReviewManager.KEY_COMMENTARY_REVIEW))+ "\n" +
                        " ----------------------- \n";
            }
            while (c.moveToNext());
            reviewList.setText(reviewString);
        } else {
            reviewList.setText("Aucune critique référencé");
        }
        c.close();
        review.close();

        //Go to AddReview activity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddReview.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
