package com.example.ubook.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityGroupBookingBinding;

public class GroupBooking extends AppCompatActivity {

    ActivityGroupBookingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_group_booking);

        binding.txtAddNewMember.setOnClickListener(v -> {
            Intent i = new Intent(GroupBooking.this, AddNewMember.class);
            startActivity(i);
        });

        binding.txtSave.setOnClickListener(v -> {
            Intent i = new Intent(GroupBooking.this, MainActivity.class);
            startActivity(i);
        });
    }
}