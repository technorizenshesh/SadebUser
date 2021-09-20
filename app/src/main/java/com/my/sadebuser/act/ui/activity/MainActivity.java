package com.my.sadebuser.act.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.my.sadebuser.R;
import com.my.sadebuser.act.ui.Fragment.AccountFragment;
import com.my.sadebuser.act.ui.Fragment.HomeFragment;
import com.my.sadebuser.act.ui.Fragment.MyBookingFragment;
import com.my.sadebuser.act.ui.Fragment.NotificationFragment;
import com.my.sadebuser.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.RRHome.setOnClickListener(view -> {
           /* fragment = new HomeFragment();
            loadFragment(fragment);*/
            fragment = new HomeFragment();
            loadFragment(fragment);

        });

        binding.rrMyBooking.setOnClickListener(v -> {
            loadFragment(new MyBookingFragment());
        });




        binding.RRProfile.setOnClickListener(view -> {

            fragment = new AccountFragment();
            loadFragment(fragment);

        });

        binding.RRNotification.setOnClickListener(v -> {
            fragment = new NotificationFragment();
            loadFragment(fragment);

        });

        fragment = new HomeFragment();
        loadFragment(fragment);
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_homeContainer, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }

    public void finishAct(){
        finish();
    }
}