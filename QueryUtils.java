package com.example.puspakbiswas.books;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Puspak Biswas on 31-05-2018.
 */

public class QueryUtils {
    public static ArrayList<Book> fetchBookData(String urlString) {
        Log.i("kielo", urlString);
        URL url = getURL(urlString);
        Log.i("CameBack","fro get url");
        String JsonResponse = makeHTTPRequest(url);
        ArrayList<Book> bookList = getBookList(JsonResponse);
        Book b = bookList.get(0);
        Log.i("Hererere",b.getTitle());
        return bookList;
    }

    private static URL getURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (java.net.MalformedURLException e) {
            Log.e("QueryUtils", "URL not created properly", e);
        }
        return url;
    }

    private static String makeHTTPRequest(URL url) {
        String JsonResponse = "";
        if(url == null){
            return JsonResponse;
        }
        InputStream bookInputStream = null;
        HttpURLConnection bookConnection = null;
        try {
            bookConnection = (HttpURLConnection) url.openConnection();
            bookConnection.setReadTimeout(3000);
            bookConnection.setConnectTimeout(3000);
            bookConnection.setRequestMethod("GET");
            bookConnection.connect();
            if(bookConnection.getResponseCode() == 200){
                bookInputStream = bookConnection.getInputStream();
                Log.i("HAHAHAHAHA","connection successful");
                JsonResponse = readFromStream(bookInputStream);
                Log.i("BoroShoro",JsonResponse);
            }else{
                Log.e("QueryUtils","Improper response code "+bookConnection.getResponseCode());
            }
        }catch(java.io.IOException e){
            Log.e("QueryUtils","Error while connecting",e);
        }finally {
            if (bookConnection != null){
                bookConnection.disconnect();
            }
        }
        return JsonResponse;
    }

    private static String readFromStream(InputStream bookInputStream){
        StringBuilder bookBuilder = new StringBuilder();
        String line;
        InputStreamReader bookStreamReader = new InputStreamReader(bookInputStream, Charset.forName("UTF-8"));
        BufferedReader bookBufferedReader = new BufferedReader(bookStreamReader);
        try {
            line = bookBufferedReader.readLine();
            while (line != null){
                bookBuilder.append(line);
                line = bookBufferedReader.readLine();
            }
        }catch(IOException e){
            Log.e("QueryUtils","Error while readline"+e);
        }
        return bookBuilder.toString();
    }

    private static ArrayList<Book> getBookList(String JsonResponse) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        try {
            JSONObject bookObject = new JSONObject(JsonResponse);
            JSONArray bookArray = bookObject.getJSONArray("items");
            for(int i=0;i<bookArray.length();i++){
                JSONObject item = bookArray.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                JSONArray author = volumeInfo.getJSONArray("authors");
                ArrayList<String> authorList = new ArrayList<String>();
                for(int j = 0; j< author.length();j++){
                    authorList.add(author.getString(j));
                }
                bookList.add(new Book(title,authorList));
            }
        }catch(JSONException e){
            Log.e("QueryUtils","Error parsing"+e);
        }
        return bookList;
    }
}
