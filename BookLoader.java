package com.example.puspakbiswas.books;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Puspak Biswas on 19-06-2018.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {

    private String mURL;
    public BookLoader(Context context, String url){
        super(context);
        mURL = url;
    }

    protected void onStartLoading() {
        forceLoad();
    }

    public ArrayList<Book> loadInBackground(){
        if (mURL == null || mURL==""){
            return null;
        }else {
            ArrayList<Book> bookList = QueryUtils.fetchBookData(mURL);
            return bookList;
        }


    }
}
