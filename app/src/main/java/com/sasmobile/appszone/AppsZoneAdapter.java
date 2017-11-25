package com.sasmobile.appszone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sasmobile.R;
import java.util.ArrayList;


/**
 * Created by Trainer on 20/08/2017.
 */

public class AppsZoneAdapter extends RecyclerView.Adapter<AppsZoneAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Event> mEventsList;
    //declare interface
    private OnEventItemClicked onClick;

    public interface OnEventItemClicked {
        void onItemClick(int position);
    }


    public AppsZoneAdapter(Context mContext, ArrayList<Event> mEventsList) {
        this.mContext = mContext;
        this.mEventsList = mEventsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.events_card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Event event = mEventsList.get(position);
        holder.eventName.setText(event.getEventName());
        holder.eventDesc.setText(event.getEventDesc());
        int i = event.getEventImage();
        holder.eventImage.setImageResource(i);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                onClick.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView eventName,eventDesc;
        private ImageView eventImage;

        public ViewHolder(View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.event_name);
            eventDesc = (TextView) itemView.findViewById(R.id.event_desc);
            eventImage = (ImageView) itemView.findViewById(R.id.event_image);

        }

    }

    public void setOnClick(OnEventItemClicked onClick) {
        this.onClick = onClick;
    }


}
