package com.marolix.journeywheels.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marolix.journeywheels.R;
import com.marolix.journeywheels.bean.Cart;
import com.marolix.journeywheels.interfaces.ClickListener;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {

    String[] prod_name;
    String[] prod_price;
    int[] images;
    Context context;
    private ClickListener clickListener;
    private ArrayList<Cart> data = new ArrayList<>();

    public CartListAdapter(Context productActivity, ArrayList<Cart> data, ClickListener clickListener) {
        context = productActivity;
        this.clickListener = clickListener;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cart_list, viewGroup, false);

//        view.setOnClickListener(MainActivity.myOnClickListener);

        return new MyViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(data.get(i).getItemName());
        myViewHolder.count.setText(data.get(i).getItemCount());
        myViewHolder.single_price.setText(data.get(i).getItemPrice());

        int item_price = Integer.parseInt(data.get(i).getItemPrice()) * (Integer.parseInt(data.get(i).getItemCount()));
        myViewHolder.price.setText(String.valueOf(item_price));
        /*if (Integer.parseInt( data.get(i).getItemCount()) == 0){
            myViewHolder.layout_cart.setVisibility(View.GONE);
        }*/
//        myViewHolder.prod_unit.setText("1");
//        myViewHolder.price.setText(prod_price[i]);
//        myViewHolder.quantity.setText("1");
       /* myViewHolder.recycler_view_list.setHasFixedSize(true);
        myViewHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        */
//       myViewHolder.prod_image.setImageResource(images[i]);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView plus, minus;
        TextView count, name, single_price, price;
        CardView layout_cart;

        int sum;
        int single_item_price;

        public MyViewHolder(@NonNull View itemView, final ClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            count = itemView.findViewById(R.id.tv_quantity);
            layout_cart = itemView.findViewById(R.id.layout_cart);
            plus = itemView.findViewById(R.id.item_add);
            minus = itemView.findViewById(R.id.item_sub);
            single_price = itemView.findViewById(R.id.single_price);


           /* name = itemView.findViewById(R.id.item_name);
            productName = itemView.findViewById(R.id.rest_name);
            *//*prod_unit = itemView.findViewById(R.id.tv_unit);
            price = itemView.findViewById(R.id.tv_price);
            quantity = itemView.findViewById(R.id.tv_quantity);*//*
            prod_image = itemView.findViewById(R.id.img_prod);
            prod_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductActivity.class));
                }
            });*/

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sum = Integer.parseInt(count.getText().toString());
                    single_item_price = Integer.parseInt(single_price.getText().toString());

                    sum = sum + 1;
                    Log.e("Quantity value2", "" + sum);
                    if (sum > 0) {
                        count.setVisibility(View.VISIBLE);
                        minus.setVisibility(View.VISIBLE);
                        price.setVisibility(View.VISIBLE);
                    }
                    count.setText(String.valueOf(sum));

                    price.setText("\u20B9 " +(single_item_price * sum));
                    listener.itemClick(view, getAdapterPosition(), sum);
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sum = Integer.parseInt(count.getText().toString());
                    single_item_price = Integer.parseInt(single_price.getText().toString());

                    if (sum > 0) {
                        sum = sum - 1;
                        if (sum == 0) {
                            count.setVisibility(View.INVISIBLE);
                            minus.setVisibility(View.INVISIBLE);
                            price.setVisibility(View.GONE);
                        } else {
                            price.setVisibility(View.VISIBLE);
                        }
                        count.setText(String.valueOf(sum));
                        price.setText("\u20B9 " +(single_item_price * sum));
                        listener.itemClick(view, getAdapterPosition(), sum);
                    }
                }
            });
        }
    }
}
