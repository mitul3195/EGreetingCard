package com.manddprojectconsulant.greedapplication.Activities.FestivalActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;
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
import com.manddprojectconsulant.greedapplication.databinding.ActivityDiwaliGalleryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiwaliGalleryActivity extends AppCompatActivity {


    ActivityDiwaliGalleryBinding diwaliGalleryBinding;
    List<SubCategory>list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diwaliGalleryBinding=ActivityDiwaliGalleryBinding.inflate(getLayoutInflater());
        setContentView(diwaliGalleryBinding.getRoot());


        LinearLayoutManager layoutManager=new GridLayoutManager(this,2);
        diwaliGalleryBinding.diwaliRecycler.setLayoutManager(layoutManager);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, APi.Diwali, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        SubCategory model=new SubCategory();
                        model.setUrl(object.getString("url"));
                        model.setId(object.getString("id"));
                        list.add(model);

                       // Toast.makeText(DiwaliGalleryActivity.this, ""+model.getId()+model.getImage(), Toast.LENGTH_SHORT).show();

                    }



                    AdapterforsubCategory adapterforsubCategory=new AdapterforsubCategory(DiwaliGalleryActivity.this,list);
                    diwaliGalleryBinding.diwaliRecycler.setAdapter(adapterforsubCategory);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(DiwaliGalleryActivity.this, "Faults in Internet"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        RequestQueue queue= Volley.newRequestQueue(DiwaliGalleryActivity.this);
        queue.add(stringRequest);














    }
}