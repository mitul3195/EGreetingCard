package com.manddprojectconsulant.greedapplication.Activities.FestivalActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.manddprojectconsulant.greedapplication.Admin.AdminDashboardActivity;
import com.manddprojectconsulant.greedapplication.Admin.AdminHoliImageUploadActivity;
import com.manddprojectconsulant.greedapplication.Model.SubCategory;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityChristmasGalleryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChristmasGalleryActivity extends AppCompatActivity {

    ActivityChristmasGalleryBinding christmasGalleryBinding;
    List<SubCategory>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        christmasGalleryBinding=ActivityChristmasGalleryBinding.inflate(getLayoutInflater());
        setContentView(christmasGalleryBinding.getRoot());
        setSupportActionBar(christmasGalleryBinding.admintoolbar);





        christmasGalleryBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                onBackPressed();


            }
        });


        LinearLayoutManager layoutManager=new GridLayoutManager(this,2);
        christmasGalleryBinding.christmasRecycler.setLayoutManager(layoutManager);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, APi.MerryimageGet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray array= null;
                try {
                    array = new JSONArray(response);

                for (int i=0;i<array.length();i++)
                {
                    JSONObject object=array.getJSONObject(i);
                    SubCategory subCategory=new SubCategory();
                    subCategory.setUrl(object.getString("url"));
                    list.add(subCategory);

                }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                AdapterforsubCategory adapterforsubCategory=new AdapterforsubCategory(ChristmasGalleryActivity.this,list);
                christmasGalleryBinding.christmasRecycler.setAdapter(adapterforsubCategory);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ChristmasGalleryActivity.this, "Fault in Internet"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue= Volley.newRequestQueue(ChristmasGalleryActivity.this);
        queue.add(stringRequest);





    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(ChristmasGalleryActivity.this, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }



}