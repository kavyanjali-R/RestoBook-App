package com.example.restobook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Booking extends AppCompatActivity {
    TextView restaurantName,restaurantLocation,restaurantRating,bookingDate,bookingDetails;
    ShapeableImageView restaurantImage;
    NumberPicker numberPicker;
    Button datePicker,confirmBooking;
    int year,month,day,image;
    String currDate,name,location,rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        restaurantName=(TextView) findViewById(R.id.restaurantName);
        restaurantLocation=(TextView)findViewById(R.id.restaurantLocation);
        restaurantRating=(TextView)findViewById(R.id.restaurantRating);
        restaurantImage=findViewById(R.id.restaurantImage);

        name=getIntent().getStringExtra("NAME");
        location=getIntent().getStringExtra("LOCATION");
        rating=getIntent().getStringExtra("RATING");
        image=getIntent().getIntExtra("IMAGE",0);

        restaurantName.setText(name);
        restaurantLocation.setText(location);
        restaurantRating.setText(rating);
        restaurantImage.setImageResource(image);


        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        final int[] currGuests = {1};



        bookingDate = (TextView) findViewById(R.id.bookingDate);
        Calendar calendar = Calendar.getInstance();
        final String[] currDate = {DateFormat.getDateInstance().format(calendar.getTime())};
        bookingDate.append(currDate[0]);

        bookingDetails=(TextView) findViewById(R.id.bookingDetails);
        bookingDetails.append("\nDate : "+ currDate[0] +"\nNumber of Guests : "+ currGuests[0]);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                currGuests[0] =i1;
                bookingDetails.setText("Booking Details\nDate : "+ currDate[0] +"\nNumber of Guests : "+i1);
            }
        });

        datePicker=(Button) findViewById(R.id.datePickerButton);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        currDate[0] =getMonthFormat(i1+1)+" "+i2+", "+i;
                        bookingDetails.setText("Booking Details\nDate : "+ currDate[0] +"\nNumber of Guests : "+ currGuests[0]);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

        confirmBooking=(Button) findViewById(R.id.confirmBooking);
        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Booking.this, Reservations.class);
                intent.putExtra("ACTIVITY","Booking");
                intent.putExtra("NAME",name);
                intent.putExtra("IMAGE",image);
                intent.putExtra("DATE", currDate[0]);
                intent.putExtra("GUESTS", currGuests[0]);

                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Booking Has Been Confirmed", Toast.LENGTH_LONG).show();
            }
        });

    }

    private String getMonthFormat(int month) {
        switch(month) {
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
        }
        return null;
    }

}