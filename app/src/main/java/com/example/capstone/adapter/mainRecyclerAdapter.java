package com.example.capstone.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone.PostInfo;
import com.example.capstone.R;

import java.util.ArrayList;

public class mainRecyclerAdapter extends RecyclerView.Adapter<mainRecyclerAdapter.MyViewHolder>{
    private ArrayList<PostInfo> mDataset;
    private Activity activity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public MyViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public mainRecyclerAdapter(Activity activity, ArrayList<PostInfo> myDataset) {
        mDataset = myDataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public mainRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.main_items, parent, false);
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
        TextView m_title = cardView.findViewById(R.id.m_title);
        m_title.setText(mDataset.get(position).getTitle());

        TextView m_content = cardView.findViewById(R.id.m_content);
        m_content.setText(mDataset.get(position).getContents());

//        TextView m_createAt = cardView.findViewById(R.id.m_createAt);
//        m_createAt.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(mDataset.get(position).getCreatedAt()));

        Log.e("로그: ","데이터: "+mDataset.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}