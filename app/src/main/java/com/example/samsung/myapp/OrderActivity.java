package com.example.samsung.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.samsung.myapp.adapter.JobsAdapter;
import com.example.samsung.myapp.adapter.MyRecyclerViewAdapter;
import com.example.samsung.myapp.domain.Login;
import com.example.samsung.myapp.rest.LoginApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends DialogFragment {
    Context mContext;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.order,null);
        Button add = view.findViewById(R.id.button3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText desc = view.findViewById(R.id.editTextName);
                EditText price = view.findViewById(R.id.editTextName2);
                EditText name = view.findViewById(R.id.editTextName3);
                System.out.println("HI");
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                LoginApiService.getInstance().getOrder(2,"none",name.getText().toString(),price.getText().toString(),desc.getText().toString(),currentDate,"false",MainActivity.auth_id).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Log.d(MainActivity.REST,response.body().toString());
                        dismiss();

                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.d(MainActivity.REST,t.getMessage());
                    }
                });;

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
        builder.setView(view);
        return builder.create();
    }
}