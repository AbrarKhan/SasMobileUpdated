package com.sasmobile.productservices;

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

public class ProductServicesAdapter extends RecyclerView.Adapter<ProductServicesAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Product> mProductsList;
    //declare interface
    private OnProductsItemClicked onClick;

    public interface OnProductsItemClicked {
        void onItemClick(int position);
    }


    public ProductServicesAdapter(Context mContext, ArrayList<Product> mProductsList) {
        this.mContext = mContext;
        this.mProductsList = mProductsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.products_card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Product product = mProductsList.get(position);
        holder.productName.setText(product.getProductName());
        int i = product.getProductImage();
        holder.imageProduct.setImageResource(i);

        //holder.productDesc.setText(product.getProductDesc());

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
        return mProductsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // private final TextView productDesc;
        private TextView productName;
        private ImageView imageProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            imageProduct = (ImageView) itemView.findViewById(R.id.product_image);
        }

    }

    public void setOnClick(OnProductsItemClicked onClick) {
        this.onClick = onClick;
    }


}
