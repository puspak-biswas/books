package com.example.puspakbiswas.books;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Puspak Biswas on 31-05-2018.
 */

public class QueryUtils {
    public static void fetchBookData(String urlString) {
        Log.i("kielo", urlString);
        URL url = getURL(urlString);
        Log.i("CameBack","fro get url");
        String JsonResponse = makeHTTPRequest(url);
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
            bookConnection.setReadTimeout(1000);
            bookConnection.setConnectTimeout(1500);
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
}
