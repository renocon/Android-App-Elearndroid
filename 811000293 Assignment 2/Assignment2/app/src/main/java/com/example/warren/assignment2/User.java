package com.example.warren.assignment2;

import java.io.Serializable;

/**
 * Created by Warren on 3/21/2015.
 * This class defines a user. It has a name and a score and implements Serializable so it can be
 * written to a file.
 */
public class User implements Serializable{

    public String name;
    public int score;

    public User(String uname){
        name = uname;
        score = 0;
    }
}
