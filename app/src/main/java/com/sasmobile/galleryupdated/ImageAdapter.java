package com.sasmobile.galleryupdated;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sasmobile.R;

/**
 * Created by abrarkhan on 10/22/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;
    private int[] mip;
    //declare interface


    public ImageAdapter(Context mContext, int[] mip) {
        this.mContext = mContext;
        this.mip = mip;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.gallery_image_fragment, parent, false);

        return new ImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, final int position) {


        holder.homeImageItems.setImageResource(mip[position]);
    }

    @Override
    public int getItemCount() {
        return mip.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView homeImageItems;

        public ViewHolder(View itemView) {
            super(itemView);

            homeImageItems = (ImageView) itemView.findViewById(R.id.iv_image_gallery);


        }


    }
}
