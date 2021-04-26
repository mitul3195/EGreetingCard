package com.manddprojectconsulant.greedapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manddprojectconsulant.greedapplication.Adapter.AdapterforCategory;
import com.manddprojectconsulant.greedapplication.Model.ModelforCategory;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    RecyclerView recyclerviewforgallery;
    List<ModelforCategory> list = new ArrayList<>();
    AdapterforCategory adapterforDashboard;
    SharedPreferences sharedPreferences;
    ActivityDashboardBinding dashboardBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(dashboardBinding.getRoot());


        setTitle("E-Greeting");
        setSupportActionBar(dashboardBinding.toolbar);


        //SharedPreference
        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        Toast.makeText(this, "Username" + sharedPreferences.getString("uname", ""), Toast.LENGTH_SHORT).show();

        //ID's
        GridLayoutManager layoutManager = new GridLayoutManager(DashboardActivity.this, 2);
        dashboardBinding.recyclerviewforgallery.setLayoutManager(layoutManager);

        dashboardBinding.actionlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logoutcode();

            }
        });



        dashboardBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();


            }
        });



        getAllData();


    }


    @Override
    public void onBackPressed() {

        finishAffinity();

        super.onBackPressed();
    }




    private void getAllData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.View, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray array = null;
                try {
                    array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        ModelforCategory modelforDashboard = new ModelforCategory();
                        modelforDashboard.setTitle(object.getString("name"));
                        modelforDashboard.setImageforthumbail(object.getString("image"));
                        list.add(modelforDashboard);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                adapterforDashboard = new AdapterforCategory(DashboardActivity.this, list);
                dashboardBinding.recyclerviewforgallery.setAdapter(adapterforDashboard);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue queue = Volley.newRequestQueue(DashboardActivity.this);
        queue.add(stringRequest);


    }


    private void logoutcode() {

        sharedPreferences.edit().clear().commit();
        Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(i);
        finish();


    }
}