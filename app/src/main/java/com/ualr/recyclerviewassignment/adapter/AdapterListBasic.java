package com.ualr.recyclerviewassignment.adapter;

import android.annotation.SuppressLint;
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

public class AdapterListBasic extends RecyclerView.Adapter {
    private List<Inbox> mItems;
    private final Context mContext;
    private OnItemClickListener mOnItemClickListener;

    // Constructor:
    public AdapterListBasic(Context context, List<Inbox> items)
    {
        this.mItems = items;
        this.mContext = context;
    }

    // Required overridden method: This method is called everytime we need to create a new view
    //                             holder to represent an item:
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder vh;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_v2, parent, false);
        vh = new InboxViewHolder(itemView);
        return vh;
    }

    // Required overridden method: This method is called when the layout manager is ready to display
    //                             a new particular view in the recycler view area
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
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

        viewHolder.tvName.setText(i.getFrom());
        viewHolder.tvSubject.setText(i.getMessage());

        // Add a specific user image for each person:
        //viewHolder.ivAvatar.setImageResource(R.drawable.ic_user);            // Generic user image
        viewHolder.ivAvatar.setImageResource(resImages[i.getImageIndex()]);

        viewHolder.tvEmail.setText(i.getEmail());
        viewHolder.tvDate.setText(i.getDate());

    }

    public void removeItem( int position )
    {
        // Check for out-of-bounds:
        if( position >= mItems.size() )
            return;

        // Remove the item from the list:
        mItems.remove( position );

        // Notify the adapter that an item has been removed from the list:
        notifyItemRemoved( position );

        // Notify the adapter the range has changed since removing the item:
        notifyItemRangeChanged( position, getItemCount() );
    }

    public void addItem( int position, Inbox email )
    {
        // Add the item to the list:
        mItems.add( email );

        // Notify the adapter that an item has been removed from the list:
        notifyItemInserted( position );
    }

    // Required overridden method: This method is called when the layout manager needs to determine
    //                             how many elements are available in the list/data collection
    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Inbox> inbox)
    {
        mItems = inbox;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener
    {
        void onItemClick( View v, Inbox email, int position );
    }

    public void setOnItemClickListener( final OnItemClickListener mItemClickListener )
    {
        mOnItemClickListener = mItemClickListener;
    }

    public Context getContext() {
        return this.mContext;
    }

    // ViewHolder Internal Class:
    public class InboxViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ivAvatar;
        public TextView tvName;
        public TextView tvSubject;
        public TextView tvEmail;
        public TextView tvDate;
        public ImageView ivTrash;
        public View layoutParent;

        // ViewHolder Constructor:
        public InboxViewHolder(View v)
        {
            super(v);
            ivAvatar = v.findViewById(R.id.ivAvatar);
            tvName = v.findViewById(R.id.tvName);
            tvSubject = v.findViewById(R.id.tvSubject);
            tvEmail = v.findViewById(R.id.tvEmail);
            tvDate = v.findViewById(R.id.tvDate);
            ivTrash = v.findViewById(R.id.ivTrash);
            layoutParent = v.findViewById(R.id.layoutParent);

            // On-Click Listener for the parent/root component of the activity:
            layoutParent.setOnClickListener(v1 -> mOnItemClickListener.onItemClick(v1, mItems.get(getLayoutPosition()), getLayoutPosition()));
        }
    }
}