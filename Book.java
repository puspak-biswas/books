package com.example.puspakbiswas.books;

/**
 * Created by Puspak Biswas on 19-05-2018.
 */

public class Book {

    private String mTitle;
    private String mAuthor;

    public Book(String t, String a){

        mTitle = t;
        mAuthor = a;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getAuthor(){return mAuthor;}

    public String toString(){
        return mTitle;
    }
}
