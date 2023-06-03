package com.example.samsung.myapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class WorkerDialog extends DialogFragment {
    public static WorkerDialog newInstance(String tg,String phone) {
        WorkerDialog fragment = new WorkerDialog();
        Bundle args = new Bundle();
        args.putString("param1", tg);
        args.putString("param2", phone);
        fragment.setArguments(args);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String param1 = getArguments().getString("param1");
        String param2 = getArguments().getString("param2");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Отклик на заказ")
                .setMessage("Tg: " + param1 + "\n" + "Phone: " + param2)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем диалоговое окно
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}
