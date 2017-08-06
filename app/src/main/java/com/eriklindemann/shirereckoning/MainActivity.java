package com.eriklindemann.shirereckoning;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

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

        dateWeekday.setText(reckoning.getShireWeekday());
        dateDayAndMonth.setText(reckoning.getDayAndMonth());
        dateAgeAndYear.setText(reckoning.getYearAndAge());
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

        if (id == R.id.action_save_date) {
            Context context = MainActivity.this;
            String message = "Date Saved!";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
