package com.my.sadebuser.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.category.ResultItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class HomeCategoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int pos = 0;
    boolean isLike = true;
    private final Context mContext;
    private final ArrayList<ResultItem> modelList;
    private OnItemClickListener mItemClickListener;

    public HomeCategoryRecyclerViewAdapter(Context context, List<ResultItem> modelList) {
        this.mContext = context;
        this.modelList = (ArrayList<ResultItem>) modelList;
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
            final ResultItem model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            if (modelList.size()!=0){
                ((ViewHolder) holder).tv_Name.setText(modelList.get(position).getCategoryName());

                if (modelList.get(position).getImage()==null &&!modelList.get(position).getImage().isEmpty())
                Picasso.get().load(modelList.get(position).getImage()).into(((ViewHolder) holder).user_image);

            }

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

    private ResultItem getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, ResultItem model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_room,user_image;
        private TextView tv_Name;


        public ViewHolder(final View itemView) {
            super(itemView);

            //   this.img_room=itemView.findViewById(R.id.img_room);
            user_image=itemView.findViewById(R.id.user_image);
            tv_Name=itemView.findViewById(R.id.tv_Name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });
        }
    }


}

