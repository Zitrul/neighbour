package com.example.samsung.myapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsung.myapp.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Integer> mViewImg;
    private List<String> mJobs;

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public MyRecyclerViewAdapter(Context context, List<Integer> colors, List<String> animals) {
        this.mInflater = LayoutInflater.from(context);
        this.mViewImg = colors;
        this.mJobs = animals;
    }


    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int color = mViewImg.get(position);
        String animal = mJobs.get(position);
        holder.myView.setImageResource(color);
        holder.myTextView.setText(animal);
    }


    @Override
    public int getItemCount() {
        return mJobs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.imageres);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null){
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }


    public String getItem(int id) {
        return mJobs.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}