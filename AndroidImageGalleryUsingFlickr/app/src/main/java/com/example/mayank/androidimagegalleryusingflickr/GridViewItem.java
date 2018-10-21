/**
 *
 * GridViewItem.java
 * Creates individual layout for each image gridview item
 *
 */

package com.example.mayank.androidimagegalleryusingflickr;

import android.content.Context;
import android.util.AttributeSet;
//import android.widget.ImageView;

public class GridViewItem extends android.support.v7.widget.AppCompatImageView
{
    public GridViewItem(Context context) {
        super(context);
    }

    public GridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewItem(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
    }

    @Override
    public void onMeasure(int width, int height) {
        // Show grid in square size
        super.onMeasure(width, width);
    }
}