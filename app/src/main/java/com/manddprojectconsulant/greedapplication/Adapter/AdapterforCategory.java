package com.manddprojectconsulant.greedapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.manddprojectconsulant.greedapplication.Activities.DashboardActivity;
import com.manddprojectconsulant.greedapplication.Activities.FestivalActivity.DiwaliGalleryActivity;
import com.manddprojectconsulant.greedapplication.Activities.FestivalActivity.ChristmasGalleryActivity;
import com.manddprojectconsulant.greedapplication.Activities.FestivalActivity.HoliGalleryActivity;
import com.manddprojectconsulant.greedapplication.Activities.FestivalActivity.JanmashtamiGalleryActivity;
import com.manddprojectconsulant.greedapplication.Model.ModelforCategory;
import com.manddprojectconsulant.greedapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterforCategory extends RecyclerView.Adapter<AdapterforCategory.Myholder> {

    List<ModelforCategory> list;
    Context context;

    public AdapterforCategory(DashboardActivity context, List<ModelforCategory> list) {

        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.designforvideo, parent, false);

        return new Myholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {


        //holder.titleView.setHint(list.get(position).getTitle());
        Picasso.get()
                .load(list.get(position).getImageforthumbail())
                .into(holder.imageviewforthumbail, new Callback() {
                    @Override
                    public void onSuccess() {
                        MaterialImageLoading.animate(holder.imageviewforthumbail).setDuration(2000).start();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


        holder.imageviewforthumbail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = list.get(position).getTitle();


                if (title.equals("Happy Diwali")) {

                    Intent diwali = new Intent(context, DiwaliGalleryActivity.class);
                    context.startActivity(diwali);

                }
                if (title.equals("Happy Holi")) {

                    Intent holi = new Intent(context, HoliGalleryActivity.class);
                    context.startActivity(holi);


                }
                if (title.equals("Merry Christmas")) {

                    Intent christmas = new Intent(context, ChristmasGalleryActivity.class);
                    context.startActivity(christmas);

                }
                if (title.equals("Happy Janmashtami")) {
                    Intent krishna = new Intent(context, JanmashtamiGalleryActivity.class);
                    context.startActivity(krishna);


                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {

        ImageView imageviewforthumbail;
        // TextView titleView;

        public Myholder(@NonNull View itemView) {
            super(itemView);

            imageviewforthumbail = itemView.findViewById(R.id.imageforthumbnail);
            //titleView = itemView.findViewById(R.id.title_text);


        }
    }


}
