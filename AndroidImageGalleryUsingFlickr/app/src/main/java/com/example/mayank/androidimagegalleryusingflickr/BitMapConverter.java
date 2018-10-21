/**
 *
 *BitMapConverter.java
 * AsyncTask which converts imageURL to bitmap seperately from main thread
 * which is used to get size and dimensions(height & width) of image
 * and update those related lists in the main thread
 */

package com.example.mayank.androidimagegalleryusingflickr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BitMapConverter extends AsyncTask<ArrayList<String>, Void, Void> {

//    ArrayList<Bitmap> bitmapArrayList;
    ArrayList<String> widths = new ArrayList<>();
    ArrayList<String> heights = new ArrayList<>();
    ArrayList<String> byteCounts = new ArrayList<>();

    private MainActivity mMainActivity = null;

    BitMapConverter(MainActivity activity)
    {
        mMainActivity = activity;
    }


    @Override
    protected Void doInBackground(ArrayList<String>... imageURLStrings) {

        try {

            for(int i = 0; i < imageURLStrings[0].size(); i++) {

                URL imageURL = new URL(imageURLStrings[0].get(i));
                HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap bmp = BitmapFactory.decodeStream(in);
//                Log.i("ImageNo",String.valueOf(i));

                heights.add(String.valueOf(bmp.getHeight()));               //Updating imageHeight list
                widths.add(String.valueOf(bmp.getWidth()));                 //Updating imageWidth list
                byteCounts.add(String.valueOf(bmp.getByteCount()));         //Updating imageSize list

            }

            mMainActivity.updateBitMapList(byteCounts, widths, heights);    //Updating data into main thread

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        return null;    //We are not returning anything in this AsyncTask hence return type is Void
    }

//    @Override
//    protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
//        super.onPostExecute(bitmaps);
//
//
//        if(bitmaps != null && bitmaps.size() > 0) {
//
//            for(int i = 0; i < bitmaps.size(); i++) {
//
//                heights.add(String.valueOf(bitmaps.get(i).getHeight()));
//                widths.add(String.valueOf(bitmaps.get(i).getWidth()));
//                byteCounts.add(String.valueOf(bitmaps.get(i).getByteCount()));
//                Log.i("Values", "Are Not Null");
//
//            }
//
//        } else {
//            Log.i("Values", "BitMapConverter if Statement");
//        }
//
//
//
//    }
}