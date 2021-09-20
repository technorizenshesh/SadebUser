package com.my.sadebuser.act.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.network.NetworkConstraint;
 import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.RequestLAuthentication;
import com.my.sadebuser.databinding.ActivityUpdatePhoneNumberBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePhoneNumber extends AppCompatActivity {

    private ActivityUpdatePhoneNumberBinding binding;
    private ResponseAuthentication model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_phone_number);

        SetupUI();
          model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
          binding.etNo.setText(model.getResult().getMobile());
        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void SetupUI() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                if (binding.etNo.getText().toString().replace(" ", "").length() == 12){
                    RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                            .create(RequestLAuthentication.class)
                            .getProfileUpdate(model.getResult().getUserName(),model.getResult().getEmail(),
                                    binding.etNo.getText().toString(),model.getResult().getId(),
                                    model.getResult().getGender())
                            .enqueue(new Callback<ResponseAuthentication>() {
                                @Override
                                public void onResponse(Call<ResponseAuthentication> call, Response<ResponseAuthentication> response) {

                                    if (response.isSuccessful()){
                                        binding.loaderLayout.loader.setVisibility(View.GONE);
                                        SharedPrefsManager.getInstance().setObject(SharePrefrenceConstraint.user, response.body());
                                        finish();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseAuthentication> call, Throwable t) {
                                    binding.loaderLayout.loader.setVisibility(View.GONE);

                                }
                            });




                }else {
                    binding.loaderLayout.loader.setVisibility(View.GONE);
                    Snackbar.make(findViewById(android.R.id.content),
                            R.string.text_register_right_no,
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}