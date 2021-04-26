package com.manddprojectconsulant.greedapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    TextInputEditText username_text, password_text;
    ImageView ivSign_button;
    TextView donothaveaccount_text;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Shared Preference
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("s1", false) && !sharedPreferences.getString("uname", "").isEmpty()) {
            Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(i);
            finish();

        }


        if (sharedPreferences.getBoolean("s12", false) && !sharedPreferences.getString("username", "").isEmpty()) {
            Intent admin = new Intent(LoginActivity.this, AdminDashboardActivity.class);
            startActivity(admin);
            finish();

        }


        //ID's
        username_text = findViewById(R.id.username_edittext);
        password_text = findViewById(R.id.password_edittext);
        ivSign_button = findViewById(R.id.signin_image);
        donothaveaccount_text = findViewById(R.id.donothaveaccount_text);

        //Image work as a button

        ivSign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = username_text.getText().toString();
                String password = password_text.getText().toString();


                if (username.equals("Admin") && password.equals("admin@123")) {


                    sharedPreferences.edit().putString("username", username_text.getText().toString()).apply();
                    sharedPreferences.edit().putBoolean("s12", true).apply();
                    Intent i = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                    startActivity(i);


                } else {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.Login, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("TAG", "onResponse: " + response.toString());


                            if (response.trim().equals("0")) {

                                Toast.makeText(LoginActivity.this, "Fail please check username and password", Toast.LENGTH_SHORT).show();

                            } else {


                                Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                                startActivity(i);
                                      }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(LoginActivity.this, "Fault in Internet" + error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }) {


                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            HashMap map = new HashMap();
                            map.put("uname", username_text.getText().toString());
                            map.put("pass", password_text.getText().toString());

                            sharedPreferences.edit().putString("uname", String.valueOf(map)).apply();
                            sharedPreferences.edit().putBoolean("s1", true).apply();

                            return map;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(stringRequest);


                }


            }
        });

        //Go registration Page
        donothaveaccount_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registration);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}