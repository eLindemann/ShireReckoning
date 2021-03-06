package com.eriklindemann.shirereckoning;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int[] currentDate;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    public static final String EXTRA_DATA = "com.eriklindemann.shirereckoning.DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        TextView dateGregorian = (TextView) findViewById(R.id.date_view_gregorian);
        TextView dateWeekday = (TextView) findViewById(R.id.date_view_day);
        TextView dateDayAndMonth = (TextView) findViewById(R.id.date_view_date);
        TextView dateAgeAndYear = (TextView) findViewById(R.id.date_view_year);

        Reckoning reckon = new Reckoning();
//        reckon.setCalendar(2012, 10, 22, true);
        reckon.reckonDate();
        currentDate = reckon.getDateArray();

        dateGregorian.setText(currentDate[2] + "/" + currentDate[1] + "/" + currentDate[0]);
        dateWeekday.setText(reckon.getReckoningWeekday());
        dateDayAndMonth.setText(reckon.getReckoningDayAndMonth());
        dateAgeAndYear.setText(reckon.getReckoningAge());
    }

    public void saveDisplayedDate(View view) {
        Intent intent = new Intent(this, CustomDateActivity.class);
        intent.putExtra(EXTRA_DATA, currentDate);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(mDrawerToggle.onOptionsItemSelected(item)) return true;

        return super.onOptionsItemSelected(item);
    }
}
