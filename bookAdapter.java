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

        TextView title = (TextView) listBookView.findViewById(R.id.title);
        title.setText(book.getTitle());

        TextView author = (TextView) listBookView.findViewById(R.id.author);
        author.setText(book.getAuthor());

        return listBookView;
    }
}
