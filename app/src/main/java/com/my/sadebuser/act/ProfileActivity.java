package com.my.sadebuser.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityProfileBinding;


public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        binding.RRBack.setOnClickListener(v -> {

        onBackPressed();

        });

        binding.llName.setOnClickListener(v -> {

            startActivity(new Intent(this, UpdateName.class));
        });

        binding.RREmail.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdateEmail.class));
        });

        binding.RRGender.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdateGender.class));
        });

        binding.RRPhoneNumber.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdatePhoneNumber.class));
        });

        binding.RRchangePassword.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdatePassword.class));
        });
        binding.RRInvite.setOnClickListener(v -> {
            startActivity(new Intent(this, InviteFriends.class));
        });
    }
}