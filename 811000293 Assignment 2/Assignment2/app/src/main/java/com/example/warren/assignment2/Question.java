package com.example.warren.assignment2;

import java.util.ArrayList;

/**
 * Created by Warren on 3/22/2015.
 * This defines the format of a quiz question. It contains the question, multiple choice options and
 * the answer.
 */
public class Question {
    public String q;
    public ArrayList<String> a;
    public int correct;

    public Question(String questions,ArrayList<String> answers, int sol){
        q = questions;
        a = answers;
        correct = sol;
    }
}
