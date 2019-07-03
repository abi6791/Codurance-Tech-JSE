package com.jseapplications.Models;

import com.jseapplications.Constants;
import com.jseapplications.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <h1>
 * Post
 * </h1>
 * This model contains data of each post for a user. The time
 * of a post is generated once a post is created and defaults to {@value Constants#DATE_TIME_FORMAT}
 */
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

    public String getMessage() {
        return this.message;
    }

    public String getTime() {
        return this.time;
    }

}
