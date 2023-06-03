package com.example.samsung.myapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samsung.myapp.R;
import com.example.samsung.myapp.domain.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Integer> mViewImg;
    private List<String> mJobs;
    private List<Order> myorder;

    private LayoutInflater mInflater;
    private Context context;
    private ItemClickListener mClickListener;

    public MyAdapter(Context context, List<Order> myorder) {
        this.mInflater = LayoutInflater.from(context);
        this.myorder = myorder;
        this.context = context;
    }


    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item1, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        System.out.println(myorder.get(position).getName());
        if(myorder.get(position).getMsg().equals("none") == false && myorder.get(position).getMsg().equals("gs://neighbours-f1462.appspot.com/images/image5.jpg") == false ){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://neighbours-f1462.appspot.com");
            StorageReference imageRef = storageRef.child(myorder.get(position).getMsg());

            Task<Uri> uriTask = imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String downloadUrl = uri.toString();
                    Glide.with(context)
                            .load(downloadUrl)
                            .into(holder.myView);
                    System.out.println("OK");
                    // здесь вы можете использовать полученную ссылку на файл
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    System.out.println("ERRERER");// обработка ошибок получения ссылки на файл
                }
            });
        }

        holder.myView.setImageResource(R.drawable.flag_russia);
        holder.myTextView.setText(myorder.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return myorder.size();
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