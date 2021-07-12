package com.my.sadebuser.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.my.sadebuser.R;
import com.my.sadebuser.act.ProfileActivity;
import com.my.sadebuser.adapter.HomeCategoryRecyclerViewAdapter;
import com.my.sadebuser.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.sadebuser.databinding.FragmentAccountBinding;
import com.my.sadebuser.model.HomeModel;

import java.util.ArrayList;

public class AccountFragment extends Fragment {

    private Fragment fragment;
    FragmentAccountBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);

        binding.RRProfile.setOnClickListener(v -> {

            startActivity(new Intent(getActivity(), ProfileActivity.class));

        });

        binding.RRpayment.setOnClickListener(v -> {

            //startActivity(new Intent(getActivity(), ProfileActivity.class));

        });

        return binding.getRoot();
    }



    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_homeContainer, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }


}