package com.jseapplications;

import com.jseapplications.Models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import static com.jseapplications.Constants.Commands.*;

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
                final String name = reader.readLine();
                checkCommand(name);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <h1>
     * Check Command
     * </h1>
     * This function splits the command and fires off the necessary function
     * associated with the command
     *
     * @param command Full line from the console. Can be
     *                {@value Constants.Commands#FOLLOW_COMMAND},
     *                {@value Constants.Commands#POST_COMMAND},
     *                {@value Constants.Commands#WALL_COMMAND},
     *                or just a username
     */
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
                case POST_COMMAND:
                    //-- Command Is Post, split by Arrow for full message
                    String[] postMessage = command.split(POST_COMMAND);
                    postMessage(postMessage[0].trim(), postMessage[1]);
                    break;
                case FOLLOW_COMMAND:
                    //-- Command is follows user
                    followUser(commands[0], commands[2]);
                    break;
                case WALL_COMMAND:
                    //-- Command is show Wall of user and users being followed
                    showUserWall(commands[0]);
                    break;
            }
        }
    }

    /**
     * <h1>
     * Show Users Wall
     * </h1>
     * This shows all the posts made by the user and all the posts
     * made by the users that this user is following. Sorts by time posted
     *
     * @param username Username to show posts of
     */
    private static void showUserWall(final String username) {
        ArrayList<String> followed = getUser(username).getFollowed();
        ArrayList<User> followedUsers = new ArrayList<>();
        //-- Get all posts by followed users
        for (String followedUsername : followed) {
            followedUsers.add(getUser(followedUsername));
        }
        //-- Add User's post to list
        followedUsers.add(getUser(username));
        //-- Get All Posts from Each user, displayed with username prefix
        TreeMap<String, String> postMap = new TreeMap<>();
        for (User user : followedUsers) {
            postMap.putAll(user.getPostMap());
        }
        //-- Reverse Map to show latest at the top
        ArrayList<String> orderedPosts = new ArrayList<>(postMap.values());
        Collections.reverse(orderedPosts);
        for (String post : orderedPosts) {
            System.out.println(post);
        }
    }

    /**
     * <h1>
     * Follow user
     * </h1>
     * Allows a user to follow another user which then shows
     * their posts by using the {@value Constants.Commands#FOLLOW_COMMAND}
     *
     * @param username         Username of user to add follow to
     * @param usernameToFollow Username of user to follow
     */
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

    /**
     * <h1>
     * Show User's Posts
     * </h1>
     * This function will print all the users posts to the console
     *
     * @param username User to print posts of
     */
    private static void showUserPosts(final String username) {
        //-- Check If User Exists and display posts
        getUser(username).displayPosts(false);
    }

    /**
     * Gets user associated with username in the system
     *
     * @param username Username to find
     * @return User associated with the username
     */
    private static User getUser(final String username) {
        for (User user : data) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
