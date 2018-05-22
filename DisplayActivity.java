package com.example.puspakbiswas.books;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        displayMethod();
    }

    public void displayMethod(){
        ArrayList<books> bookList = new ArrayList<books>();
        bookList.add(new books("The idea of justice"));
        bookList.add(new books("The argumentative Indian"));

        ArrayAdapter<books> booksAdapter = new ArrayAdapter<books>(this,0,bookList);
        ListView booksView = (ListView) findViewById(R.id.list);
        booksView.setAdapter(booksAdapter);
    }
}

