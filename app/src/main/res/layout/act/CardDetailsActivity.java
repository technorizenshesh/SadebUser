package com.example.ubook.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityCardDetailsBinding;

public class CardDetailsActivity extends AppCompatActivity {

    ActivityCardDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_card_details);

        binding.RRBack.setOnClickListener(v -> {

            onBackPressed();

        });

        binding.txtSave.setOnClickListener(v -> {

            startActivity(new Intent(this, MainActivity.class));

        });

    }
}