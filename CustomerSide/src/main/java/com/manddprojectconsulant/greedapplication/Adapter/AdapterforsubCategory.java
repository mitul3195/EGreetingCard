package com.manddprojectconsulant.greedapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.manddprojectconsulant.greedapplication.Activities.DetailImageActivity;
import com.manddprojectconsulant.greedapplication.Activities.FestivalActivity.DiwaliGalleryActivity;
import com.manddprojectconsulant.greedapplication.Model.SubCategory;
import com.manddprojectconsulant.greedapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterforsubCategory extends RecyclerView.Adapter<AdapterforsubCategory.Myholder> {

    List<SubCategory>list;
    Context context;

    public AdapterforsubCategory(Context context, List<SubCategory> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.designforsubcategory,parent,false);

        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

       Picasso.get().load(list.get(position).getUrl())
               .placeholder(R.drawable.loading)
               .error(R.drawable.ic_launcher_background)
               .into(holder.imageforsubcategory);


        holder.imageforsubcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent detailImage=new Intent(context, DetailImageActivity.class);
                detailImage.putExtra("diwaliimage",list.get(position).getUrl());
                context.startActivity(detailImage);


            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {

        ImageView imageforsubcategory;
        public Myholder(@NonNull View itemView) {
            super(itemView);


            imageforsubcategory=itemView.findViewById(R.id.imageforsubcategory);


        }
    }
}
