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
import com.manddprojectconsulant.greedapplication.databinding.ActivityAdminOptionBinding;

public class AdminOption extends AppCompatActivity {


    ActivityAdminOptionBinding optionBinding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionBinding = ActivityAdminOptionBinding.inflate(getLayoutInflater());
        setContentView(optionBinding.getRoot());

        setSupportActionBar(optionBinding.admintoolbar);


        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        Toast.makeText(this, "Welcome " + sharedPreferences.getString("username", "") + " to E Greeting App", Toast.LENGTH_SHORT).show();


        optionBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();


            }
        });


        optionBinding.actionLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logoutcode();

            }
        });


        optionBinding.CategoryCarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent category = new Intent(AdminOption.this, AdminDashboardActivity.class);
                category.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(category);
                overridePendingTransition(0, 0);


            }
        });
        optionBinding.userlistCarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent category = new Intent(AdminOption.this, UserListActivity.class);
                category.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(category);
                overridePendingTransition(0, 0);


            }
        });


    }

    private void logoutcode() {

        sharedPreferences.edit().clear().commit();
        Intent i = new Intent(AdminOption.this, LoginActivity.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}