package com.my.sadebuser.Fragment;

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
import com.my.sadebuser.databinding.FragmentAllSaloonBinding;
import com.my.sadebuser.model.HomeModel;

import java.util.ArrayList;

public class AllSaloonFragment extends Fragment {

    private Fragment fragment;
    FragmentAllSaloonBinding binding;

    HomeSaloonRecyclerViewAdapter mAdapter;
    private ArrayList<HomeModel> modelList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_saloon, container, false);

        setAdapter();

        return binding.getRoot();
    }

    private void setAdapter() {

        this.modelList.add(new HomeModel("John Smith"));
        this.modelList.add(new HomeModel("John Smith"));
        this.modelList.add(new HomeModel("John Smith"));
        this.modelList.add(new HomeModel("John Smith"));

        mAdapter = new HomeSaloonRecyclerViewAdapter(getActivity(),modelList, AllSaloonFragment.this);
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


}