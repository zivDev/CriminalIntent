package com.zivdev.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.widget.ImageView;

/**
 * Created by ziv on 16.7.4.
 */
public class PictureUtils {
    public static BitmapDrawable getScaledDrawable(Activity a,String path){
        Display display = a.getWindowManager().getDefaultDisplay();
        float destWidth = display.getWidth();
        float destHeigh = display.getHeight();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth = options.outWidth;
        float srcHeigh = options.outHeight;

        int inSampleSize = 1;
        if (srcHeigh>destHeigh||srcWidth>destWidth){
            if (srcWidth>srcHeigh){
                inSampleSize = Math.round(srcHeigh/destHeigh);
            }else {
                inSampleSize = Math.round(srcWidth/destWidth);
            }
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        Bitmap bitmap = BitmapFactory.decodeFile(path,options);

        return new BitmapDrawable(a.getResources(),bitmap);
    }

    public static void cleanImageView(ImageView imageView){
        if (!(imageView.getDrawable() instanceof BitmapDrawable))
            return;

        BitmapDrawable b = (BitmapDrawable)imageView.getDrawable();
        b.getBitmap().recycle();
        imageView.setImageDrawable(null);
    }
}
