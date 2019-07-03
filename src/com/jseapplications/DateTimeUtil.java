package com.jseapplications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    /**
     * Utility to convert date string to "x seconds/minutes/hours ago"
     *
     * @param dateTime Date time string in the following format {@value Constants#DATE_TIME_FORMAT}
     * @return Human readable string with time ago
     * <p>
     * Example: "5 Seconds Ago", "10 minutes ago"
     */
    public static String getTimeAgo(final String dateTime) {
        //-- Get current time and parse time of post
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String timeNow = sdf.format(Calendar.getInstance().getTime());
        Date d1;
        Date d2;
        try {
            d1 = sdf.parse(dateTime);
            d2 = sdf.parse(timeNow);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        //-- Get in millis to give "Time ago"
        long elapsed = d2.getTime() - d1.getTime();
        long seconds = elapsed / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        //-- Check each style to return time accordingly
        if (seconds > 60) {
            if (minutes > 60) {
                return hours + " hours ago";
            }
            else {
                return minutes + " minutes ago";
            }
        }
        else {
            return " seconds ago";
        }
    }

}