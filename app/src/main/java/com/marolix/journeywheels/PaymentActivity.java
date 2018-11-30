package com.marolix.journeywheels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.marolix.journeywheels.utils.Utilities;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ((TextView)findViewById(R.id.text_payment)).setText(getIntent().getStringExtra("CART"));
        ArrayList<String> prod_list = new ArrayList<>();
        prod_list = Utilities.getArrayPreference(this, "ORDER_DETAILS");
        Log.e("Prod_details", prod_list.toString());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Payment");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
