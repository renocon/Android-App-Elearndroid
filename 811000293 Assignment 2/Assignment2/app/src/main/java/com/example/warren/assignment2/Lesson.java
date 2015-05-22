package com.example.warren.assignment2;

import java.util.ArrayList;

/**
 * Created by Warren on 3/22/2015.
 * This class defines the general format of a topic tutorial.
 * It has a name and collection of pages like a book.
 */
public class Lesson {
    public String title;
    public ArrayList<Page> pages;

    public Lesson(String t){
        title = t;
        pages = new ArrayList<Page>();
    }

    public void addPage(Page newPage){
        pages.add(newPage);
    }

}
