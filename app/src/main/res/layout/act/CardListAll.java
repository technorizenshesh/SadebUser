package com.example.ubook.act;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityCardListAllBinding;

public class CardListAll extends AppCompatActivity {

    ActivityCardListAllBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_card_list_all);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });

   binding.txtCard.setOnClickListener(v -> {

       startActivity(new Intent(this, AddCardPayment.class));
   });

   binding.cardDeetails.setOnClickListener(v -> {

       startActivity(new Intent(this, CardDetailsActivity.class));

   });

    }
}