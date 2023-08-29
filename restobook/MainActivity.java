package com.example.restobook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    TextView currlocation;
    RecyclerView recyclerView;
    ArrayList<Restaurant> restaurantArrayList;
    RestaurantAdapter myAdapter;

    Button bookings;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currlocation = (TextView) findViewById(R.id.currentLocation);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        restaurantArrayList = new ArrayList<Restaurant>();



        restaurantArrayList.add(new Restaurant("JW Marriott","MG Road","4.4",R.drawable.jw));
        restaurantArrayList.add(new Restaurant("Anagha Grand","Uttrahalli","4.7",R.drawable.anagha));
        restaurantArrayList.add(new Restaurant("Ayodhya Upachar","Banashankari","4.4",R.drawable.ayodhya));
        restaurantArrayList.add(new Restaurant("Schezoun Dragon","Basavangudi","4.0",R.drawable.dragon));
        restaurantArrayList.add(new Restaurant("Easy Tiger","MG Road","4.4",R.drawable.easy));
        restaurantArrayList.add(new Restaurant("Glen's Bakehouse","Jayanagar","4.5",R.drawable.glens));
        restaurantArrayList.add(new Restaurant("Goli Vadapav","Nagarbhavi","3.9",R.drawable.goli));
        restaurantArrayList.add(new Restaurant("Istta Cafe","RR nagar","4.9",R.drawable.istta));
        restaurantArrayList.add(new Restaurant("Tipsy Bull","JP Nagar","4.5",R.drawable.tipsybull));
        restaurantArrayList.add(new Restaurant("1947","Bhanashankari","4.4",R.drawable.ninteen));
        restaurantArrayList.add(new Restaurant("No Limmits Lounge","MG Road","4.47",R.drawable.nolimmits));
        restaurantArrayList.add(new Restaurant("Tandoori Spot","RR Nagar","4.6",R.drawable.tandoori));
        restaurantArrayList.add(new Restaurant("Pizza Cafe","Kormangala","4.0",R.drawable.pizza));
        restaurantArrayList.add(new Restaurant("Polar Bear","MG Road","4.4",R.drawable.polar));
        restaurantArrayList.add(new Restaurant("Skyline Pizzeria","JP Nagar","4.8",R.drawable.skyline));
        restaurantArrayList.add(new Restaurant("RR Vatika","RR Nagar","4.1",R.drawable.rrvatika));
        restaurantArrayList.add(new Restaurant("Leela Palace","Church Street","5.0",R.drawable.leelapalace));
        restaurantArrayList.add(new Restaurant("Barbeque Nation","Kanakpura Road","4.5",R.drawable.barbeque));
        restaurantArrayList.add(new Restaurant("Sindhoora Gardenia","Banashankai","4.8",R.drawable.sindhoora));
        restaurantArrayList.add(new Restaurant("MC Donalds","Basavangudi","4.1",R.drawable.mcd));
        restaurantArrayList.add(new Restaurant("My Tea House","BDA complex","4.5",R.drawable.myteahouse));

        myAdapter = new RestaurantAdapter(this,restaurantArrayList,this);
        recyclerView.setAdapter(myAdapter);

        bookings=(Button) findViewById(R.id.bookings);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Reservations.class);
                intent.putExtra("ACTIVITY","MainActivity");
                startActivity(intent);
            }
        });

    }


    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        currlocation.setText(addressList.get(0).getSubLocality());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please Enable Location Services", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getBaseContext(),Booking.class);

        intent.putExtra("NAME",restaurantArrayList.get(position).getRestaurant_name());
        intent.putExtra("LOCATION",restaurantArrayList.get(position).getRestaurant_location());
        intent.putExtra("RATING",restaurantArrayList.get(position).getRestaurant_rating());
        intent.putExtra("IMAGE",restaurantArrayList.get(position).getRestaurant_image());

        startActivity(intent);
    }
}