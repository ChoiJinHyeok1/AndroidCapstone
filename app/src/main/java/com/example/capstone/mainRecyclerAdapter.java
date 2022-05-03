package com.example.capstone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mainRecyclerAdapter extends RecyclerView.Adapter<mainRecyclerAdapter.MyViewHolder>{
    private final ArrayList<item1> iList1;

    public mainRecyclerAdapter(ArrayList<item1> iList1){ this.iList1 = iList1; }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView titleTxt;
        private final TextView dateTxt;
        private final TextView contentTxt;

        public MyViewHolder(final View itemView){
            super(itemView);
            titleTxt = itemView.findViewById(R.id.m_title);
            dateTxt = itemView.findViewById(R.id.m_date);
            contentTxt = itemView.findViewById(R.id.m_content);

        }
    }

    @NonNull
    @Override
    public mainRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull mainRecyclerAdapter.MyViewHolder holder, int position) {
        String title = iList1.get(position).getTitle();
        String date = iList1.get(position).getDate();
        String content = iList1.get(position).getContent();

        holder.titleTxt.setText(title);
        holder.dateTxt.setText(date);
        holder.contentTxt.setText(content);

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, Activity6Reader.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(iList1 != null){
            return iList1.size();
        }
        return 0;
    }
}
