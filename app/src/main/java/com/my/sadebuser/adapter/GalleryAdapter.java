package com.my.sadebuser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryAdapter_View> {

    private final List<String> list;

    public GalleryAdapter(List<String> list) {
        this.list = list;
    }
    @Override
    public GalleryAdapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery, parent, false);

        view.getLayoutParams().width = (Utils.getScreenWidth(view.getContext()) - Utils.dpToPx(view.getContext(), 16)) / 2; /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS

        return new GalleryAdapter_View(view);
    }

    @Override
    public void onBindViewHolder( GalleryAdapter.GalleryAdapter_View holder, int position) {

        Picasso.get().load(list.get(position)).into(holder.iv_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GalleryAdapter_View extends RecyclerView.ViewHolder  {

        private ImageView iv_img;

        public GalleryAdapter_View( View itemView) {
            super(itemView);

            iv_img=itemView.findViewById(R.id.iv_img);
        }
    }
}
