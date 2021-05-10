package com.manddprojectconsulant.greedapplication.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityUpdatePasswordBinding;

import java.util.HashMap;
import java.util.Map;

public class UpdatePassword extends AppCompatActivity {

    ActivityUpdatePasswordBinding updatePasswordBinding;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updatePasswordBinding = ActivityUpdatePasswordBinding.inflate(getLayoutInflater());
        setContentView(updatePasswordBinding.getRoot());

        updatePasswordBinding.email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                verifiedEmail();
            }
        });

        updatePasswordBinding.updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password=updatePasswordBinding.password.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, APi.UpdatePassword, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap map=new HashMap();
                      /*  map.put("id", id);
                        map.put("product_name", productnameText.getText().toString().trim());
*/

                        return map;
                    }
                };





            }
        });

    }

    private void verifiedEmail() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.EmailVerfied, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("0")) {

                    updatePasswordBinding.email.setError("Not Match");
                }
                else{
                    updatePasswordBinding.password.setVisibility(View.VISIBLE);
                    updatePasswordBinding.confirmpassword.setVisibility(View.VISIBLE);
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