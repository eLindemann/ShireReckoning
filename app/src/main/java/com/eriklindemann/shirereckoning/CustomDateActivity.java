package com.eriklindemann.shirereckoning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CustomDateActivity extends AppCompatActivity {
    int[] receivedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_date);

        // Get the sent date
        Intent intent = getIntent();
        receivedDate = intent.getIntArrayExtra(MainActivity.EXTRA_DATA);

        // Display the date
        TextView dateGregorian = (TextView) findViewById(R.id.date_view_gregorian);
        TextView dateWeekday = (TextView) findViewById(R.id.date_view_day);
        TextView dateDayAndMonth = (TextView) findViewById(R.id.date_view_date);
        TextView dateAgeAndYear = (TextView) findViewById(R.id.date_view_year);

        Reckoning reckon = new Reckoning();
        reckon.setCalendar(receivedDate[0], receivedDate[1], receivedDate[2]); // For some reason, this is not working here...
        reckon.reckonDate();

        dateGregorian.setText(receivedDate[0] + "/" + receivedDate[1] + "/" + receivedDate[2]);
        dateWeekday.setText(reckon.getReckoningWeekday());
        dateDayAndMonth.setText(reckon.getReckoningDayAndMonth());
        dateAgeAndYear.setText(reckon.getReckoningAge());

        // TODO: Save this to user database.
        // TODO: Display list from user database.

    }
}
