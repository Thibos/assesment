package com.example.thabo.bhgjhg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Thabo on 2016/05/02.
 */
public class ImageDownloaderTask extends AsyncTask<String,Void,Bitmap> {

    private final WeakReference<ImageView> imageViewReference;

    public ImageDownloaderTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);

    }

    @Override
    protected Bitmap doInBackground(String... params) {


        return downloadBitmap(params[0]);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(isCancelled()){
            bitmap=null;
        }

        if(imageViewReference !=null){
            ImageView imageView=imageViewReference.get();
            if (imageView !=null){
                if (bitmap !=null){
                    imageView.setImageBitmap(bitmap);

                }else {
                    Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.placeholder);
                    imageView.setImageDrawable(placeholder);
                }
            }
        }
    }

    private Bitmap downloadBitmap(String url){

        HttpURLConnection urlConnection=null;

        try{
            URL uri=new URL(url);
            urlConnection=(HttpURLConnection)uri.openConnection();
            int statusCode=urlConnection.getResponseCode();

            InputStream inputStream=urlConnection.getInputStream();
            if(inputStream !=null){
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                return  bitmap;
            }
        }catch (Exception e){
            urlConnection.disconnect();
            Log.w("ImageDownloader","Error downloading image from " + url);

        }finally {
            if(urlConnection !=null){
                urlConnection.disconnect();
            }
        }

        return null;

    }


}
