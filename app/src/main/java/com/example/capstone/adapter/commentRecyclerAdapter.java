package com.example.capstone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone.R;
import com.example.capstone.item2;

import java.util.ArrayList;

public class commentRecyclerAdapter extends RecyclerView.Adapter<commentRecyclerAdapter.MyViewHolder> {
    private final ArrayList<item2> iList2;

    public commentRecyclerAdapter(ArrayList<item2> iList2){ this.iList2 = iList2; }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView UserTxt;
        private final TextView CommentTxt;

        public MyViewHolder(final View itemView){
            super(itemView);
            UserTxt = itemView.findViewById(R.id.c_user);
            CommentTxt = itemView.findViewById(R.id.c_contents);
        }
    }

    @NonNull
    @Override
    public commentRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull commentRecyclerAdapter.MyViewHolder holder, int position) {
        String user = iList2.get(position).getUser();
        String comment = iList2.get(position).getComment();

        holder.UserTxt.setText(user);
        holder.CommentTxt.setText(comment);
    }

    @Override
    public int getItemCount() {
        if(iList2 != null){
            return iList2.size();
        }
        return 0;
    }
}
