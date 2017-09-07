package com.eriklindemann.shirereckoning;

import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.webkit.ConsoleMessage;

import java.util.Calendar;
import java.util.GregorianCalendar;

class Reckoning {
    private Calendar c;
    private boolean isLeapYear;
    private int shireDay;
    private int shireMonth;
    private int shireYear;
    private int ardaAge;
    private boolean isEastFarthing;

    Reckoning() {
        c = Calendar.getInstance();
    }

    void setCalendar(int year, int month, int day) {
        setCalendar(year, month, day, false);
        Log.i("setCalendar", "No isBeforeCommonEra parameter given - passing as false");
    }

    void setCalendar(int year, int month, int day, boolean isBeforeCommonEra) {
        c.clear(); // Clear the current calendar object

        if (isBeforeCommonEra) {
            c.set(Calendar.ERA, GregorianCalendar.BC);
        }
        else c.set(Calendar.ERA, GregorianCalendar.AD);

        c.set(year, month-1, day); // Calendar.MONTH is 0-based
    }

    void reckonDate(){
        setReckoningDate();
        setReckoningYear();
    }

    private void setReckoningDate() { // TODO: Think on this. There is probably a cleaner/simpler way.
        SimpleDateFormat day = new SimpleDateFormat("D");
        int dayOfYear = Integer.parseInt(day.format(c.getTime()));
        this.shireDay = 0;
        this.shireMonth = 0;
        int currentDay = dayOfYear + 10; // The Shire year starts on 22 December Gregorian
        if (currentDay > 366) { // If dayOfYear + 10 is > 366, we subtract 366 to bring synchronize
            currentDay -= 366;
        }
        /*if (currentDay > 183) { // Leap Year matters for Lithe, unfortunately it's ruining things...
            if (!isLeapYear) {
                currentDay += 1;
            }
        }*/
        if (currentDay == 1) { // Month 0 = Yule, this is December 22
            shireDay = 2;
            shireMonth = 0;
        }
        if (currentDay >= 2 && currentDay <= 31) {
            shireDay = currentDay - 1;
            shireMonth = 1;
        }
        if (currentDay >= 32 && currentDay <= 61) {
            shireDay = currentDay - 31;
            shireMonth = 2;
        }
        if (currentDay >= 62 && currentDay <= 91) {
            shireDay = currentDay - 61;
            shireMonth = 3;
        }
        if (currentDay >= 92 && currentDay <= 121) {
            shireDay = currentDay - 91;
            shireMonth = 4;
        }
        if (currentDay >= 122 && currentDay <= 151) {
            shireDay = currentDay - 121;
            shireMonth = 5;
        }
        if (currentDay >= 152 && currentDay <= 181) {
            shireDay = currentDay - 151;
            shireMonth = 6;
        }
        if (currentDay == 182) {
            shireDay = 1;
            shireMonth = 7;
        }
        if (currentDay == 183) {
            shireDay = 3;
            shireMonth = 7;
        }
        if (currentDay == 184) {
            shireDay = 4;
            shireMonth = 7;
        }
        if (currentDay == 185) {
            shireDay = 2;
            shireMonth = 7;
        }
        if (currentDay >= 186 && currentDay <= 215) {
            shireDay = currentDay - 185;
            shireMonth = 8;
        }
        if (currentDay >= 216 && currentDay <= 245) {
            shireDay = currentDay - 215;
            shireMonth = 9;
        }
        if (currentDay >= 246 && currentDay <= 275) {
            shireDay = currentDay - 245;
            shireMonth = 10;
        }
        if (currentDay >= 276 && currentDay <= 305) {
            shireDay = currentDay - 275;
            shireMonth = 11;
        }
        if (currentDay >= 306 && currentDay <= 335) {
            shireDay = currentDay - 305;
            shireMonth = 12;
        }
        if (currentDay >= 336 && currentDay <= 365) {
            shireDay = currentDay - 335;
            shireMonth = 13;
        }
        if (currentDay == 366) { // December 21
            shireDay = 1;
            shireMonth = 0;
        }
    }

    private void setReckoningYear() {
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        int thisYear = Integer.parseInt(year.format(c.getTime()));
        setCalendarLeapYear(thisYear);

        SimpleDateFormat era = new SimpleDateFormat("G");
        String getEra = era.format(c.getTime());
        Log.i("setReckoningYear", "getEra = " + getEra);
        if (getEra.equals("BC")){
            thisYear -= thisYear*2;
            Log.i("setReckoningYear", "getEra returned BC, thisYear =" + thisYear);
        }
        this.shireYear = thisYear;


        this.ardaAge = 0;
        if (thisYear >= 1945) {
            shireYear -= 1944;
            ardaAge = 7;
        }
        if (thisYear < 1945 && thisYear >= 445) {
            shireYear -= 444;
            ardaAge = 6;
        }
        if (thisYear < 445 && thisYear >= -1103) {
            shireYear += 1104;
            ardaAge = 5;
        }
        if (thisYear < -1103 && thisYear >= -3102) {
            shireYear += 3102;
            ardaAge = 4;
        }
        if (thisYear < -3102 && thisYear >= -6122) {
            shireYear += 6122;
            ardaAge = 3;
        }
        if (thisYear < -6122 && thisYear >= -9563) {
            shireYear += 9563;
            ardaAge = 2;
        }
        if (thisYear < -9563 && thisYear >= -730153) {
            shireYear += 730153;
            ardaAge = 1;
        }
        /*if (ardaAge == 3) {
            shireYear = shireYear - 1600; // I don't know why this was in the code...
        }*/
    }

