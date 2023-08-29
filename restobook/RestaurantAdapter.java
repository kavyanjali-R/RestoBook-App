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

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    Context context;
    ArrayList<Restaurant> restaurantList;

    public final RecyclerViewInterface recyclerViewInterface;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurantList,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);

        return new MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.restaurantName.setText(restaurant.restaurant_name);
        holder.restaurantLocation.setText(restaurant.restaurant_location);
        holder.restaurantRating.setText(restaurant.restaurant_rating);
        holder.restaurantImage.setImageResource(restaurant.restaurant_image);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView restaurantName,restaurantLocation,restaurantRating;
        ShapeableImageView restaurantImage;

        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            restaurantName=itemView.findViewById(R.id.restaurantName);
            restaurantLocation=itemView.findViewById(R.id.restaurantLocation);
            restaurantRating=itemView.findViewById(R.id.restaurantRating);
            restaurantImage=itemView.findViewById(R.id.restaurantImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null) {
                        int pos=getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.OnItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
