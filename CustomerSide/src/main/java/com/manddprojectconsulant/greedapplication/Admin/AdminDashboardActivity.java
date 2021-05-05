package com.manddprojectconsulant.greedapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.manddprojectconsulant.greedapplication.Activities.LoginActivity;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityAdminDashboardBinding;

public class AdminDashboardActivity extends AppCompatActivity {

    ActivityAdminDashboardBinding activityAdminDashboardBinding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminDashboardBinding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityAdminDashboardBinding.getRoot());
        setSupportActionBar(activityAdminDashboardBinding.admintoolbar);


        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        Toast.makeText(this, "Welcome " + sharedPreferences.getString("username", "") + " to E Greeting App", Toast.LENGTH_SHORT).show();


        activityAdminDashboardBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();


            }
        });


        activityAdminDashboardBinding.actionLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logoutcode();

            }
        });


        activityAdminDashboardBinding.diwaliCarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent diwali = new Intent(AdminDashboardActivity.this, AdminDiwaliImageUploadActivity.class);
                diwali.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(diwali);
                overridePendingTransition(0, 0);



            }
        });

        activityAdminDashboardBinding.holiCarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent holi = new Intent(AdminDashboardActivity.this, AdminHoliImageUploadActivity.class);
                holi.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(holi);
                overridePendingTransition(0, 0);


            }
        });


        activityAdminDashboardBinding.krishnaCarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent krishna = new Intent(AdminDashboardActivity.this, AdminKrishnaImageUploadActivity.class);
                krishna.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(krishna);
                overridePendingTransition(0, 0);


            }
        });


        activityAdminDashboardBinding.christmasCarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent christmas = new Intent(AdminDashboardActivity.this, AdminChrstmasImageUploadActivity.class);
                christmas.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(christmas);
                overridePendingTransition(0, 0);


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

    }
}