    private void setCalendarLeapYear(int year) {
        if (year%4 != 0) {
            isLeapYear = false;
        } else if (year%100 == 0) {
            if (year%400 != 0) {
                isLeapYear = false;
            } else if (year%400 == 0) {
                isLeapYear = true;
            }
        } else if (year%4 == 0) {
            isLeapYear = true;
        }
    }

    private void setEastFarthing(boolean b) {
        this.isEastFarthing = b;
    }

    String getReckoningWeekday() { // Apparently their weekdays, while are similar to ours, fall on different days...
        SimpleDateFormat weekday = new SimpleDateFormat(SimpleDateFormat.WEEKDAY);
        String dayOfTheWeek = weekday.format(c.getTime());
        String weekDay = "Sterday";

        switch (dayOfTheWeek) {
            case "Tuesday":
                weekDay = "Sunday";
                break;
            case "Wednesday":
                weekDay = "Monday";
                break;
            case "Thursday":
                weekDay = "Trewsday";
                break;
            case "Friday":
                weekDay = "Hevensday";
                break;
            case "Saturday":
                weekDay = "Mersday";
                break;
            case "Sunday":
                weekDay = "Highday";
                break;
        }

        return weekDay;
    }

    String getReckoningAge() {
        String getAge = "";
        switch(ardaAge) {
            case 1:
                getAge = "First Age";
                break;
            case 2:
                getAge = "Second Age";
                break;
            case 3:
                getAge = "Third Age";
                break;
            case 4:
                getAge = "Fourth Age";
                break;
            case 5:
                getAge = "Fifth Age";
                break;
            case 6:
                getAge = "Sixth Age";
                break;
            case 7:
                getAge = "Seventh Age";
                break;
        }

        return getAge + " " + shireYear;
    }

    String getReckoningDayAndMonth() { // TODO: In The Eastfarthing/Bree, the months are named differently. Will have to make a preference for that.
        String month = "";
        switch (shireMonth) {
            case 0:
                month = "Yule";
                break;
            case 1:
                month = "Afteryule";
                if (isEastFarthing) month = "Frery";
                break;
            case 2:
                month = "Solmath";
                break;
            case 3:
                month = "Rethe";
                break;
            case 4:
                month = "Astron";
                if (isEastFarthing) month = "Chithing";
                break;
            case 5:
                month = "Thrimidge";
                break;
            case 6:
                month = "Forelithe";
                if (isEastFarthing) month = "Lithe";
                break;
            case 7:
                month = "Lithe";
                if (isEastFarthing) month = "The Summerdays";
                break;
            case 8:
                month = "Afterlithe";
                if (isEastFarthing) month = "Mede";
                break;
            case 9:
                month = "Wedmath";
                break;
            case 10:
                month = "Halimath";
                if (isEastFarthing) month = "Harvestmath";
                break;
            case 11:
                month = "Winterfilth";
                if (isEastFarthing) month = "Wintring";
                break;
            case 12:
                month = "Blotmath";
                if (isEastFarthing) month = "Blooting";
                break;
            case 13:
                month = "Foreyule";
                if (isEastFarthing) month = "Yulemath";
                break;
        }

        String getDayAndMonth = shireDay + " " + month;
        if (shireMonth == 7 && shireDay == 3) {
            getDayAndMonth = "Midyear's Day";
        } else if (shireMonth == 7 && shireDay == 4) {
            getDayAndMonth = "Overlithe";
        }
        return getDayAndMonth;
    }

    int[] getDateArray() {
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
            int thisYear = Integer.parseInt(year.format(c.getTime()));
        Log.i("getDateArray", "thisYear = " + thisYear);
        SimpleDateFormat month = new SimpleDateFormat("MM");
            int thisMonth = Integer.parseInt(month.format(c.getTime()));
        Log.i("getDateArray", "thisMonth = " + thisMonth);
        SimpleDateFormat day = new SimpleDateFormat("dd");
            int thisDay = Integer.parseInt(day.format(c.getTime()));
        Log.i("getDateArray", "thisDay = " + thisDay);
        SimpleDateFormat era = new SimpleDateFormat("G");
            String getEra = era.format(c.getTime());
        Log.i("getDateArray", "getEra = " + getEra);
        int thisEra = 1;
            if (getEra.equals("AD")) thisEra = 0;
        return new int[] {thisYear, thisMonth, thisDay, thisEra};
    }
}
