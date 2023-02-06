package com.example.finalprojectandroid.Adopters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectandroid.Models.MainModel;
import com.example.finalprojectandroid.R;
import com.example.finalprojectandroid.detailActivity;

import java.util.ArrayList;

public class MainAdopter extends RecyclerView.Adapter<MainAdopter.viewHolder>{

    ArrayList<MainModel> list;
    Context context;

    public MainAdopter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.medicine_items_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final MainModel model = list.get(position);
        holder.medicineImage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailActivity.class);
                 /*
                id = 0
                name = 1
                phone 2
                price 3
                image 4
                quantity 5
                description 6
                medicineName 7
                */
                intent.putExtra("image",model.getImage());
                intent.putExtra("name",model.getName());
                intent.putExtra("desc",model.getDescription());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView medicineImage;
        TextView mainName,price, description;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            medicineImage = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.orderPrice);
            description = itemView.findViewById(R.id.description);

        }
    }
}
