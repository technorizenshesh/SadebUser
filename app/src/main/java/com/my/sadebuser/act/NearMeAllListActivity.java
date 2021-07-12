package com.my.sadebuser.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.my.sadebuser.Fragment.AllSaloonFragment;
import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityNearMeAllListBinding;

public class NearMeAllListActivity extends AppCompatActivity {

    ActivityNearMeAllListBinding binding;

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_near_me_all_list);

        fragment = new AllSaloonFragment();
        loadFragment(fragment);
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_homeContainer1, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }

}