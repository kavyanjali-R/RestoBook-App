package com.example.restobook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    Context context;
    ArrayList<Order> orderList;
    public OrderAdapter(Context context, ArrayList<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_item,parent,false);
        return new OrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.restaurantName.setText(order.restaurantName);
        holder.bookingDate.append(order.bookingDate);
        holder.bookingGuests.append(String.valueOf(order.bookingGuests));
        holder.restaurantImage.setImageResource(order.restaurantImage);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView restaurantName,restaurantLocation,bookingDate,bookingGuests;
        ShapeableImageView restaurantImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName=itemView.findViewById(R.id.restaurantName);
            restaurantLocation=itemView.findViewById(R.id.restaurantLocation);
            bookingDate=itemView.findViewById(R.id.bookingDate);
            bookingGuests=itemView.findViewById(R.id.bookingGuests);
            restaurantImage=itemView.findViewById(R.id.restaurantImage);
        }
    }
}
