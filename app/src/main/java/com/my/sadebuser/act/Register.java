package com.my.sadebuser.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.MainActivity;
import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =    DataBindingUtil.setContentView(this, R.layout.activity_register);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });

        SetupUI();
    }

    private void SetupUI() {


    }
}