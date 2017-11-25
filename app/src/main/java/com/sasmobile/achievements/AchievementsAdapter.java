package com.sasmobile.achievements;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;

import com.sasmobile.R;

import java.util.ArrayList;


/**
 * Created by Trainer on 20/08/2017.
 */

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Achievements> mAchievementsList;
    //declare interface
    private OnNewsItemClicked onClick;
    private MediaController mediaControls;
    private int videoPosition = 0;

    public interface OnNewsItemClicked {
        void onItemClick(int position);
    }


    public AchievementsAdapter(Context mContext, ArrayList<Achievements> mAchievementsList) {
        this.mContext = mContext;
        this.mAchievementsList = mAchievementsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.achievements_card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Achievements achievements = mAchievementsList.get(position);

        holder.newsHeader.setText(achievements.getmAchievements());

    }

    @Override
    public int getItemCount() {
        return mAchievementsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView newsHeader;


        public ViewHolder(View itemView) {
            super(itemView);
            newsHeader = (TextView) itemView.findViewById(R.id.achievements_header);


        }

    }

    public void setOnClick(OnNewsItemClicked onClick) {
        this.onClick = onClick;
    }


}
