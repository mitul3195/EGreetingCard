package com.manddprojectconsulant.greedapplication.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.manddprojectconsulant.greedapplication.databinding.ActivityAdminChrstmasImageUploadBinding;

import net.gotev.uploadservice.MultipartUploadRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminChrstmasImageUploadActivity extends AppCompatActivity {

    ActivityAdminChrstmasImageUploadBinding activityAdminChrstmasImageUploadBinding;
    int SELECT_PICTURE = 200;
    List<SubCategory> list = new ArrayList<>();
    Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminChrstmasImageUploadBinding = ActivityAdminChrstmasImageUploadBinding.inflate(getLayoutInflater());
        setContentView(activityAdminChrstmasImageUploadBinding.getRoot());
        setSupportActionBar(activityAdminChrstmasImageUploadBinding.admintoolbar);

        activityAdminChrstmasImageUploadBinding.christmastext.setText("Christmas Images");

        activityAdminChrstmasImageUploadBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        //gallery image selection
        activityAdminChrstmasImageUploadBinding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });

        //fetch from server
        activityAdminChrstmasImageUploadBinding.getImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent christmas=new Intent(AdminChrstmasImageUploadActivity.this,AdminListImageActivity.class);
                christmas.putExtra("christmas",activityAdminChrstmasImageUploadBinding.christmastext.getText().toString());
                startActivity(christmas);


            }
        });


        activityAdminChrstmasImageUploadBinding.uploadfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String path = getPath(filepath);
                String name = activityAdminChrstmasImageUploadBinding.nameText.getText().toString();


                try {
                    new MultipartUploadRequest(AdminChrstmasImageUploadActivity.this, APi.Merryimageupload)
                            .addFileToUpload(path, "url")
                            .addParameter("name", name)
                            .setMaxRetries(2)
                            .startUpload();

                    Toast.makeText(AdminChrstmasImageUploadActivity.this, "success", Toast.LENGTH_SHORT).show();

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
                    activityAdminChrstmasImageUploadBinding.photocomefromgallery.setImageURI(filepath);
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBackPressed() {

        Intent i=new Intent(AdminChrstmasImageUploadActivity.this,AdminDashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        overridePendingTransition(0,0);
        super.onBackPressed();
    }


}