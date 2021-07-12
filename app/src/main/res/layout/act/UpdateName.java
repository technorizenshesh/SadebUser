package com.example.ubook.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityUpdateNameBinding;

public class UpdateName extends AppCompatActivity {

    ActivityUpdateNameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_name);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}