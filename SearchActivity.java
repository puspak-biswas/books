package com.example.puspakbiswas.books;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
       String url = "https://www.googleapis.com/books/v1/volumes?q="+keyword+"&maxResults=1";
        Log.i("URL:",url);
        ExperimentClass task = new ExperimentClass();
        task.execute(url);
       // Log.i("URL:",url);
        //Intent intent = new Intent(this,DisplayActivity.class);
       // intent.setData(Uri.parse(url));
        //startActivity(intent);
    }
    
    private void callDisplayActivity(ArrayList<Book> bookList){
        Intent intent = new Intent(SearchActivity.class,DisplayActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)bookList);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
            
    }

    class ExperimentClass extends AsyncTask<String,Void,String>{

        protected ArrayList<Book> doInBackground(String... urls){
            ArrayList bookList = QueryUtils.fetchBookData(urls[0]);
            return bookList;
        }
        protected void onPostExecute(ArrayList<Book> bookList){
            callDisplayActivity(bookList);
        }
    }
}
