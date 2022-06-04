package com.example.capstone.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone.Activity.Activity6Reader;
import com.example.capstone.Info.CommentInfo;
import com.example.capstone.Info.PostInfo;
import com.example.capstone.R;
import com.google.firebase.Timestamp;

import java.util.ArrayList;


public class commentRecyclerAdapter extends RecyclerView.Adapter<commentRecyclerAdapter.MyViewHolder>{
    private ArrayList<CommentInfo> mDataset;
    private Activity activity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public MyViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public commentRecyclerAdapter(Activity activity, ArrayList<CommentInfo> myDataset) {
        this.mDataset = myDataset;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @NonNull
    @Override
    public commentRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_items, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView c_contents = cardView.findViewById(R.id.c_contents);
        c_contents.setText(mDataset.get(position).getCcontents());

        TextView c_time = cardView.findViewById(R.id.c_time);
        Timestamp timestamp = (Timestamp) mDataset.get(position).getCcreatedAt();
        String date = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm").format(new java.util.Date(String.valueOf(timestamp.toDate())));
        c_time.setText(date);

    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
