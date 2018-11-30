package com.marolix.journeywheels;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import sharukh.sliderdtpicker.SliderDateTimePicker;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView pickUp_date, dropOff_date, calculate_days;
    private SimpleDateFormat sdf = new SimpleDateFormat("d MMM, h aa", Locale.ENGLISH);
    private Calendar start_date ;
    private Spinner sp_current_loc, sp_pickup_loc, sp_drop_off_loc;
    private Button search_vehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_vehicles = findViewById(R.id.search_vehicles);
        search_vehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, BikeBookListActivity.class));
            }
        });
        pickUp_date = findViewById(R.id.pickup_date);
        dropOff_date = findViewById(R.id.dropoff_date);
        calculate_days = findViewById(R.id.calculate_days);

        pickUp_date.setOnClickListener(this);
        dropOff_date.setOnClickListener(this);

        sp_current_loc = findViewById(R.id.spinner_location);
        sp_pickup_loc = findViewById(R.id.spinner_pickup);
        sp_drop_off_loc = findViewById(R.id.spinner_drop_off);

        sp_current_loc.setOnItemSelectedListener(this);
        sp_pickup_loc.setOnItemSelectedListener(this);
        sp_drop_off_loc.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pickup_date:
                SliderDateTimePicker.newInstance()
                        .setOnDateTimeSetListener((new SliderDateTimePicker.OnDateTimeSetListener() {
                            @Override
                            public void onDateTimeSelected(final Calendar startTime) {
                                start_date = startTime;
                                pickUp_date.setText(sdf.format(startTime.getTime()));
                            }
                        }))
                        .setStartLabel("Start Time")
                        .show(getSupportFragmentManager(), "Your wish");

            break;

            case R.id.dropoff_date:
                if (start_date!=null) {
                    SliderDateTimePicker.newInstance()
                            .setStartDate(start_date.getTime())
                            .setOnDateTimeSetListener(new SliderDateTimePicker.OnDateTimeSetListener() {
                                @Override
                                public void onDateTimeSelected(Calendar endTime) {
                                    dropOff_date.setText(sdf.format(endTime.getTime()));
                                    
                                    calculate_days.setText(getDifferenceDays(start_date.getTime(), endTime.getTime())+" Days");
//                                    calculate_days.setText(sdf.format(start_date.getTime()) + " ---to--- " + sdf.format(endTime.getTime()));

                                }
                            })
                            .setStartLabel("Start date")
                            .setEndLabel("End date")
                            .setTimeTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ongoing_dark))
                            .setSelectedDateBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.some_drawable))
                            .setSelectedTimeBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.from_date_bg))
                            .setDoneButtonBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.failure_dark))
                            .show(getSupportFragmentManager(), "Your wish");
                } else {
                    Toast.makeText(this, "Select the Pick up date first", Toast.LENGTH_SHORT).show();
                }

            break;
        }
    }


    public String getDifferenceDays(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        
        return String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner_location:

                break;
            case R.id.spinner_drop_off:

                break;
            case R.id.spinner_pickup:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
