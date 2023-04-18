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

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private List<Integer> mViewImg;
    private List<String> mJobs;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public JobsAdapter(Context context, List<Integer> colors, List<String> animals) {
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

        System.out.println(mViewImg.size());
        String text1 = mJobs.get(position);
        String text2 = mJobs.get(position+1);
        String text3 = mJobs.get(position+2);
        holder.myimgres.setImageResource(R.drawable.flag_russia);
        holder.myimgresof1.setImageResource(R.drawable.flag_russia);
        holder.myimgresof2.setImageResource(R.drawable.flag_russia);
        holder.myTextView.setText(text1);
        holder.myTextView.setText(text2);
        holder.myTextView.setText(text3);
    }


    @Override
    public int getItemCount() {
        return mJobs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myimgres;
        ImageView myimgresof1;
        ImageView myimgresof2;
        TextView myTextView;
        TextView myTextView1;
        TextView myTextView2;
        ViewHolder(View itemView) {
            super(itemView);
            myimgres = itemView.findViewById(R.id.imageres);
            myimgresof1 = itemView.findViewById(R.id.imageres1);
            myimgresof2 = itemView.findViewById(R.id.imageres2);

            myTextView = itemView.findViewById(R.id.tvname1);
            myTextView1 = itemView.findViewById(R.id.tvname2);
            myTextView2 = itemView.findViewById(R.id.tvname3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
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