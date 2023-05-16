package com.example.samsung.myapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.samsung.myapp.domain.Order;
import com.example.samsung.myapp.domain.Spot;

public class OrderTextDialogForList extends DialogFragment {
    Context mContext;
    private int last_index;

    private Order o;
    private static final String ARG_MY_CLASS = "spot_arg";
    public static OrderTextDialogForList newInstance(Order o) {
        OrderTextDialogForList fragment = new OrderTextDialogForList();
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

        View view = getLayoutInflater().inflate(R.layout.add_order,null);
        TextView name = view.findViewById(R.id.Textname);
        name.setText(o.getName());
        TextView desc = view.findViewById(R.id.editTextDesc);
        desc.setText(o.getDesc());


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