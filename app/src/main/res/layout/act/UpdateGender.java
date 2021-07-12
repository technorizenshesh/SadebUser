package com.example.ubook.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityUpdateGenderBinding;

public class UpdateGender extends AppCompatActivity {

    ActivityUpdateGenderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_gender);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}