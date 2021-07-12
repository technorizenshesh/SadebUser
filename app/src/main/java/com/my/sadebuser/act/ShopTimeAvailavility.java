package com.my.sadebuser.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityShopTimeAvailavilityBinding;


public class ShopTimeAvailavility extends AppCompatActivity {

    ActivityShopTimeAvailavilityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_time_availavility);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.imgBack.setOnClickListener(v -> {

            onBackPressed();
        });

        binding.RRBook.setOnClickListener(v -> {

          startActivity(new Intent(this, BookingDetails.class));

        });

    }
}