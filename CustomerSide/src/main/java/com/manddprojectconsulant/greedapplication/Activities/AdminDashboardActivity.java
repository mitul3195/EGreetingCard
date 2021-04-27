package com.manddprojectconsulant.greedapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityAdminDashboardBinding;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class AdminDashboardActivity extends AppCompatActivity {

    ActivityAdminDashboardBinding dashboardBinding;
    int SELECT_PICTURE = 200;
    SharedPreferences sharedPreferences;
    Uri filepath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardBinding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(dashboardBinding.getRoot());
        setSupportActionBar(dashboardBinding.admintoolbar);




        //SharedPreference
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        Toast.makeText(this, "Welcome " + sharedPreferences.getString("username", "") + " to E Greeting App", Toast.LENGTH_SHORT).show();

        dashboardBinding.actionLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logoutcode();

            }
        });


        dashboardBinding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });


        dashboardBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();


            }
        });


        dashboardBinding.uploadfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String path = getPath(filepath);
                String name = dashboardBinding.nameText.getText().toString();


                try {
                    new MultipartUploadRequest(AdminDashboardActivity.this, APi.Diwalimageupload)
                            .addFileToUpload(path, "url")
                            .addParameter("name", name)
                            .setMaxRetries(2)
                            .startUpload();

                    Toast.makeText(AdminDashboardActivity.this, "success", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }

    private String getPath(Uri filepath) {

        Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;


    }

    @Override
    public void onBackPressed() {

        finishAffinity();

        super.onBackPressed();
    }


    private void logoutcode() {

        sharedPreferences.edit().clear().commit();
        Intent i = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        startActivity(i);
        finish();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {

                filepath = data.getData();
                if (filepath != null) {
                    // dashboardBinding.cardview.setVisibility(View.VISIBLE);
                    dashboardBinding.photocomefromgallery.setImageURI(filepath);
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}