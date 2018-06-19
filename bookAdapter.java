package com.example.puspakbiswas.books;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Puspak Biswas on 27-05-2018.
 */

public class bookAdapter extends ArrayAdapter<Book> {

    public bookAdapter(Context c, ArrayList<Book> bookList){
        super(c,0,bookList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listBookView = convertView;
        if (listBookView == null){
            listBookView = LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }

        Book book = getItem(position);

        TextView textTitle = (TextView) listBookView.findViewById(R.id.title);
        textTitle.setText(book.getTitle());

        TextView textAuthor = (TextView) listBookView.findViewById(R.id.author);
        ArrayList<String> authorList = book.getAuthor();
        String author="";
        for(int i =0 ; i<authorList.size();i++){
            if (i != 0){
                author = author+"|"+authorList.get(i);
            }else{
                author = authorList.get(i);
            }
        }
        textAuthor.setText(author);

        return listBookView;
    }
}
