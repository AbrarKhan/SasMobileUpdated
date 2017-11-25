package com.sasmobile.galleryupdated;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sasmobile.R;

import java.util.ArrayList;

/**
 * Created by abrarkhan on 10/23/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context mContext;
    private int[] mip;
    private ArrayList<Gallery> videoList;
    //declare interface


    public VideoAdapter(Context mContext, int[] mMip, ArrayList<Gallery> videoList) {
        this.mContext = mContext;
        this.mip = mMip;
        this.videoList = videoList;
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.gallery_video_fragment, parent, false);

        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, final int position) {


        holder.homeImageItems.setImageResource(mip[position]);
        holder.playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FullScreenVideo.class);
                intent.putExtra("VIDEO_URL", videoList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mip.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView homeImageItems;
        private ImageView playVideo;

        public ViewHolder(View itemView) {
            super(itemView);

            homeImageItems = (ImageView) itemView.findViewById(R.id.iv_video_gallery);
            playVideo = (ImageView) itemView.findViewById(R.id.play_video);


        }


    }
}
