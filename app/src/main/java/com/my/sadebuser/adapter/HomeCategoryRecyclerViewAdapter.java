package com.my.sadebuser.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.my.sadebuser.R;
import com.my.sadebuser.model.HomeModel;

import java.util.ArrayList;


public class HomeCategoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    int pos = 0;
    private Context mContext;
    private ArrayList<HomeModel> modelList;
    private OnItemClickListener mItemClickListener;
    private Fragment fragment;
    boolean isLike=true;

    public HomeCategoryRecyclerViewAdapter(Context context, ArrayList<HomeModel> modelList, Fragment fragment) {
        this.mContext = context;
        this.modelList = modelList;
        this.fragment = fragment;
    }

    public void updateList(ArrayList<HomeModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final HomeModel model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;


         //   String IsFav= model.getIsFav().toString();


        }

    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private HomeModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, HomeModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_room;


        public ViewHolder(final View itemView) {
            super(itemView);

         //   this.img_room=itemView.findViewById(R.id.img_room);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

