package com.marolix.journeywheels.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.marolix.journeywheels.R;

import java.util.ArrayList;

public class MyCustomPagerAdapter extends PagerAdapter { //View Pager
    private Context context;
    private LayoutInflater layoutInflater;
    public int i;
    ArrayList<String> images = new ArrayList<>();

    int[] image_bikes = {R.drawable.car_banner, R.drawable.bike_banner, R.drawable.cycle_banner};

    public MyCustomPagerAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return image_bikes.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.home_banners, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
//        imageView.setImageResource(images.get(position));
//        Picasso.with(context).load(images.get(position)).error(R.drawable.noimage).into(imageView);
        imageView.setImageResource(image_bikes[position]);
        container.addView(itemView);


        //listening to image_bikes click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "you clicked image_bikes " + (position + 1), Toast.LENGTH_SHORT).show();
                Log.e("image_bikes length ", String.valueOf(images.size()));
                /*Intent intent = new Intent(context, ImageOpeningActivity.class);
                intent.putStringArrayListExtra(Constants.KEY_ARRAY_IMAGE, images);
                Utilities.setPrefernc(context, Constants.POSITION, String.valueOf(position));
                context.startActivity(intent);*/
            }
        });
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
