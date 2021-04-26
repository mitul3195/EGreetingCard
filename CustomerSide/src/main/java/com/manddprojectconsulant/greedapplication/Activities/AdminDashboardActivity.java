package com.manddprojectconsulant.greedapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityAdminDashboardBinding;

public class AdminDashboardActivity extends AppCompatActivity {

    ActivityAdminDashboardBinding dashboardBinding;
    int SELECT_PICTURE = 200;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardBinding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(dashboardBinding.getRoot());
        setSupportActionBar(dashboardBinding.admintoolbar);






        //SharedPreference
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        Toast.makeText(this, "Welcome " + sharedPreferences.getString("username","")+" to E Greeting App", Toast.LENGTH_SHORT).show();

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




            }
        });



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

                Uri image = data.getData();
                if (image != null) {
                    dashboardBinding.cardview.setVisibility(View.VISIBLE);
                    dashboardBinding.photocomefromgallery.setImageURI(image);
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}