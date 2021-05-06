package com.manddprojectconsulant.greedapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manddprojectconsulant.greedapplication.Admin.UserListActivity;
import com.manddprojectconsulant.greedapplication.Model.UserModel;
import com.manddprojectconsulant.greedapplication.R;

import java.util.List;

public class Adapterforuserlogin extends RecyclerView.Adapter<Adapterforuserlogin.Myholder> {
    List<UserModel> list;
    Context context;

    public Adapterforuserlogin(UserListActivity context, List<UserModel> list) {

        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.designforuserlist, parent, false);

        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        holder.nameTextView.setText(" Name: "+list.get(position).getName());
        holder.phoneTextView.setText(" Phone No: "+list.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {

       TextView nameTextView,phoneTextView;

        public Myholder(@NonNull View itemView) {
            super(itemView);

            nameTextView=itemView.findViewById(R.id.nametext);
            phoneTextView=itemView.findViewById(R.id.phonetext);

        }
    }
}
