package com.my.sadebuser.act.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.servicelist.ResultItem;
import com.my.sadebuser.act.model.timeavailibity.TimeAvailibityResponse;
import com.my.sadebuser.act.model.timeavailibity.TimeItem;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.time.TimeAvailibity;
import com.my.sadebuser.adapter.TimeSlotAdapter;
import com.my.sadebuser.databinding.ActivityShopTimeAvailavilityBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.my.sadebuser.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopTimeAvailavility extends AppCompatActivity {

    private final List<TimeItem> timeItems = new ArrayList<>();
    private final Calendar selectedDateTime = Calendar.getInstance();
    private final int ALL_API_COUNT = 1;
    private ActivityShopTimeAvailavilityBinding binding;
    private String start_time, end_time, date, availibitiydate;
    private TimeSlotAdapter timeSlotAdapter;
    private ResponseAuthentication model;
    private String service_id;
    private ResultItem item;
    private int API_COUNT;
    private String provider_Name,provider_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_time_availavility);


        init();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        timeAvailibity();
        setData();
        setUpDatePicker();
        binding.imgBack.setOnClickListener(v -> {
            onBackPressed();
        });


        binding.RRBook.setOnClickListener(v -> {
            if (validate()) {
                Intent intent = new Intent(ShopTimeAvailavility.this, BookingDetails.class);
                intent.putExtra("service_id", service_id);
                intent.putExtra("start_time", start_time);
                intent.putExtra("end_time", end_time);
                intent.putExtra("date", date);
                intent.putExtra("email", binding.tvEmail.getText().toString());
                intent.putExtra("no", binding.tvNo.getText().toString());
                intent.putExtra("item", new Gson().toJson(item));
                startActivity(intent);
            }
        });
    }


    private void init() {

        model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
        service_id = getIntent().getStringExtra("service_id");
        Log.i("sfsf", "init: "+model.getResult());
        provider_Name = getIntent().getStringExtra("provider_Name");
        provider_img = getIntent().getStringExtra("provider_img");
        item = new Gson().fromJson(getIntent().getStringExtra("item"), ResultItem.class);
        binding.tvProviderName.setText(provider_Name);
    }
    private void setData() {
        Picasso.get().load(item.getImage1()).placeholder(R.drawable.user_placeholder).into(binding.ivImage);
        Picasso.get().load(provider_img).placeholder(R.drawable.user_placeholder).into(binding.ivPorviderImg);
        if (model != null) {
            binding.tvEmail.setText(model.getResult().getEmail());
            binding.tvNo.setText(model.getResult().getMobile());
        }

        binding.rvTimeslot.setLayoutManager(new GridLayoutManager(ShopTimeAvailavility.this, 2));
        timeSlotAdapter = new TimeSlotAdapter(timeItems, new TimeSlotAdapter.ClickCallback() {
            @Override
            public void click(int positoin) {
                Log.i("TAGczczv", "click: " + timeItems.get(positoin).getStatus());
                start_time = timeItems.get(positoin).getStart();
                end_time = timeItems.get(positoin).getEnd();
                if (timeItems.get(positoin).getStatus().equals("false")) {
                    date = new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis());
                    binding.appointmentDT.setText(date + " " + start_time);
                }
            }
        });

        binding.rvTimeslot.setAdapter(timeSlotAdapter);
    }

    private void timeAvailibity() {
        binding.llMain.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(TimeAvailibity.class)
                .getAllTimeSlot(service_id, String.valueOf(selectedDateTime.getTimeInMillis()))
                .enqueue(new Callback<TimeAvailibityResponse>() {
                    @Override
                    public void onResponse(Call<TimeAvailibityResponse> call, Response<TimeAvailibityResponse> response) {
                        Log.i("cxvxvxv", "onResponse: " + response.body());
                        Log.i("cxvxvxv", "onResponse: " + response.toString());

                        ++API_COUNT;
                        if (API_COUNT == ALL_API_COUNT) {
                            binding.llMain.setVisibility(View.VISIBLE);
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                        }
                        if (response.body().getResult().getTime()!=null){
                            timeItems.addAll(response.body().getResult().getTime());
                            timeSlotAdapter.notifyDataSetChanged();

                            Log.i("sfdddds", "onResponse: "+timeItems.size());
                            if (timeItems.size()==0){
                                binding.Nofound.setVisibility(View.VISIBLE);
                                 binding.RRBook.setEnabled(false);
                                 binding.RRBook.setAlpha(0.7f);
                                 binding.rvTimeslot.setVisibility(View.GONE);
                            }else {
                                binding.Nofound.setVisibility(View.GONE);
                                binding.RRBook.setEnabled(true);
                                binding.RRBook.setAlpha(1f);
                                binding.rvTimeslot.setVisibility(View.VISIBLE);

                            }
                        }

//                        if (timeItems.size()>0) {
//                            date = new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis());
//                             binding.appointmentDT.setText(date + " " + timeItems.get(0).getStart());
//
//                        }


                    }

                    @Override
                    public void onFailure(Call<TimeAvailibityResponse> call, Throwable t) {
                        ++API_COUNT;
                        if (API_COUNT == ALL_API_COUNT) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                        }
                        Log.i("xvcbcvxvdv", "onFailure: " + t.getMessage());
                    }
                });
    }


    private void setUpDatePicker() {

        List<CalendarConstraints.DateValidator> validators = new ArrayList<>();
        validators.add(DateValidatorPointForward.from(selectedDateTime.getTimeInMillis()));

        MaterialDatePicker picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setTheme(R.style.ThemeMaterialCalendar)
                .setCalendarConstraints(
                        new CalendarConstraints.Builder().
                                setStart(MaterialDatePicker.todayInUtcMilliseconds()).
                                setValidator(CompositeDateValidator.allOf(validators)).
                                build()).
                        build();

        binding.llDate.setOnClickListener(v -> {
            picker.show(getSupportFragmentManager(), "tag");
        });

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                selectedDateTime.setTime(new Date(selection));
                binding.tvDate.setText(new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis()));
                availibitiydate = new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis());
                nextdateAvalibility();
                if(start_time!=null)
                binding.appointmentDT.setText(availibitiydate + " " + start_time);

            }
        });

    }

    private void nextdateAvalibility() {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(TimeAvailibity.class)
                .getAllTimeSlot(service_id, availibitiydate)
                .enqueue(new Callback<TimeAvailibityResponse>() {
                    @Override
                    public void onResponse(Call<TimeAvailibityResponse> call, Response<TimeAvailibityResponse> response) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        timeItems.clear();
                        timeItems.addAll(response.body().getResult().getTime());
                        timeSlotAdapter.notifyDataSetChanged();
//                         date = new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis());
//                        binding.appointmentDT.setText(date + " " + timeItems.get(0).getStart());
                    }

                    @Override
                    public void onFailure(Call<TimeAvailibityResponse> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                    }
                });

    }

    private boolean validate() {
        if (TextUtils.isEmpty(binding.tvEmail.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.enter_email,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (!Utils.EMAIL_ADDRESS_PATTERN.matcher(binding.tvEmail.getText().toString().replace(" ", "")).matches()
        ) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_correct_email,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.tvNo.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.text_register_no, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.tvNo.getText().toString().replace(" ", "").length() < 12) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_right_no,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.appointmentDT.getText().equals("Select time availibity")) {

            Snackbar.make(findViewById(android.R.id.content),
                    "Please Select Time Availibity",
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}