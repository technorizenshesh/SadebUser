package com.example.ubook.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityUpdatePhoneNumberBinding;

public class UpdatePhoneNumber extends AppCompatActivity {

    ActivityUpdatePhoneNumberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_phone_number);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}