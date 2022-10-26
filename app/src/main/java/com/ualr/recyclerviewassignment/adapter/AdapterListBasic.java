package com.ualr.recyclerviewassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;
import java.util.Random;

public class AdapterListBasic extends RecyclerView.Adapter {
    private List<Inbox> mItems;
    private Context mContext;

    public AdapterListBasic(Context context, List<Inbox> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    // This method is called everytime we need to create a new view holder to represent an item:
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        vh = new InboxViewHolder(itemView);
        return vh;
    }

    @Override
    // This method is called when the layout manager is ready to display a new particular view in the recycler view area
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InboxViewHolder viewHolder = (InboxViewHolder) holder;
        Inbox i = mItems.get(position);

        // Simple method to link the name to the appropriate user image:
        int[] resImages = {
                            R.drawable.u8_round,
                            R.drawable.u10_round,
                            R.drawable.u22_round,
                            R.drawable.u20_round,
                            R.drawable.u2_round,
                            R.drawable.u15_round,
                            R.drawable.u27_round,
                            R.drawable.u24_round,
                            R.drawable.u13_round,
                            R.drawable.u23_round,
                            R.drawable.u4_round,
                            R.drawable.u12_round,
                            R.drawable.u16_round,
                            R.drawable.u19_round,
                            R.drawable.u5_round
                          };

        Random r = new Random();
        int randomInt = r.nextInt(resImages.length);

        viewHolder.tvName.setText(i.getFrom());
        viewHolder.tvSubject.setText(i.getMessage());

        // Add a specific user image for each person:
        //viewHolder.ivAvatar.setImageResource(R.drawable.ic_user); // Generic user image
        viewHolder.ivAvatar.setImageResource(resImages[i.getImageIndex()]);

        viewHolder.tvEmail.setText(i.getEmail());
        viewHolder.tvDate.setText(i.getDate());
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public class InboxViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivAvatar;
        public TextView tvName;
        public TextView tvSubject;
        public TextView tvEmail;
        public TextView tvDate;
        public View layoutParent;

        public InboxViewHolder(View v) {
            super(v);
            ivAvatar = v.findViewById(R.id.ivAvatar);
            tvName = v.findViewById(R.id.tvName);
            tvSubject = v.findViewById(R.id.tvSubject);
            tvEmail = v.findViewById(R.id.tvEmail);
            tvDate = v.findViewById(R.id.tvDate);
            layoutParent = v.findViewById(R.id.layoutParent);
        }
    }

}


