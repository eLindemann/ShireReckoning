package com.eriklindemann.shirereckoning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class CustomDateActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    int[] receivedDate;
    EditText dayGregorian;
    EditText monthGregorian;
    EditText yearGregorian;
    TextView dateWeekday;
    TextView dateDayAndMonth;
    TextView dateAgeAndYear;
    Switch toggleBeforeCommonEra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_date);

        // Get the sent date
        Intent intent = getIntent();
        receivedDate = intent.getIntArrayExtra(MainActivity.EXTRA_DATA);

        // Display the date
        dayGregorian = (EditText) findViewById(R.id.day_view_gregorian);
        monthGregorian = (EditText) findViewById(R.id.month_view_gregorian);
        yearGregorian = (EditText) findViewById(R.id.year_view_gregorian);
        dateWeekday = (TextView) findViewById(R.id.date_view_day);
        dateDayAndMonth = (TextView) findViewById(R.id.date_view_date);
        dateAgeAndYear = (TextView) findViewById(R.id.date_view_year);
        toggleBeforeCommonEra = (Switch) findViewById(R.id.toggleBeforeCommonEra);
        toggleBeforeCommonEra.setOnCheckedChangeListener(this);

        Reckoning reckon = new Reckoning();
        boolean isBeforeCommonEra = false;
        if (receivedDate[3] == 1) isBeforeCommonEra = true;
        reckon.setCalendar(receivedDate[0], receivedDate[1], receivedDate[2], isBeforeCommonEra);
        reckon.reckonDate();

        updateGregorian(receivedDate[0], receivedDate[1], receivedDate[2]);
        updateReckoning(reckon.getReckoningWeekday(), reckon.getReckoningDayAndMonth(), reckon.getReckoningAge());
        updateBeforeCommonEraSwitch();


        // TODO: Save this to user database.
        // TODO: Display list from user database.
        // TODO: Automatically updateReckoning() when gregorian inputs are changed.

    }
    void updateGregorian(int year, int month, int day){ // TODO: Update this so that BC and AD are options.
        yearGregorian.setText(String.format("%d", year));
        monthGregorian.setText(String.format("%d", month));
        dayGregorian.setText(String.format("%d", day));
    }
    void updateReckoning(String reckoningWeekday, String reckoningDayAndMonth, String reckoningAge) {
        dateWeekday.setText(reckoningWeekday);
        dateDayAndMonth.setText(reckoningDayAndMonth);
        dateAgeAndYear.setText(reckoningAge);
    }
    void updateBeforeCommonEraSwitch() {
        if (receivedDate[3] != 0) {
            Log.i("updateBCESwitch", "receivedDate[3] returned " + receivedDate[3] + ". Setting toggleBeforeCommonEra to true.");
            toggleBeforeCommonEra.setChecked(true);
        } else {
            Log.i("updateBCESwitch", "receivedDate[3] returned " + receivedDate[3] + ". Setting toggleBeforeCommonEra to false.");
            toggleBeforeCommonEra.setChecked(false);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            toggleBeforeCommonEra.setGravity(Gravity.LEFT);
            toggleBeforeCommonEra.setPadding(5, 0, 0, 0);
        } else {
            toggleBeforeCommonEra.setGravity(Gravity.RIGHT);
            toggleBeforeCommonEra.setPadding(0, 0, 5, 0);
        }
    }
}
