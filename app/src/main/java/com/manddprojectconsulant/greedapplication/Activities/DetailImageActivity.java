package com.manddprojectconsulant.greedapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.manddprojectconsulant.greedapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;

public class DetailImageActivity extends AppCompatActivity {

    String diwali_image;
    PhotoView festivalimageview;
    ImageButton downloadbutton,sharebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        diwali_image=getIntent().getStringExtra("diwaliimage");

        //ID's
        festivalimageview=findViewById(R.id.festival_imageview);
        downloadbutton=findViewById(R.id.download_button);
        sharebutton=findViewById(R.id.share_button);

        Picasso.get().load(diwali_image).placeholder(R.drawable.loading).into(festivalimageview, new Callback() {
            @Override
            public void onSuccess() {

                MaterialImageLoading.animate(festivalimageview).setDuration(1000).start();

            }

            @Override
            public void onError(Exception e) {

            }
        });


        //Share Function
        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Picasso.get().load(diwali_image).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                        preparedforshare(bitmap);


                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });


            }
        });



        //Download function
        downloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(diwali_image);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadManager.enqueue(request);
                Toast.makeText(DetailImageActivity.this, "Downloading Start", Toast.LENGTH_SHORT).show();



            }
        });



    }

    private void preparedforshare(Bitmap bitmap) {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(DetailImageActivity.this,bitmap));
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey there" + "shareAppLink");
        shareIntent.setType("image/jpeg");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share Opportunity"));

    }

    public static Uri getImageUri(Context inContext, Bitmap bitmapInImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmapInImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), bitmapInImage, "Greetings", null);
        return Uri.parse(path);
    }
}