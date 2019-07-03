package com.jseapplications;

import java.util.ArrayList;

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

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String getUsername() {
        return username;
    }

    public void displayPosts() {
        for (Post post : posts) {
            System.out.println(post.showPost());
        }
    }

    public ArrayList<String> getFollowed() {
        return followsList;
    }
}
