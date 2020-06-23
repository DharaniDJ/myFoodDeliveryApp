package com.example.fooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Model> models;

    public MyAdapter(Context c, ArrayList<Model> p){
        context = c;
        models = p;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(models.get(position).getTitle());
        holder.description.setText(models.get(position).getDescription());
        holder.price.setText("Price: "+models.get(position).getPrice());
        Picasso.get().load(models.get(position).getImage()).into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,OpenDetailsActivity.class);
                intent.putExtra("image_name",models.get(position).getTitle());
                intent.putExtra("image_url",models.get(position).getImage());
                intent.putExtra("image_desc",models.get(position).getDescription());
                intent.putExtra("image_price",models.get(position).getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,description,price;
        ImageView imageView;
        public LinearLayout linearLayout;


        public MyViewHolder(@NonNull View itemView ) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description=(TextView) itemView.findViewById(R.id.description);
            price=(TextView) itemView.findViewById(R.id.price);
            imageView = (ImageView) itemView.findViewById(R.id.rImageView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);


        }


    }


}
