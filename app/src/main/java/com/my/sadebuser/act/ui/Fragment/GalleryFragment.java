package com.my.sadebuser.act.ui.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.servicelist.AllServiceResponse;
import com.my.sadebuser.act.model.servicelist.ResultItem;
import com.my.sadebuser.adapter.GalleryAdapter;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private View view;
    private RecyclerView rv_gallery;
    private GalleryAdapter  galleryAdapter;
    private AllServiceResponse response;
    private List<String> list=new ArrayList<>();

    public static GalleryFragment newInstance(AllServiceResponse response ) {
        GalleryFragment f = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString("response", new Gson().toJson(response));
        f.setArguments(args);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_gallery, container, false);
        rv_gallery=view.findViewById(R.id.rv_gallery);

        Bundle args = getArguments();
        if(args!=null){
            Log.i("Xsvd", "onCreateView: +"+2);
            response = new Gson().fromJson(args.getString("response"),AllServiceResponse.class);

            for (ResultItem resultItem : response.getResult()) {
                list.add(resultItem.getImage1());
                list.add(resultItem.getImage2());
                list.add(resultItem.getImage3());
                list.add(resultItem.getImage4());
                list.add(resultItem.getImage5());
                list.add(resultItem.getImage6());
                list.add(resultItem.getImage7());
            }

            rv_gallery.setLayoutManager(new GridLayoutManager(getContext(),3));
            galleryAdapter=new GalleryAdapter(list);
            rv_gallery.setAdapter(galleryAdapter);

         }

        Log.i("sfdzffs", "onCreateView: "+response.getResult().toString());


        return view;
    }
}