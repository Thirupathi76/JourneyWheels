package com.marolix.journeywheels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.marolix.journeywheels.adapter.BikeBookAdapter;
import com.marolix.journeywheels.database.DatabaseCart;
import com.marolix.journeywheels.interfaces.ClickListener;

public class BikeBookListActivity extends AppCompatActivity {

    LinearLayout bottom_cart_layout;
    TextView text_cart;
    private int total;
    String[] prod_name = {"Duke", "RE", " FZ", "Pulsar", "Activa", "Maestro","Dio"};
    String[] prod_name_bike = {"Duke", "RE", " FZ", "Pulsar", "Activa", "Maestro","Dio"};
    String[] prod_name_cycle = {"Hero", "Atlas", "Hero", "Atlas", "Hero", "Atlas","Hero"};
    String[] prod_price = {"120", "150", "120", "100", "100", "100", "100"};
    String[] prod_price_bike = {"120", "150", "120", "100", "100", "100", "100"};
    String[] prod_price_cycle = {"120", "150", "120", "100", "100", "100", "100"};
    int[] images = {R.drawable.bycycle_1, R.drawable.bycycle_1, R.drawable.bycycle_1, R.drawable.bycycle_1, R.drawable.bycycle_1,
            R.drawable.bycycle_1, R.drawable.bycycle_1};
    DatabaseCart databaseCart;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        databaseCart = new DatabaseCart(this);

        ((ScrollView) findViewById(R.id.scrollView)).smoothScrollTo(0, 0);
        bottom_cart_layout = findViewById(R.id.bottom_cart_layout);
        text_cart = findViewById(R.id.cart_text);
//        String product_name = getIntent().getStringExtra("PRODUCT_NAME");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Book");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BikeBookAdapter adapter = new BikeBookAdapter(this, prod_name, prod_price, images,
                new ClickListener() {
            @Override
            public void itemClick(View view, int pos, int quantity) {
                if (view.getId() == R.id.img_add) {

                    Log.e("Click ", "databaseCart " + pos);
                    total = total + Integer.parseInt(prod_price[pos]);
                    bottom_cart_layout.setVisibility(View.VISIBLE);
                    text_cart.setText("Add to Cart items \u20B9 " + total);


                    if (!databaseCart.checkIfExists(prod_name[pos])) {

                        databaseCart.addItemsToCart(prod_name[pos], prod_price[pos], String.valueOf(quantity));
                    } else if (quantity == 0){
                        databaseCart.removeItemsFromCart(prod_name[pos]);
                    }
                    else
                        databaseCart.updateCartItems(prod_name[pos], prod_price[pos], String.valueOf(quantity));

                    /*if (quantity == 0){
                        databaseCart.removeItemsFromCart(prod_name[pos]);
                    }*/
                    if (total == 0) {
//                        databaseCart.dropTable();
//                        databaseCart.removeItemsFromCart(prod_name[pos]);
                        bottom_cart_layout.setVisibility(View.GONE);
                    }

                } else if (view.getId() == R.id.img_sub) {
                    Log.e("Click ", "databaseCart " + pos);

                    /*if (quantity == 0){
                        databaseCart.removeItemsFromCart(prod_name[pos]);
                    }*/
//                    databaseCart.removeItemsFromCart(prod_name[pos]);
                    total = total - Integer.parseInt(prod_price[pos]);
                    bottom_cart_layout.setVisibility(View.VISIBLE);
                    text_cart.setText("Add to databaseCart items " + total);
                    if (!databaseCart.checkIfExists(prod_name[pos])) {
                        databaseCart.addItemsToCart(prod_name[pos], prod_price[pos], String.valueOf(quantity));
                    } else if (quantity == 0){
                        databaseCart.removeItemsFromCart(prod_name[pos]);
                    } else
                        databaseCart.updateCartItems(prod_name[pos], prod_price[pos], String.valueOf(quantity));


                    if (total == 0) {
//                        databaseCart.dropTable();
//                        databaseCart.removeItemsFromCart(prod_name[pos]);
                        bottom_cart_layout.setVisibility(View.GONE);
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);
        bottom_cart_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (total == 0) {
//                    databaseCart.removeItemsFromCart(prod_name[0]);
                }
                Intent intent = new Intent(BikeBookListActivity.this, CartActivity.class);
                intent.putExtra("CART", String.valueOf(total));
                startActivity(intent);

            }
        });

        text_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BikeBookListActivity.this, CartActivity.class);
                intent.putExtra("CART", String.valueOf(total));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
