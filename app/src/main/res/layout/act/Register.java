package com.example.ubook.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =    DataBindingUtil.setContentView(this, R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        SetupUI();
    }

    private void SetupUI() {
        binding.loginID.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        binding.signiin.setOnClickListener(v -> {
            startActivity(new Intent(this, Login.class));
        });

    }
}