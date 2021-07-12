package com.example.ubook.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityPaymentOptionBinding;

public class PaymentOption extends AppCompatActivity {

    ActivityPaymentOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment_option);

        binding.RRCreditCard.setOnClickListener(v -> {


        });

        binding.RRBankTransFr.setOnClickListener(v -> {


        });

    }
}