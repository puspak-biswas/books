package com.example.puspakbiswas.books;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Puspak Biswas on 19-05-2018.
 */

public class Book implements Serializable {

    private String mTitle;
    private ArrayList<String> mAuthor;

    public Book(String t, ArrayList<String> a){

        mTitle = t;
        mAuthor = a;
    }

    public String getTitle(){
        return mTitle;
    }

    public ArrayList<String> getAuthor(){
        return mAuthor;
    }

    public String toString(){
        return mTitle;
    }
}
