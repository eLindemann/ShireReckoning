package com.eriklindemann.shirereckoning;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView dateWeekday = (TextView) findViewById(R.id.date_view_day);
        TextView dateDayAndMonth = (TextView) findViewById(R.id.date_view_date);
        TextView dateAgeAndYear = (TextView) findViewById(R.id.date_view_year);

        Reckoning reckoning = new Reckoning();
        // reckoning.setCalendar(2001, 12, 19);
        reckoning.reckonDate();

        dateWeekday.setText(reckoning.getReckoningWeekday());
        dateDayAndMonth.setText(reckoning.getReckoningDayAndMonth());
        dateAgeAndYear.setText(reckoning.getReckoningAge());
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
        if (id == R.id.action_settings) { // TODO: Make actual settings to set.
            return true;
        }

        if (id == R.id.action_save_date) {
            Context context = MainActivity.this;
            String message = "Date Saved!";  // TODO: Save a set calendar date to DB for later retrieval.
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
