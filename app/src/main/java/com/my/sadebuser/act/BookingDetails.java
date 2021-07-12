package com.my.sadebuser.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityBookingDetailsBinding;


public class BookingDetails extends AppCompatActivity {

    ActivityBookingDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_booking_details);

        binding.RRBook.setOnClickListener(v -> {

            startActivity(new Intent(BookingDetails.this, PaymentOption.class));

        });


    }
}