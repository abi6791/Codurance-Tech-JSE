package com.jseapplications;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Post {
    private String message;
    private String time;

    public Post(String message) {
        this.message = message;
        this.time = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).format(Calendar.getInstance().getTime());
    }

    public String showPost() {
//        LocalDateTime t1 = LocalDateTime.of(2015, 1, 1, 0, 0, 0);
//        LocalDateTime t2 = LocalDateTime.now();
//        Period period = Period.between(t1.toLocalDate(), t2.toLocalDate());
//        Duration duration = Duration.between(t1, t2);
        String post = this.message + " (" + this.time + ")";
        return post;
    }


}
