package com.my.sadebuser;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.my.sadebuser.Fragment.AccountFragment;
import com.my.sadebuser.Fragment.HomeFragment;
import com.my.sadebuser.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.RRHome.setOnClickListener(view -> {
           /* fragment = new HomeFragment();
            loadFragment(fragment);*/
            fragment = new HomeFragment();
            loadFragment(fragment);

        });
        binding.RRProfile.setOnClickListener(view -> {

            fragment = new AccountFragment();
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
}