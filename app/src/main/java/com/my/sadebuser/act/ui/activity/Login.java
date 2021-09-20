package com.my.sadebuser.act.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.network.NetworkConstraint;
 import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.RequestLAuthentication;
import com.my.sadebuser.databinding.ActivityLoginBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.my.sadebuser.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        SetupUI();
    }

    private void SetupUI() {

        binding.loginID.setOnClickListener(v -> {
            if (validate()){
                Log.i("NBdc", "SetupUI: "+"in");
                binding.loaderLayout.loader.setVisibility(View.VISIBLE);
                RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                        .create(RequestLAuthentication.class)
                        .getlogIn(binding.etEmail.getText().toString(),
                                binding.etPassword.getText().toString(),
                                NetworkConstraint.type)
                        .enqueue(new Callback<JsonElement>() {
                            @Override
                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                binding.loaderLayout.loader.setVisibility(View.GONE);
                                if (response.isSuccessful()){
                                   if(response.body()!=null){
                                       JsonObject object = response.body().getAsJsonObject();
                                       int status = object.get("status").getAsInt();
                                       if(status==1){
                                           SharedPrefsManager.getInstance().setObject(SharePrefrenceConstraint.user,object);
                                           ResponseAuthentication authentication = new Gson().fromJson(object,ResponseAuthentication.class);
                                           Log.i("dscbhs", "onResponse: "+authentication);
                                           startActivity(new Intent(Login.this, MainActivity.class));
                                           finish();
                                       }
                                       else {
                                           ResponseAuthError authentication = new Gson().fromJson(object,ResponseAuthError.class);
                                           Log.i("dscbhs", "onResponse: "+authentication);
                                           Snackbar.make(findViewById(android.R.id.content),
                                                   authentication.getResult(),
                                                   Snackbar.LENGTH_SHORT).show();
                                       }
                                   }

                                }
                            }

                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {
                                binding.loaderLayout.loader.setVisibility(View.GONE);
                                Log.i("vgdxvd", "onFailure: "+t.getMessage());
                                Log.i("vgdxvd", "onFailure: "+t.getMessage());
                            }
                        });



            }
//            else {
//                Snackbar.make(findViewById(android.R.id.content),
//                        R.string.exist,
//                        Snackbar.LENGTH_SHORT).show();
//            }
        });

        binding.buttonFotgotAction.setOnClickListener(v -> {
            startActivity(new Intent(this, ForogotPassword.class));
        });

        binding.reg.setOnClickListener(v -> {
            startActivity(new Intent(this, Register.class));
        });


    }

    private boolean validate() {
        if (TextUtils.isEmpty(binding.etEmail.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.enter_email, Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else if (!Utils.EMAIL_ADDRESS_PATTERN.matcher(binding.etEmail.getText().toString().replace(" ", "")).matches()
        ) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_correct_email,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(binding.etPassword.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Enter Password",
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }else if (binding.etPassword.getText().toString().replace(" ", "").length() < 5) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_password,
                    Snackbar.LENGTH_SHORT).show();
            return false;

        }
       else {
            return true;
        }
    }

}