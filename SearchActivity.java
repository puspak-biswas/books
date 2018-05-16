package com.example.puspakbiswas.books;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void onSearch(View view){
        EditText keywordText = (EditText) findViewById(R.id.keyword);
        String keyword = keywordText.getText().toString();
        String url = "https://www.googleapis.com/books/v1/volumes?q="+keyword;
        Log.i("URL:",url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
