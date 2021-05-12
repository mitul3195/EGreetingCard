package com.manddprojectconsulant.greedapplication.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manddprojectconsulant.greedapplication.Model.UserModel;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityUpdatePasswordBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdatePassword extends AppCompatActivity {

    ActivityUpdatePasswordBinding updatePasswordBinding;
    String password,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updatePasswordBinding = ActivityUpdatePasswordBinding.inflate(getLayoutInflater());
        setContentView(updatePasswordBinding.getRoot());


        updatePasswordBinding.updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password = updatePasswordBinding.password.getText().toString();

                ViewData();

                verifiedEmail();


            }
        });

    }

    private void ViewData() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, APi.Userlist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray array= null;
                try {
                    array = new JSONArray(response);
                    for (int i=0;i<array.length();i++) {

                        JSONObject object = array.getJSONObject(i);
                        UserModel model=new UserModel();

                        id=object.getString("id");
                        model.setId(id);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdatePassword.this, "Fault in Internet"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(UpdatePassword.this);
        requestQueue.add(stringRequest);


    }

    private void ForgetUpdatePassword() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.UpdatePassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("UpdatePassword",response.toString());
                Toast.makeText(UpdatePassword.this, "Update Done Successfully", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(UpdatePassword.this, "Fault in Internet" + error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap map=new HashMap();
                map.put("id",id);
                map.put("password",password);
                map.put("email",updatePasswordBinding.email.getText().toString());
                return map;


            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(UpdatePassword.this);
        requestQueue.add(stringRequest);

    }

    private void verifiedEmail() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.EmailVerfied, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("0")) {

                    updatePasswordBinding.email.setError("Not Match");
                } else {

                    ForgetUpdatePassword();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(UpdatePassword.this, "Fault in Internet", Toast.LENGTH_SHORT).show();

            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap map = new HashMap();
                map.put("uname", updatePasswordBinding.email.getText().toString());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(UpdatePassword.this);
        queue.add(stringRequest);


    }


}