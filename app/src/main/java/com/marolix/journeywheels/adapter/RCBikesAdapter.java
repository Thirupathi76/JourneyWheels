package com.marolix.journeywheels.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marolix.journeywheels.BikeBookListActivity;
import com.marolix.journeywheels.R;
import com.marolix.journeywheels.SearchActivity;


public class RCBikesAdapter extends RecyclerView.Adapter<RCBikesAdapter.MyViewHolder> {

    String[] bike_name = {"Duke", "RE", " FZ", "Pulsar", "Activa", "Maestro"};
    String[] bike_price = {"120/hr", "150/hr", "120/hr", "100/hr", "100/hr", "100/hr"};
    int[] images = {R.drawable.bike_duke,R.drawable.bike_duke,R.drawable.bike_duke,
            R.drawable.bike_duke,R.drawable.bike_duke,R.drawable.bike_duke,};
    Context context;
    String type;
    public RCBikesAdapter(Context productActivity, String type) {
        context = productActivity;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bike_horiz_list, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.bike_name.setText(bike_name[i]);
        myViewHolder.bike_place.setText("Vizag");
        myViewHolder.bike_price.setText("\u20B9 "+bike_price[i]);
        if (type.equals("Bikes"))
       myViewHolder.bike_image.setImageResource(R.drawable.bike_duke);
        else if (type.equals("Cycle"))
       myViewHolder.bike_image.setImageResource(R.drawable.bycycle_1);
        else
            myViewHolder.bike_image.setImageResource(R.drawable.car_shift);

        myViewHolder.bike_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, BikeBookListActivity.class);
               context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bike_name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bike_name, bike_place, bike_price;
        ImageView bike_image;
        int sum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bike_image = itemView.findViewById(R.id.bike_image);
            bike_name = itemView.findViewById(R.id.bike_name);
            bike_place = itemView.findViewById(R.id.bike_place);
            bike_price = itemView.findViewById(R.id.bike_price);


        }
    }
}
