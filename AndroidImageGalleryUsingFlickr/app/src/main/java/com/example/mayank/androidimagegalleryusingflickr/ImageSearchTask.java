/**
 *
 * ImageSearchTask.java
 * AsyncTask which runs seperately from main thread
 * Get JSON data from provided flickr URL
 * and convert that data into ArrayList of image URL list
 * and update imageURL list of main thread
 *
 */

package com.example.mayank.androidimagegalleryusingflickr;

import android.os.AsyncTask;
//import android.renderscript.ScriptGroup;
//import android.util.Log;
//import android.widget.GridView;
//
//import com.example.mayank.androidimagegalleryusingflickr.PhotoItem;
//import com.example.mayank.androidimagegalleryusingflickr.PhotoListItem;
//import com.example.mayank.androidimagegalleryusingflickr.SearchItem;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.URL;
import java.util.ArrayList;


public class ImageSearchTask extends AsyncTask<String, Void, String> {

    URL url;
    HttpURLConnection httpURLConnection;
    private MainActivity mMainActivity = null;

    ImageSearchTask(MainActivity activity)
    {
        mMainActivity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {

        String result = "";
        try {

            url = new URL(strings[0]);

            httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            InputStreamReader reader = new InputStreamReader(inputStream);

            int data = reader.read();

            while(data != -1) {

                char currentData = (char) data;

                result += currentData;

                data = reader.read();

            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();

            return "Failed";
        }

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //Using Gson for fetching JSON data
        Gson gson = new GsonBuilder().create();

        SearchItem dataList = gson.fromJson(result, SearchItem.class);

        ArrayList<PhotoItem> photoList = dataList.getPhotos().getPhotoList();
        ArrayList<String> imageList = getImageList(photoList);
        mMainActivity.updateImageList(imageList);

    }

    /**
     * Extract each image information to get image url.
     * URL Format: http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg
     * Source of URL format: https://www.flickr.com/services/api/misc.urls.html
     * @param photoList
     * @return
     */
    private ArrayList<String> getImageList(ArrayList<PhotoItem> photoList)
    {
        int n = photoList.size();
        if (n == 0)
            return null;
        ArrayList<String> imageList = new ArrayList<>();
        ArrayList<String> imageTitleList = new ArrayList<>();   //Fetching title for updating Dialogue box on photo click

        //Constructing URL
        for(int i = 0; i < n; i++)
        {
            PhotoItem currItem = photoList.get(i);
            String imageURL = "http://farm" + currItem.getFarm() + ".static.flickr.com/" +
                    currItem.getServer() + "/" + currItem.getId() + "_" + currItem.getSecret() + ".jpg";
            imageTitleList.add(currItem.getTitle());
            imageList.add(imageURL);
        }

        mMainActivity.getImageTitles(imageTitleList);
        return imageList;
    }
}
