package com.jseapplications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    //-- Stores information about users and posts
    private static ArrayList<User> data = new ArrayList<>();

    public static void main(String[] args) {
        //-- Loop Until program exited
        while (true) {
            //-- Read in line from console
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            try {
                String name = reader.readLine();
                checkCommand(name);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private static void checkCommand(final String command) {
        //-- Split command by space
        String[] commands = command.split(" ");

        if (commands.length == 1) {
            //-- Command Is A Read, check user
            showUserPosts(commands[0]);
        }
        else {
            //-- Multiple commands
            switch (commands[1]) {
                case "->":
                    //-- Command Is Post, split by Arrow for full message
                    String[] postMessage = command.split("->");
                    postMessage(postMessage[0].trim(), postMessage[1]);
                    break;
                case "follows":
                    //-- Command is follows user
                    followUser(commands[0], commands[2]);
                    break;
                case "wall":
                    //-- Command is show Wall of user and users being followed
                    showUserWall(commands[0]);
                    break;
            }
        }
    }

    private static void showUserWall(final String username) {
        ArrayList<String> followed = getUser(username).getFollowed();
        ArrayList<User> followedUsers = new ArrayList<>();
        //-- Get all posts by followed users
        for (String followedUsername : followed) {
            followedUsers.add(getUser(followedUsername));
        }
        //-- Add User's post to list
        followedUsers.add(getUser(username));
        //-- Convert all to String lines


    }

    private static void followUser(final String username, final String usernameToFollow) {
        getUser(username).addFollow(usernameToFollow);
    }

    private static void postMessage(final String username, final String message) {
        //-- Check if user already exists
        User user = getUser(username);
        if (user != null) {
            //-- User exists, add post
            user.post(message);
            return;

        }
        //-- User doesn't exist, create and post
        User newUser = new User(username);
        newUser.post(message);
        data.add(newUser);
    }

    private static void showUserPosts(final String username) {
        //-- Check If User Exists and display posts
        getUser(username).displayPosts();
    }

    private static User getUser(final String username) {
        for (User user : data) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
