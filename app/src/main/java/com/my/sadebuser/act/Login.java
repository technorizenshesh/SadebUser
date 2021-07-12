package com.my.sadebuser.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.MainActivity;
import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityLoginBinding;


public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        SetupUI();

    }

    private void SetupUI() {
        binding.loginID.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        binding.buttonFotgotAction.setOnClickListener(v -> {
            startActivity(new Intent(this, ForogotPassword.class));
        });

        binding.reg.setOnClickListener(v -> {
            startActivity(new Intent(this, Register.class));
        });
    }
}