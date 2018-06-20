    package com.example.puspakbiswas.books;

    import android.app.LoaderManager;
    import android.content.Intent;
    import android.content.Loader;
    import android.os.AsyncTask;
    import android.app.LoaderManager.LoaderCallbacks;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.ArrayAdapter;
    import android.widget.ListAdapter;
    import android.widget.ListView;

    import java.util.ArrayList;

    public class DisplayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

        private String URL;
        private bookAdapter mAdapter;
        private TextView mEmptyTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display);
            Intent intent = getIntent();
            URL = intent.getExtras().getString("StringUrl");
            mAdapter = new bookAdapter(this,new ArrayList<Book>());
            ListView booksView = (ListView) findViewById(R.id.list);
            booksView.setAdapter(mAdapter);
            mEmptyTextView = (TextView) findViewById(R.id.empty_View);
            booksView.setEmptyView(mEmptyTextView);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
        }

        public Loader<ArrayList<Book>> onCreateLoader(int i, Bundle bundle){
            Loader<ArrayList<Book>> loader = new BookLoader(this,URL);
            return loader;
        }

        public void onLoadFinished(Loader<ArrayList<Book>> loader,ArrayList<Book> bookList){
            if (bookList != null && !bookList.isEmpty()) {
                mAdapter.addAll(bookList);
            }else{
            mEmptyTextView.setText("No books found");
            }
        }

        public void onLoaderReset(Loader<ArrayList<Book>> loader){
            mAdapter.clear();
        }



    }

