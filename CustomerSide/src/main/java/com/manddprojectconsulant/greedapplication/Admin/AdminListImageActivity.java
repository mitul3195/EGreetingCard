package com.manddprojectconsulant.greedapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manddprojectconsulant.greedapplication.Adapter.AdapterforAdmin;
import com.manddprojectconsulant.greedapplication.Model.SubCategory;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityAdminListImageBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminListImageActivity extends AppCompatActivity {

    ActivityAdminListImageBinding activityAdminListImageBinding;
    List<SubCategory> list = new ArrayList<>();
    String christmasname, diwaliname, holiname, krishname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminListImageBinding = ActivityAdminListImageBinding.inflate(getLayoutInflater());
        setContentView(activityAdminListImageBinding.getRoot());

        LinearLayoutManager manager = new LinearLayoutManager(AdminListImageActivity.this);
        activityAdminListImageBinding.listforadminimage.setLayoutManager(manager);


        activityAdminListImageBinding.listtext.setText("List Data");


        //Christmas Data

        christmasname = getIntent().getExtras().getString("christmas");

        if (christmasname != null) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.MerryimageGet, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("TAG", "onResponse: " + response.toString());


                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            SubCategory model = new SubCategory();
                            model.setUrl(object.getString("url"));
                            model.setName(object.getString("name"));
                            list.add(model);

                            // Toast.makeText(DiwaliGalleryActivity.this, ""+model.getId()+model.getImage(), Toast.LENGTH_SHORT).show();

                        }


                        AdapterforAdmin adapterforAdmin = new AdapterforAdmin(getApplicationContext(), list);
                        activityAdminListImageBinding.listforadminimage.setAdapter(adapterforAdmin);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AdminListImageActivity.this, "Fault in Internet" + error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

            RequestQueue queue = Volley.newRequestQueue(AdminListImageActivity.this);
            queue.add(stringRequest);


        }


        //Diwali Data
        diwaliname = getIntent().getExtras().getString("diwali");


        if (diwaliname != null) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.DiwalimageGet, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            SubCategory model = new SubCategory();
                            model.setUrl(object.getString("url"));
                            model.setName(object.getString("name"));
                            list.add(model);

                            // Toast.makeText(DiwaliGalleryActivity.this, ""+model.getId()+model.getImage(), Toast.LENGTH_SHORT).show();

                        }


                        AdapterforAdmin adapterforAdmin = new AdapterforAdmin(getApplicationContext(), list);
                        activityAdminListImageBinding.listforadminimage.setAdapter(adapterforAdmin);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AdminListImageActivity.this, "Fault in Internet" + error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

            RequestQueue queue = Volley.newRequestQueue(AdminListImageActivity.this);
            queue.add(stringRequest);


        }


        activityAdminListImageBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


        //Holi Data

        holiname = getIntent().getExtras().getString("holi");
        if (holiname != null) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.HoliimageGet, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            SubCategory model = new SubCategory();
                            model.setUrl(object.getString("url"));
                            model.setName(object.getString("name"));
                            list.add(model);

                            // Toast.makeText(DiwaliGalleryActivity.this, ""+model.getId()+model.getImage(), Toast.LENGTH_SHORT).show();

                        }


                        AdapterforAdmin adapterforAdmin = new AdapterforAdmin(getApplicationContext(), list);
                        activityAdminListImageBinding.listforadminimage.setAdapter(adapterforAdmin);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AdminListImageActivity.this, "Fault in Internet" + error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

            RequestQueue queue = Volley.newRequestQueue(AdminListImageActivity.this);
            queue.add(stringRequest);


        }


        //Krishna Data


       krishname=getIntent().getExtras().getString("a");
        if (krishname != null) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.Krishna, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            SubCategory model = new SubCategory();
                            model.setUrl(object.getString("url"));
                            model.setName(object.getString("name"));
                            list.add(model);

                            // Toast.makeText(DiwaliGalleryActivity.this, ""+model.getId()+model.getImage(), Toast.LENGTH_SHORT).show();

                        }


                        AdapterforAdmin adapterforAdmin = new AdapterforAdmin(getApplicationContext(), list);
                        activityAdminListImageBinding.listforadminimage.setAdapter(adapterforAdmin);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AdminListImageActivity.this, "Fault in Internet" + error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

            RequestQueue queue = Volley.newRequestQueue(AdminListImageActivity.this);
            queue.add(stringRequest);


        }


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}