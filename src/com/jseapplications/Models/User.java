package com.jseapplications.Models;

import com.jseapplications.DateTimeUtil;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * <h1>
 * User
 * </h1>
 * This class provides and overall data structure for each User created
 * in the system. Each user must must have a username which is used when
 * creating posts or following another user
 */
public class User {
    //-- Stores username of user
    private String username;
    //-- Stores the usernames of the users this user is following
    private ArrayList<String> followsList = new ArrayList<>();
    //-- Stores data of the posts the user has added
    private ArrayList<Post> posts = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public void post(final String message) {
        Post post = new Post(message);
        this.posts.add(0, post);
    }

    public void addFollow(final String username) {
        this.followsList.add(username);
    }

    public String getUsername() {
        return this.username;
    }

    public void displayPosts(final boolean showUsername) {
        for (Post post : this.posts) {
            if (showUsername) {
                System.out.println(this.username + " - " + post.showPost());
            }
            else {
                System.out.println(post.showPost());
            }
        }
    }

    public TreeMap<String, String> getPostMap() {
        TreeMap<String, String> postMap = new TreeMap<>();
        for (Post post : this.posts) {
            postMap.put(post.getTime(), username + " - " + post.getMessage() + " (" + DateTimeUtil.getTimeAgo(post.getTime()) + ")");
        }
        return postMap;
    }


    public ArrayList<String> getFollowed() {
        return this.followsList;
    }

}
