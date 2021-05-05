package com.manddprojectconsulant.greedapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manddprojectconsulant.greedapplication.Admin.AdminDiwaliImageUploadActivity;
import com.manddprojectconsulant.greedapplication.Model.SubCategory;
import com.manddprojectconsulant.greedapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterforAdmin extends RecyclerView.Adapter<AdapterforAdmin.MyHolder> {

    List<SubCategory>list;
    Context  context;

    public AdapterforAdmin(Context context, List<SubCategory> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.designforadminlist,parent,false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        holder.adminlist_text.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getUrl()).into(holder.adminlist_imageview);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView adminlist_text;
        ImageView adminlist_imageview;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            adminlist_text=itemView.findViewById(R.id.adminlist_text);
            adminlist_imageview=itemView.findViewById(R.id.adminlist);



        }
    }
}
