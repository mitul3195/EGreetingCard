package com.manddprojectconsulant.greedapplication.Activities.FestivalActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manddprojectconsulant.greedapplication.Activities.DashboardActivity;
import com.manddprojectconsulant.greedapplication.Adapter.AdapterforsubCategory;
import com.manddprojectconsulant.greedapplication.Model.SubCategory;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityJanmashtamiGalleryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JanmashtamiGalleryActivity extends AppCompatActivity {

    ActivityJanmashtamiGalleryBinding janmashtamiGalleryBinding;
    List<SubCategory> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        janmashtamiGalleryBinding = ActivityJanmashtamiGalleryBinding.inflate(getLayoutInflater());
        setContentView(janmashtamiGalleryBinding.getRoot());
        setSupportActionBar(janmashtamiGalleryBinding.admintoolbar);



        janmashtamiGalleryBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        janmashtamiGalleryBinding.krishnaRecycler.setLayoutManager(layoutManager);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, APi.KrishnaimageGet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        SubCategory model = new SubCategory();
                        model.setUrl(object.getString("url"));
                        list.add(model);


                    }

                    AdapterforsubCategory adapterforsubCategory = new AdapterforsubCategory(JanmashtamiGalleryActivity.this, list);
                    janmashtamiGalleryBinding.krishnaRecycler.setAdapter(adapterforsubCategory);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(JanmashtamiGalleryActivity.this, "Faults in Internet" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        RequestQueue queue = Volley.newRequestQueue(JanmashtamiGalleryActivity.this);
        queue.add(stringRequest);


    }
    @Override
    public void onBackPressed() {

        Intent i = new Intent(JanmashtamiGalleryActivity.this, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

}