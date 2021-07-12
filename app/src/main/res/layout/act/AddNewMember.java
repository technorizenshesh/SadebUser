package com.example.ubook.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ubook.R;
import com.example.ubook.databinding.ActivityAddNewMemberBinding;

public class AddNewMember extends AppCompatActivity {

    ActivityAddNewMemberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_new_member);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.txtAdd.setOnClickListener(v -> {
            finish();
        });
    }
}