package com.example.ubook.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityAddCardPaymentBinding;

public class AddCardPayment extends AppCompatActivity {

    ActivityAddCardPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_card_payment);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.txtSave.setOnClickListener(v -> {
            finish();
        });
    }
}