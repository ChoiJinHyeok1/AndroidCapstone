package com.example.capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final ArrayList<item3> ilist3;

    public chatAdapter(ArrayList<item3> ilist3) {
        this.ilist3 = ilist3;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == code.item3.CENTER_CONTENT)
        {
            view = inflater.inflate(R.layout.center_content, parent, false);
            return new CenterViewHolder(view);
        }
        else if(viewType == code.item3.LEFT_CONTENT)
        {
            view = inflater.inflate(R.layout.left_content, parent, false);
            return new LeftViewHolder(view);
        }
        else
        {
            view = inflater.inflate(R.layout.right_content, parent, false);
            return new RightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof CenterViewHolder)
        {
            ((CenterViewHolder) viewHolder).content.setText(ilist3.get(position).getContent());
        }
        else if(viewHolder instanceof LeftViewHolder)
        {
            ((LeftViewHolder) viewHolder).content.setText(ilist3.get(position).getContent());
        }
        else
        {
            ((RightViewHolder) viewHolder).content.setText(ilist3.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return ilist3.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ilist3.get(position).getViewType();
    }

    public class CenterViewHolder extends RecyclerView.ViewHolder{
        TextView content;

        CenterViewHolder(View itemView)
        {
            super(itemView);

            content = itemView.findViewById(R.id.content);
        }
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder{
        TextView content;

        LeftViewHolder(View itemView)
        {
            super(itemView);

            content = itemView.findViewById(R.id.content);
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView content;

        RightViewHolder(View itemView)
        {
            super(itemView);

            content = itemView.findViewById(R.id.content);
        }
    }
}
