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
import com.my.sadebuser.adapter.HomeCategoryRecyclerViewAdapter;
import com.my.sadebuser.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.sadebuser.model.HomeModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private Fragment fragment;
    com.my.sadebuser.databinding.FragmentHomeBinding binding;

    HomeSaloonRecyclerViewAdapter mAdapter;
    HomeCategoryRecyclerViewAdapter mAdapterCategory;
    private ArrayList<HomeModel> modelList = new ArrayList<>();
    private ArrayList<HomeModel> modelListCategory = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);


        setAdapter();
        setAdapterOne();
        return binding.getRoot();
    }

    private void setAdapter() {

        this.modelList.add(new HomeModel("John Smith"));
        this.modelList.add(new HomeModel("John Smith"));
        this.modelList.add(new HomeModel("John Smith"));
        this.modelList.add(new HomeModel("John Smith"));

        mAdapter = new HomeSaloonRecyclerViewAdapter(getActivity(),modelList,HomeFragment.this);
        binding.recyclernearme.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclernearme.setLayoutManager(linearLayoutManager);
        binding.recyclernearme.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new HomeSaloonRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeModel model) {


            }
        });
    }

    private void setAdapterOne() {
        this.modelListCategory.clear();
        this.modelListCategory.add(new HomeModel("John Smith"));
        this.modelListCategory.add(new HomeModel("John Smith"));
        this.modelListCategory.add(new HomeModel("John Smith"));
        this.modelListCategory.add(new HomeModel("John Smith"));

        mAdapterCategory = new HomeCategoryRecyclerViewAdapter(getActivity(),modelList,HomeFragment.this);
        binding.recyclerCategory.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        binding.recyclerCategory.setAdapter(mAdapterCategory);

        mAdapterCategory.SetOnItemClickListener(new HomeCategoryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeModel model) {


            }
        });
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_homeContainer, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }


}