package com.example.fooddeliveryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    ArrayList<Cart> carts;

    public CartAdapter(Context c, ArrayList<Cart> p){
        context =  c;
        carts=p;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.name.setText(carts.get(position).getName());
            holder.price.setText("Price: "+carts.get(position).getPrice());
            holder.quantity.setText("Quantity: "+carts.get(position).getQuantity());



    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,quantity,price;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.product_name);
            quantity=(TextView) itemView.findViewById(R.id.product_quantity);
            price=(TextView) itemView.findViewById(R.id.product_price);


        }
    }
}
