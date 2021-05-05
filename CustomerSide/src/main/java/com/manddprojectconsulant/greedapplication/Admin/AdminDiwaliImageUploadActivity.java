package com.manddprojectconsulant.greedapplication.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manddprojectconsulant.greedapplication.Activities.LoginActivity;
import com.manddprojectconsulant.greedapplication.Adapter.AdapterforAdmin;
import com.manddprojectconsulant.greedapplication.Model.SubCategory;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.databinding.ActivityAdminImageuploadBinding;

import net.gotev.uploadservice.MultipartUploadRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminDiwaliImageUploadActivity extends AppCompatActivity {

    ActivityAdminImageuploadBinding activityAdminImageuploadBinding;
    int SELECT_PICTURE = 200;
    List<SubCategory> list = new ArrayList<>();
    Uri filepath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminImageuploadBinding = ActivityAdminImageuploadBinding.inflate(getLayoutInflater());
        setContentView(activityAdminImageuploadBinding.getRoot());


        //SharedPreference


        //gallery image selection
        activityAdminImageuploadBinding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });

        //fetch from server
        activityAdminImageuploadBinding.getImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayoutManager manager = new LinearLayoutManager(AdminDiwaliImageUploadActivity.this);
                activityAdminImageuploadBinding.listforadminimage.setLayoutManager(manager);

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
                            activityAdminImageuploadBinding.listforadminimage.setAdapter(adapterforAdmin);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(AdminDiwaliImageUploadActivity.this, "Fault in Internet" + error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

                RequestQueue queue = Volley.newRequestQueue(AdminDiwaliImageUploadActivity.this);
                queue.add(stringRequest);


            }
        });


        activityAdminImageuploadBinding.uploadfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String path = getPath(filepath);
                String name = activityAdminImageuploadBinding.nameText.getText().toString();


                try {
                    new MultipartUploadRequest(AdminDiwaliImageUploadActivity.this, APi.Diwalimageupload)
                            .addFileToUpload(path, "url")
                            .addParameter("name", name)
                            .setMaxRetries(2)
                            .startUpload();

                    Toast.makeText(AdminDiwaliImageUploadActivity.this, "success", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }

    private String getPath(Uri filepath) {

        Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {

                filepath = data.getData();
                if (filepath != null) {
                    // dashboardBinding.cardview.setVisibility(View.VISIBLE);
                    activityAdminImageuploadBinding.photocomefromgallery.setImageURI(filepath);
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}