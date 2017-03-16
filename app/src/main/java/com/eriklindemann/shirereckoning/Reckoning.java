package com.eriklindemann.shirereckoning;

import android.icu.text.SimpleDateFormat;

import java.util.Calendar;

class Reckoning {
    private Calendar c;
    private boolean leapYear;
    private int shireDay;
    private int shireMonth;
    private int shireYear;
    private int ardaAge;

    Reckoning() {
        this.c = Calendar.getInstance();
        setDate();
    }

    private void setYear() {
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        int thisYear = Integer.parseInt(year.format(c.getTime()));
        setLeapYear(thisYear);
        this.shireYear = thisYear;
        this.ardaAge = 0;
        if (thisYear >= 1954) {
            shireYear -= 1953;
            ardaAge = 7;
        }
        if (thisYear < 1954 && thisYear >= 445) {
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
    }

    private void setLeapYear(int year) {
        if (year%4 != 0) {
            leapYear = false;
        } else if (year%100 == 0) {
            if (year%400 != 0) {
                leapYear = false;
            } else if (year%400 == 0) {
                leapYear = true;
            }
        } else if (year%4 == 0) {
            leapYear = true;
        }
    }

    private void setDate() {
        SimpleDateFormat day = new SimpleDateFormat("D");
        int dayOfYear = Integer.parseInt(day.format(c.getTime()));
        setYear();
        this.shireDay = 0;
        this.shireMonth = 0;
        if (ardaAge == 3) {
            shireYear = shireYear - 1600;
        }
        int currentDay = dayOfYear + 11;
        if (currentDay > 183) {
            if (!leapYear) {
                currentDay += 1;
            }
        }
        if (currentDay > 366) {
            currentDay -= 366;
        }
        if (currentDay == 1) {
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
        if (currentDay == 366) {
            shireDay = 1;
            shireMonth = 0;
        }
    }

    String getShireWeekday() {
        SimpleDateFormat weekday = new SimpleDateFormat(SimpleDateFormat.WEEKDAY);
        String dayOfTheWeek = weekday.format(c.getTime());
        String weekDay = "Sterday";

        if (dayOfTheWeek.matches("Sunday")) {
            weekDay = "Sunday";
        }
        if (dayOfTheWeek.matches("Monday")) {
            weekDay = "Monday";
        }
        if (dayOfTheWeek.matches("Tuesday")) {
            weekDay = "Trewsday";
        }
        if (dayOfTheWeek.matches("Wednesday")) {
            weekDay = "Hevensday";
        }
        if (dayOfTheWeek.matches("Thursday")) {
            weekDay = "Mersday";
        }
        if (dayOfTheWeek.matches("Friday")) {
            weekDay = "Highday";
        }

        return weekDay;
    }

    String getYearAndAge() {
        String getAge = "";
        if (ardaAge == 1) {
            getAge = "First Age";
        }
        if (ardaAge == 2) {
            getAge = "Second Age";
        }
        if (ardaAge == 3) {
            getAge = "Third Age";
        }
        if (ardaAge == 4) {
            getAge = "Fourth Age";
        }
        if (ardaAge == 5) {
            getAge = "Fifth Age";
        }
        if (ardaAge == 6) {
            getAge = "Sixth Age";
        }
        if (ardaAge == 7) {
            getAge = "Seventh Age";
        }

        return getAge + " " + shireYear;
    }

    String getDayAndMonth() {
        String getMonth = "";
        if (shireMonth == 0) {
            getMonth = "Yule";
        }
        if (shireMonth == 1) {
            getMonth = "Afteryule";
        }
        if (shireMonth == 2) {
            getMonth = "Solmath";
        }
        if (shireMonth == 3) {
            getMonth = "Rethe";
        }
        if (shireMonth == 4) {
            getMonth = "Astron";
        }
        if (shireMonth == 5) {
            getMonth = "Thrimidge";
        }
        if (shireMonth == 6) {
            getMonth = "Forelithe";
        }
        if (shireMonth == 7) {
            getMonth = "Lithe";
        }
        if (shireMonth == 8) {
            getMonth = "Afterlithe";
        }
        if (shireMonth == 9) {
            getMonth = "Wedmath";
        }
        if (shireMonth == 10) {
            getMonth = "Halimath";
        }
        if (shireMonth == 11) {
            getMonth = "Winterfilth";
        }
        if (shireMonth == 12) {
            getMonth = "Blotmath";
        }
        if (shireMonth == 13) {
            getMonth = "Foreyule";
        }

        String getDayAndMonth = shireDay + " " + getMonth;
        if (shireMonth == 7 && shireDay == 3) {
            getDayAndMonth = "Midyear's Day";
        } else if (shireMonth == 7 && shireDay == 4) {
            getDayAndMonth = "Overlithe";
        }
        return getDayAndMonth;
    }
}
