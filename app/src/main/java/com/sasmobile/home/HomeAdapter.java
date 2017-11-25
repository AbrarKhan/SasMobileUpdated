package com.sasmobile.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sasmobile.R;


/**
 * Created by Trainer on 20/08/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context mContext;
    private String[] mHomeItems;
    private int[] mip;
    //declare interface
    private OnItemClicked onClick;


    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public HomeAdapter(Context mContext, String[] mHomeItems, int[] mip) {
        this.mContext = mContext;
        this.mHomeItems = mHomeItems;
        this.mip = mip;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home_card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.homeItemName.setText(mHomeItems[position]);
        holder.homeImageItems.setImageResource(mip[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                Log.d("OnBindViewHolder", "Homedapter.setOnclick");
                onClick.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mHomeItems.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView homeItemName;
        private ImageView homeImageItems;

        public ViewHolder(View itemView) {
            super(itemView);

            homeItemName = (TextView) itemView.findViewById(R.id.home_element_name);
            homeImageItems = (ImageView) itemView.findViewById(R.id.iv);


        }


    }

    public void setOnClick(OnItemClicked onClick) {
        Log.d("Homedapter", "Homedapter.setOnclick");
        this.onClick = onClick;
    }


}
