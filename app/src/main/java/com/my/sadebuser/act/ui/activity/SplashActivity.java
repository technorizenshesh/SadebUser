package com.my.sadebuser.act.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    private ImageView iv_Logo;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        finds();

    }

    private static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private void finds() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                 ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
                if (model!=null){
                    Log.i("sfsfdd", "run: "+12);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
                else {
                    Log.i("sfsfdd", "run: "+12342);
                    Intent i = new Intent(SplashActivity.this, ChooseLoginActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, 3000);
    }
}