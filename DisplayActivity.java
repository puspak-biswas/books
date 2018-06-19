    package com.example.puspakbiswas.books;

    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.ArrayAdapter;
    import android.widget.ListAdapter;
    import android.widget.ListView;

    import java.util.ArrayList;

    public class DisplayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display);
            Intent intent = getIntent();
            String url = intent.getExtras.getString("StringUrl");
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
            //ExperimentClass task = new ExperimentClass();
            //task.execute(url);
        }
        
        public Loader<ArrayList<Book>> onCreateLoader(int i, Bundle bundle) {
        // TODO: Create a new loader for the given URL
        Loader<ArrayList<Earthquake>> loader = new EarthquakeLoader(this,queryString);
        return loader;
    }
        
        public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> bookList) {
        if (bookList != null && !bookList.isEmpty()) {
            displayMethod(bookList);
        }
        // TODO: Update the UI with the result
    }
        
        public void onLoaderReset(Loader<ArrayList<Book>> loader) {
        bookAdapter.clear();
        // TODO: Loader reset, so we can clear out our existing data.
    }

        public void displayMethod(ArrayList<Book> bookList){
            //ArrayList<Book> bookList = new ArrayList<Book>();
            //bookList.add(new Book("The idea of justice","Amartya Sen"));
            //bookList.add(new Book("The argumentative Indian","Amartya Sen"));

            bookAdapter booksAdapter = new bookAdapter(this,bookList);
            ListView booksView = (ListView) findViewById(R.id.list);
            booksView.setAdapter(booksAdapter);
        }
        //class ExperimentClass extends AsyncTask<String,Void,ArrayList<Book>>{

        //protected ArrayList<Book> doInBackground(String... urls){
        //    ArrayList<Book> bookList = QueryUtils.fetchBookData(urls[0]);
        //    return bookList;
        //}
        //protected void onPostExecute(ArrayList<Book> bookList){
        //    displayMethod(bookList);
        //}
    //}

