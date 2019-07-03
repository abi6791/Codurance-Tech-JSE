package com.jseapplications;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Post {
    private String message;
    private String time;

    public Post(String message) {
        this.message = message;
        //-- Sets the time to be when the post is created
        this.time = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).format(Calendar.getInstance().getTime());
    }

    public String showPost() {
        return this.message + " (" + DateTimeUtil.getTimeAgo(this.time) + ")";
    }

}
