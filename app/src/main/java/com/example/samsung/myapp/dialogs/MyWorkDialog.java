package com.example.samsung.myapp.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.samsung.myapp.MainActivity;
import com.example.samsung.myapp.OrderTextDialogForList;
import com.example.samsung.myapp.R;
import com.example.samsung.myapp.domain.Login;
import com.example.samsung.myapp.domain.Order;
import com.example.samsung.myapp.rest.LoginApiService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyWorkDialog extends DialogFragment {
    Context mContext;
    private int last_index;

    private Order o;
    private static final String ARG_MY_CLASS = "spot_arg";
    public static MyWorkDialog newInstance(Order o) {
        MyWorkDialog fragment = new MyWorkDialog();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MY_CLASS, o);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            o = (Order) getArguments().getSerializable(ARG_MY_CLASS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.myworkdialog,null);
        TextView name = view.findViewById(R.id.Textname);
        name.setText(o.getName());
        TextView desc = view.findViewById(R.id.editTextDesc);
        desc.setText(o.getDesc());
        TextView price = view.findViewById(R.id.editTextPrice);
        price.setText(o.getPrice());
        TextView tg = view.findViewById(R.id.textViewtg);
        tg.setText(o.getTg());
        TextView phone = view.findViewById(R.id.textViewephone);
        phone.setText(o.getPhone());
        TextView email = view.findViewById(R.id.textViewemail);
        email.setText(o.getEmail());

        if(o.getMsg().equals("none") == false && o.getMsg().equals("gs://neighbours-f1462.appspot.com/images/image5.jpg") == false ){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://neighbours-f1462.appspot.com");
            StorageReference imageRef = storageRef.child(o.getMsg());
            ImageView img = view.findViewById(R.id.imageview);
            Task<Uri> uriTask = imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String downloadUrl = uri.toString();
                    Glide.with(getContext())
                            .load(downloadUrl)
                            .into(img);
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



        Button add = view.findViewById(R.id.button3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        //builder.setTitle("Создать заказ")
        //
        //        .setPositiveButton("Отменить", new DialogInterface.OnClickListener() {
        //            public void onClick(DialogInterface dialog, int id) {
        //                // Закрываем диалоговое окно
        //                dialog.cancel();
        //            }
        //        });

        return view;
    }
}
