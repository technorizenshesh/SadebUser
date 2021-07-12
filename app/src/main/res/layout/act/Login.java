package com.example.ubook.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        SetupUI();

    }

    private void SetupUI() {
        binding.loginID.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        binding.reg.setOnClickListener(v -> {
            startActivity(new Intent(this, Register.class));
        });
    }
}