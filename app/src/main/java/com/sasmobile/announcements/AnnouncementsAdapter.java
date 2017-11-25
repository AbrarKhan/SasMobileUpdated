package com.sasmobile.announcements;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sasmobile.R;

import java.util.ArrayList;


/**
 * Created by Trainer on 20/08/2017.
 */

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Announcements> mAnnouncementsList;
    //declare interface
    private OnCareerItemClicked onClick;

    public interface OnCareerItemClicked {
        void onItemClick(int position);

        void onAddressClick(int position);
    }


    public AnnouncementsAdapter(Context mContext, ArrayList<Announcements> mAnnouncementsList) {
        this.mContext = mContext;
        this.mAnnouncementsList = mAnnouncementsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.announcements_card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Announcements announcements = mAnnouncementsList.get(position);
        holder.announcements.setText(announcements.getAnnouncementsName());

    }

    @Override
    public int getItemCount() {
        return mAnnouncementsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView announcements;


        public ViewHolder(View itemView) {
            super(itemView);
            announcements = (TextView) itemView.findViewById(R.id.announcements);


        }

    }

    public void setOnClick(OnCareerItemClicked onClick)
    {
        this.onClick = onClick;
    }


}
