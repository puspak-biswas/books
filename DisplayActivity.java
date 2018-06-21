    package com.example.puspakbiswas.books;

    import android.app.LoaderManager;
    import android.content.Intent;
    import android.content.Loader;
    import android.os.AsyncTask;
    import android.app.LoaderManager.LoaderCallbacks;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.widget.ArrayAdapter;
    import android.widget.ListAdapter;
    import android.widget.ListView;
    import android.widget.TextView;

    import java.util.ArrayList;

    public class DisplayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

        private String URL;
        private bookAdapter mAdapter;
        private TextView mEmptyView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display);
            Log.i("TABLO","activity created");
            Intent intent = getIntent();
            URL = intent.getExtras().getString("StringUrl");
            mAdapter = new bookAdapter(this,new ArrayList<Book>());
            ListView booksView = (ListView) findViewById(R.id.list);
            booksView.setAdapter(mAdapter);
            mEmptyView = (TextView) findViewById(R.id.empty_view);
            booksView.setEmptyView(mEmptyView);
            ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                                   activeNetwork.isConnected();
            if(isConnected){
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.initLoader(0, null, this);
            }else{("No internet connection");
                mEmptyView.setText
            }
         }

        public Loader<ArrayList<Book>> onCreateLoader(int i, Bundle bundle){
            Log.i("TABLO","on create loader");
            Loader<ArrayList<Book>> loader = new BookLoader(this,URL);
            return loader;
        }

        public void onLoadFinished(Loader<ArrayList<Book>> loader,ArrayList<Book> bookList){
            Log.i("TABLO","on load finished");
            if (bookList != null && !bookList.isEmpty()) {
                mAdapter.addAll(bookList);
            }else{
                mEmptyView.setText(R.string.empty_text);
            }
        }

        public void onLoaderReset(Loader<ArrayList<Book>> loader){
            Log.i("TABLO","on loader reset");
            mAdapter.clear();
        }



    }

