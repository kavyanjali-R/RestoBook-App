package com.example.restobook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Reservations extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Order> orderArrayList;
    OrderAdapter myAdapter;
    String name,date;int image,guests;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        orderArrayList = PrefConfig.readListFromPref(getApplicationContext());
        if(orderArrayList == null)
        orderArrayList = new ArrayList<Order>();

        String activity = getIntent().getStringExtra("ACTIVITY");

        if(activity.equals("Booking")) {

            name=getIntent().getStringExtra("NAME");
            date=getIntent().getStringExtra("DATE");
            guests=getIntent().getIntExtra("GUESTS",0);
            image=getIntent().getIntExtra("IMAGE",0);

            orderArrayList.add(new Order(name,date,guests,image));
            PrefConfig.writeListInPref(getApplicationContext(),orderArrayList);
        }
        else {
            Toast.makeText(getBaseContext(), "Fetched Reservations", Toast.LENGTH_SHORT).show();
        }

        myAdapter = new OrderAdapter(this,orderArrayList);
        recyclerView.setAdapter(myAdapter);

        backButton=(ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}