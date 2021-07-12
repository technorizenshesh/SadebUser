package com.my.sadebuser.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityShopDetailsBinding;


public class ShopDetailsActivity extends AppCompatActivity {

    ActivityShopDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_shop_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.RRBook.setOnClickListener(v -> {

            startActivity(new Intent(this, ShopTimeAvailavility.class));

        });

        binding.imgBack.setOnClickListener(v -> {

            onBackPressed();
        });
    }
}