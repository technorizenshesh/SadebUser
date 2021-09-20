package com.my.sadebuser.act.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.category.CategoryResponse;
import com.my.sadebuser.act.model.serviceprovider.ResultItem;
import com.my.sadebuser.act.model.serviceprovider.ServiceProviderListResponse;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.category.CategoryRequest;
import com.my.sadebuser.act.network.request.service.Serviecs;
import com.my.sadebuser.act.network.request.serviceprovider.ServiceProvider;
import com.my.sadebuser.act.ui.activity.AllCategoryActivity;
import com.my.sadebuser.act.ui.activity.NearMeAllListActivity;
import com.my.sadebuser.act.ui.activity.SearchActivity;
import com.my.sadebuser.act.ui.activity.ShopDetailsActivity;
import com.my.sadebuser.adapter.HomeCategoryRecyclerViewAdapter;
import com.my.sadebuser.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.sadebuser.model.HomeModel;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private final List<com.my.sadebuser.act.model.category.ResultItem> list = new ArrayList<>();
    com.my.sadebuser.databinding.FragmentHomeBinding binding;
    private Fragment fragment;
    private final List<ResultItem> serviceproviderlist = new ArrayList<>();
    private String Provider_Name,Provider_id,provider_img;
    private HomeSaloonRecyclerViewAdapter mAdapter;
    private HomeCategoryRecyclerViewAdapter mAdapterCategory;
    private int API_COUNT;
    private final int ALL_API_COUNT = 2;
    private final List<com.my.sadebuser.act.model.servicelist.ResultItem> servicelist = new ArrayList<>();
    private final ArrayList<HomeModel> modelListCategory = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
        if (model!=null){
             Picasso.get().load(model.getResult().getImage()).placeholder(R.drawable.user_placeholder).into(binding.cvImage);

        }

        binding.txtNearme.setOnClickListener(v -> {

            startActivity(new Intent(getActivity(), NearMeAllListActivity.class));
        });

        binding.tvAllcategory.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AllCategoryActivity.class));
        });

        binding.relativeSearchBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SearchActivity.class));

        });


        providerList();
        CategoryResponse();
        return binding.getRoot();
    }


    private void CategoryResponse() {
        binding.llMain.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);


        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(CategoryRequest.class)
                .getCategory()
                .enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

                        ++API_COUNT;
                        if (API_COUNT == ALL_API_COUNT) {
                            binding.llMain.setVisibility(View.VISIBLE);
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                        }
                        if (response != null) {
                            list.addAll(response.body().getResult());
                            mAdapterCategory = new HomeCategoryRecyclerViewAdapter(getActivity(), list);
                            binding.recyclerCategory.setHasFixedSize(true);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
                            binding.recyclerCategory.setAdapter(mAdapterCategory);

                            mAdapterCategory.SetOnItemClickListener(new HomeCategoryRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position, com.my.sadebuser.act.model.category.ResultItem model) {

                                }
                            });
                            mAdapterCategory.notifyDataSetChanged();
                        }


                    }

                    @Override
                    public void onFailure(Call<CategoryResponse> call, Throwable t) {

                        ++API_COUNT;
                        if (API_COUNT == ALL_API_COUNT) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void providerList() {

        binding.llMain.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);


        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(ServiceProvider.class)
                .getServiceProviderList()
                .enqueue(new Callback<ServiceProviderListResponse>() {
                    @Override
                    public void onResponse(Call<ServiceProviderListResponse> call, Response<ServiceProviderListResponse> response) {

                        ++API_COUNT;
                        if (API_COUNT == ALL_API_COUNT) {
                            binding.llMain.setVisibility(View.VISIBLE);
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                        }
                        serviceproviderlist.addAll(response.body().getResult());
                        Log.i("sdvdc", "onResponse: "+response.toString());
                        Log.i("sdvdc", "onResponse: "+response.body());
                        mAdapter = new HomeSaloonRecyclerViewAdapter(getActivity(), serviceproviderlist, HomeFragment.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        binding.recyclernearme.setLayoutManager(linearLayoutManager);
                        binding.recyclernearme.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                        mAdapter.SetOnItemClickListener(new HomeSaloonRecyclerViewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position, ResultItem model) {
                                allServicelist(model.getId());
                                Provider_Name = model.getUserName();
                                Provider_id=model.getId();
                                provider_img=model.getImage();
                                binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ServiceProviderListResponse> call, Throwable t) {
                        ++API_COUNT;
                        if (API_COUNT == ALL_API_COUNT) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                        }
                        Log.i("sfvvs", "onFailure: " + t.getMessage());
                    }
                });
    }


    private void allServicelist(String id) {
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(Serviecs.class)
                .getServices(id)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {


                        if (response.isSuccessful()) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                            JsonObject jsonObject = response.body().getAsJsonObject();
                            int status = jsonObject.get("status").getAsInt();
                            if (status == 1) {
                                Intent intent = new Intent(getContext(), ShopDetailsActivity.class);
                                intent.putExtra("response", new Gson().toJson(response.body()));
                                intent.putExtra("Provider_Name", Provider_Name);
                                intent.putExtra("Provider_id", Provider_id);
                                intent.putExtra("provider_img", provider_img);
                                getContext().startActivity(intent);

                            } else {
                                binding.loaderLayout.loader.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "There is No Service", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        Log.i("sfdgvcv", "onFailure: " + t.getMessage());
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