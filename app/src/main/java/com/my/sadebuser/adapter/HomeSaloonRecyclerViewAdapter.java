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
import com.my.sadebuser.act.model.serviceprovider.ResultItem;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HomeSaloonRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private Context mContext;
    private List<ResultItem> list;
    private OnItemClickListener mItemClickListener;
    private Fragment fragment;
    boolean isLike=true;

    public HomeSaloonRecyclerViewAdapter(Context context, List<ResultItem> list, Fragment fragment) {
        this.mContext = context;
        this.list = list;
        this.fragment = fragment;
    }

    public HomeSaloonRecyclerViewAdapter(Context context, List<ResultItem> list) {
        this.mContext = context;
        this.list = list;

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_salooon, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you2 can fill your row view
        if (holder instanceof ViewHolder) {
            final ResultItem model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            ((ViewHolder) holder).tv_Name.setText(list.get(position).getUserName());
            if (list.get(position).getDescription().equals("")){
                ((ViewHolder) holder).tv_Detail.setText("Lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem ipsum has been the industry's standard");
            }else{
                ((ViewHolder) holder).tv_Detail.setText(list.get(position).getDescription());
            }
            Picasso.get().load(list.get(position).getImage()).placeholder(R.drawable.user_placeholder).into(((ViewHolder) holder).img1);
            genericViewHolder.txtBook.setOnClickListener(v -> {
                mItemClickListener.onItemClick(position, list.get(position));
            });


        }

    }


    @Override
    public int getItemCount() {

        return list.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private ResultItem getItem(int position) {
        return list.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(int position, ResultItem model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         private TextView txtBook,tv_Detail,tv_Name;
         private ImageView img1;


        public ViewHolder(final View itemView) {
            super(itemView);

          this.txtBook=itemView.findViewById(R.id.txtBook);
          this.tv_Detail=itemView.findViewById(R.id.tv_Detail);
          this.tv_Name=itemView.findViewById(R.id.tv_Name);
          this.img1=itemView.findViewById(R.id.img1);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }


}

