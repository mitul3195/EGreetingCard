package com.manddprojectconsulant.greedapplication.Activities.FestivalActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manddprojectconsulant.greedapplication.Adapter.AdapterforsubCategory;
import com.manddprojectconsulant.greedapplication.Model.SubCategory;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.R;
import com.manddprojectconsulant.greedapplication.databinding.ActivityHoliGalleryBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class HoliGalleryActivity extends AppCompatActivity {

    ActivityHoliGalleryBinding holiGalleryBinding;
    List<SubCategory>list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        holiGalleryBinding = ActivityHoliGalleryBinding.inflate(getLayoutInflater());
        setContentView(holiGalleryBinding.getRoot());


        LinearLayoutManager layoutManager = new GridLayoutManager(HoliGalleryActivity.this, 2);
        holiGalleryBinding.holiRecycler.setLayoutManager(layoutManager);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, APi.Holi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray array= null;
                try {
                    array = new JSONArray(response);

                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        SubCategory subCategory=new SubCategory();
                        subCategory.setImage(object.getString("image"));
                        list.add(subCategory);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                AdapterforsubCategory adapterforsubCategory=new AdapterforsubCategory(HoliGalleryActivity.this,list);
                holiGalleryBinding.holiRecycler.setAdapter(adapterforsubCategory);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(HoliGalleryActivity.this, "Fault in Internet"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue= Volley.newRequestQueue(HoliGalleryActivity.this);
        queue.add(stringRequest);


    }
}