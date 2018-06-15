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
            Intent intent = getIntent();
            Bundle args = intent.getBundleExtra("BUNDLE");
            ArrayList<Book> bookList = (ArrayList<Book>) args.getSerializable("ARRAYLIST");
            displayMethod(bookList);
        }

        public void displayMethod(ArrayList<Book> bookList){
            ArrayList<Book> bookList = new ArrayList<Book>();
            //bookList.add(new Book("The idea of justice","Amartya Sen"));
            //bookList.add(new Book("The argumentative Indian","Amartya Sen"));

            bookAdapter booksAdapter = new bookAdapter(this,bookList);
            ListView booksView = (ListView) findViewById(R.id.list);
            booksView.setAdapter(booksAdapter);
        }
    }

