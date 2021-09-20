package com.my.sadebuser.act.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.databinding.ActivityProfileBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;

    public static final int PICK_IMAGE = 1;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        setProfileData();

        init();

        binding.RRLogout.setOnClickListener(v -> {
            SharedPrefsManager.getInstance().clearPrefs();
            startActivity(new Intent(this, Login.class));
            finish();

        });
        binding.ivBack.setOnClickListener(v -> {
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

        binding.llMain.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdateName.class));
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setProfileData();
    }

    private void setProfileData() {
        ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
        if (model!=null){

            Picasso.get().load(model.getResult().getImage()).placeholder(R.drawable.user_placeholder).into(binding.ivUser);
            binding.tvStatus.setText(model.getResult().getEmail());
//            binding.tvEmail.setText(model.getResult().getEmail());
            binding.tvName.setText(model.getResult().getUserName());
            binding.tvName.setText(model.getResult().getUserName());
            binding.tvNo.setText(model.getResult().getMobile());
            binding.tvGender.setText(model.getResult().getGender());

        }
      }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                binding.ivUser.setImageBitmap(bitmap);
            }
        }

    }

    public void init() {
//        binding.ivUser.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
//
//            }
//        });
    }

}