package com.my.sadebuser.act.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.my.sadebuser.act.ui.Fragment.GalleryFragment;
import com.my.sadebuser.act.ui.Fragment.ReviewFragment;
import com.my.sadebuser.act.ui.Fragment.ServicesFragment;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.servicelist.AllServiceResponse;
import com.my.sadebuser.act.model.servicelist.ResultItem;
import com.my.sadebuser.databinding.ActivityShopDetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ShopDetailsActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ActivityShopDetailsBinding binding;
    private AllServiceResponse response;
    private String Provider_Name,Provider_id,provider_img;
    private final List<ResultItem> servicelist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_details);

        response = new Gson().fromJson(getIntent().getStringExtra("response"), AllServiceResponse.class);
        Provider_Name=getIntent().getStringExtra("Provider_Name");
        Provider_id=getIntent().getStringExtra("Provider_id");
        provider_img=getIntent().getStringExtra("provider_img");
        Log.i("fdvdfcd", "onCreate: "+response.getResult());
        frameLayout=findViewById(R.id.frame);

        servicelist.addAll(response.getResult());


        replace(ServicesFragment.newInstance(servicelist, Provider_Name, Provider_Name));
        if (servicelist.size() != 0 && servicelist != null) {
            binding.tvDescription.setText(servicelist.get(0).getDescription());
            Picasso.get().load(servicelist.get(0).getImage1()).into(binding.ivService);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.tvService.setOnClickListener(v -> {
            binding.tvService.setBackgroundResource(R.drawable.bg_round);
            binding.tvService.setTextColor(Color.parseColor("#FFFFFF"));
            binding.tvGallery.setTextColor(Color.parseColor("#000000"));
            binding.tvReviews.setTextColor(Color.parseColor("#000000"));
             binding.tvGallery.setBackgroundResource(R.drawable.bg_round_white);
            binding.tvReviews.setBackgroundResource(R.drawable.bg_round_white);

            replace( ServicesFragment.newInstance(servicelist,Provider_Name,provider_img));


        });

        binding.tvGallery.setOnClickListener(v -> {
            binding.tvGallery.setBackgroundResource(R.drawable.bg_round);
            binding.tvGallery.setTextColor(Color.parseColor("#FFFFFF"));
            binding.tvService.setTextColor(Color.parseColor("#000000"));
            binding.tvReviews.setTextColor(Color.parseColor("#000000"));
            binding.tvService.setBackgroundResource(R.drawable.bg_round_white);
            binding.tvReviews.setBackgroundResource(R.drawable.bg_round_white);
            replace( GalleryFragment.newInstance(response));

        });

        binding.tvReviews.setOnClickListener(v -> {
            binding.tvReviews.setBackgroundResource(R.drawable.bg_round);
            binding.tvReviews.setTextColor(Color.parseColor("#FFFFFF"));
            binding.tvGallery.setTextColor(Color.parseColor("#000000"));
            binding.tvService.setTextColor(Color.parseColor("#000000"));
            binding.tvService.setBackgroundResource(R.drawable.bg_round_white);
            binding.tvGallery.setBackgroundResource(R.drawable.bg_round_white);
            replace(  ReviewFragment.newInstance(Provider_id,Provider_Name,provider_img));

        });



        binding.ivBack.setOnClickListener(v -> {

            onBackPressed();
        });


    }
    public void replace(Fragment fragment){
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame,fragment);
         ft.commit();
    }

}