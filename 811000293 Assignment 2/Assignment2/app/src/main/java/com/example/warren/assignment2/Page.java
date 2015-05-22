package com.example.warren.assignment2;

/**
 * Created by Warren on 3/22/2015.
 * This class defines the format of a page of a lesson. It contains some text to put in the TextView
 * of the lesson viewer and an Image Resource ID to put in the image view. If the Image Resource ID
 * is -1, the ImageView is set to be invisible as not all pages have images.
 */
public class Page {
    public String txt;
    public int img;

    public Page(String text, int image){
        txt = text;
        img = image;
    }
}
