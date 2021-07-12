package com.my.sadebuser.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityUpdateNameBinding;

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