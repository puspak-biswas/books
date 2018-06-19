
import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Puspak Biswas on 10-05-2018.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {
    private String URL;

    public BookLoader(Context context, String url) {
        super(context);
        URL = url;
        // TODO: Finish implementing this constructor
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Book> loadInBackground() {
        ArrayList<Book> bookList = null;
        if(URL != null) {
            // Create a fake list of earthquake locations.
            bookList = QueryUtils.fetchBookData(URL);
        }
        return bookList;
        // TODO: Implement this method
    }
}
