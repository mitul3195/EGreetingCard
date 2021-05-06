package com.manddprojectconsulant.greedapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manddprojectconsulant.greedapplication.Adapter.Adapterforuserlogin;
import com.manddprojectconsulant.greedapplication.Model.UserModel;
import com.manddprojectconsulant.greedapplication.PublicApi.APi;
import com.manddprojectconsulant.greedapplication.databinding.ActivityUserListBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    ActivityUserListBinding userListBinding;
    List<UserModel>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userListBinding=ActivityUserListBinding.inflate(getLayoutInflater());
        setContentView(userListBinding.getRoot());
        setSupportActionBar(userListBinding.admintoolbar);


        LinearLayoutManager layoutManager=new LinearLayoutManager(UserListActivity.this);
        userListBinding.userListRecyclerview.setLayoutManager(layoutManager);

        userListBinding.actionBackpressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();


            }
        });


        StringRequest request=new StringRequest(Request.Method.POST, APi.Userlist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++) {
                        JSONObject object = array.getJSONObject(i);
                        UserModel model=new UserModel();
                        model.setName(object.getString("name"));
                        model.setPhone(object.getString("phone"));
                        list.add(model);

                    }

                    Adapterforuserlogin adapterforuserlogin=new Adapterforuserlogin(UserListActivity.this,list);
                    userListBinding.userListRecyclerview.setAdapter(adapterforuserlogin);



                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue= Volley.newRequestQueue(UserListActivity.this);
        queue.add(request);





    }

    @Override
    public void onBackPressed() {

        Intent i=new Intent(UserListActivity.this,AdminOption.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        overridePendingTransition(0,0);
        super.onBackPressed();
    }

}