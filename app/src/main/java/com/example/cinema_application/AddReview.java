package com.example.cinema_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.cinema_application.database.ReviewManager;
import com.example.cinema_application.model.Review;

import java.util.Calendar;

public class AddReview extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker, btnReviewSend;
    EditText txtDate, txtTime, reviewTitle, mScoreScenario, mScoreProduction, mScoreMusic, mCommentary;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        reviewTitle = (EditText) findViewById(R.id.reviewTitle);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        txtDate = (EditText) findViewById(R.id.in_date);
        txtTime = (EditText) findViewById(R.id.in_time);
        mScoreScenario = (EditText) findViewById(R.id.scenario);
        mScoreProduction = (EditText) findViewById(R.id.production);
        mScoreMusic = (EditText) findViewById(R.id.music);
        mCommentary = (EditText) findViewById(R.id.reviewCommentary);
        btnReviewSend = (Button) findViewById(R.id.reviewSend);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnReviewSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if(v == btnReviewSend) {
            ReviewManager review = new ReviewManager(this);
            review.open();
            try {
                review.addReview(new Review(
                       0,
                        reviewTitle.getText().toString(),
                        txtDate.getText().toString() + " " + txtTime.getText().toString(),
                        Integer.parseInt(mScoreScenario.getText().toString()),
                        Integer.parseInt(mScoreProduction.getText().toString()),
                        Integer.parseInt(mScoreMusic.getText().toString()),
                        mCommentary.getText().toString())
                );

                review.close();
                AddReview.this.finish();
            } catch (Exception error) {
                Log.d("error : " , error.toString());
            }
        }
    }
}