package com.sasmobile.contactus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sasmobile.R;

import java.util.ArrayList;


/**
 * Created by Trainer on 20/08/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ContactUs> mContactsList;
    //declare interface
    private OnContactItemClicked onClick;

    public interface OnContactItemClicked {
        void onItemClick(int position);
    }


    public ContactsAdapter(Context mContext, ArrayList<ContactUs> mContactsList) {
        this.mContext = mContext;
        this.mContactsList = mContactsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.contacts_card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ContactUs contactUs = mContactsList.get(position);
        holder.contactFax.setText(contactUs.getContactName());
        holder.contactAddress.setText(contactUs.getContactAddress());
        // holder.contactEmail.setText(contactUs.getContactEmail());
        holder.contactPhone.setText(contactUs.getContactPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                onClick.onItemClick(position);
            }
        });
        holder.phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactUs.getContactPhone()));
                mContext.startActivity(intent);
            }
        });
        holder.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent.setType("vnd.android.cursor.item/email");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{contactUs.getContactEmail()});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Email Subject");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "My email content");
                mContext.startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
            }
        });
        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Implement it", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContactsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView contactPhone;
        private TextView contactFax;
        private TextView contactAddress;
        private ImageView phoneButton;
        private ImageView emailButton;
        private ImageView mapButton;

        public ViewHolder(View itemView) {
            super(itemView);
            contactFax = (TextView) itemView.findViewById(R.id.contact_fax);
            contactAddress = (TextView) itemView.findViewById(R.id.contact_address);
            contactPhone = (TextView) itemView.findViewById(R.id.contact_phone);
            phoneButton = (ImageView) itemView.findViewById(R.id.phone_button);
            emailButton = (ImageView) itemView.findViewById(R.id.email_button);
            mapButton = (ImageView) itemView.findViewById(R.id.map_button);

        }

    }

    public void setOnClick(OnContactItemClicked onClick) {
        this.onClick = onClick;
    }


}
