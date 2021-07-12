package com.my.sadebuser.act;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityUpdatePasswordBinding;

public class UpdatePassword extends AppCompatActivity {

    ActivityUpdatePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_password);

        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}