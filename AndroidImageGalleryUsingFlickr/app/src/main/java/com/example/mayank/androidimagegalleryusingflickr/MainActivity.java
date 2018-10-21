/**
 * MainActivity.java
 * Entry point for application
 */

package com.example.mayank.androidimagegalleryusingflickr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GalleryListAdapter mListAdapter;
    private ArrayList<String> mImageList = null;
//    private Context mContext = null;
    private GridView mGridView = null;
    public ArrayList<String> imagesHeight = new ArrayList<>();
    public ArrayList<String> imagesWidth = new ArrayList<>();
    public ArrayList<String> imagesByteCount = new ArrayList<>();
    public ArrayList<String> imageTitles = new ArrayList<>();
//    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mContext = this;
        mListAdapter = new GalleryListAdapter(this);
        mGridView = findViewById(R.id.imageGridView);
        mGridView.setAdapter(mListAdapter);

        ImageSearchTask imageSearchTask = new ImageSearchTask(this);

        imageSearchTask.execute("https://api.flickr.com/services/rest/?api_key=949e98778755d1982f537d56236bbb42&format=json&nojsoncallback=1&method=flickr.photos.getRecent");

        /**
         * Handler is used in order to delay conversion of image URL to Bitmap by 10 seconds
         * If it starts with ImageSearchTask simultaneously then it will try to fetch imageURL from it
         * which is not yet populated and will throw NullPointerException
         * and eventually force application to crash
         */

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                try {
                    BitMapConverter bitmapConverter = new BitMapConverter(MainActivity.this);

                    bitmapConverter.execute(mImageList);
                } catch(NullPointerException e) {
                    Toast.makeText(MainActivity.this, "Wait while data are being fetched", Toast.LENGTH_SHORT).show();
                }

            }
        }, 20000);  //Delaying by 10 seconds. Enough time for image URLs list to get populated.

        /**
         * GridView OnItemClickListener will show up Dialogue box which will show details about the image like
         * Title of Image
         * Size in bytes
         * Dimensions: Height and Width in pixels
         */
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Creating Dialogue Box for finding image size and dimensions
                try {
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(android.R.drawable.sym_def_app_icon)
                            .setTitle("Title: " + imageTitles.get(i))
                            .setMessage("Size: " + imagesByteCount.get(i) + " bytes\nWidth: " + imagesWidth.get(i) + " pixels" + "\nHeight: " + imagesHeight.get(i) + " pixels")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Data is being processed.\nPlease try again after some time", Toast.LENGTH_LONG).show();
                }
//                    Log.i("URL", mImageList.get(i));
            }
        });

    }

    /**
     * Update the image list and update the view,
     * @param imageList New image list from the search
     */
    public void updateImageList(ArrayList<String> imageList) {

        if (imageList == null || imageList.size() == 0) {
            Toast.makeText(getApplicationContext(), "There are no Images to show", Toast.LENGTH_SHORT).show();
        } else {
            mImageList = imageList;
        }

        Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable() {
            public void run() {

                mListAdapter.notifyDataSetChanged();

            }
        }, 15000);

    }

    /**
     * Updates the list of Imagesize, widths and heights which can be fetched
     * when creating dialogue box when image is clicked
     * @param byteCount
     * @param widths
     * @param heights
     */
    public void updateBitMapList(ArrayList<String> byteCount, ArrayList<String> widths, ArrayList<String> heights) {

        if (widths == null || widths.size() == 0) {
            Toast.makeText(getApplicationContext(), "Values are null", Toast.LENGTH_SHORT).show();
        } else {

            imagesByteCount = byteCount;
            imagesWidth = widths;
            imagesHeight = heights;

        }

    }

    /**
     * Gets ImageTitles from JSON data using Gson object
     * in order to set dialogue box title when specific image is clicked
     * @param imageTitles
     */
    public void getImageTitles(ArrayList<String> imageTitles) {

        if(imageTitles == null && imageTitles.size() > 0) {

            Toast.makeText(getApplicationContext(), "Oops...There is something wrong with the titles", Toast.LENGTH_SHORT).show();

        } else {

            this.imageTitles = imageTitles;

        }

    }

    /**
     * Class that handles Image grid view.
     */
    class GalleryListAdapter extends BaseAdapter {

        private Context mContext = null;

        GalleryListAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return (mImageList == null) ? 0 : mImageList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View gridItemView = null;

            if (convertView == null)
                gridItemView = inflater.inflate(R.layout.grid_view_item, parent, false);
            else
                gridItemView = convertView;

            // We use Picasso to load image URL to ImageView
            if (mImageList != null) {
                ImageView gridItemImageView = gridItemView.findViewById(R.id.item_image);
                Picasso.get().load(mImageList.get(position)).into(gridItemImageView);
            }

            return gridItemView;
        }
    }

}
