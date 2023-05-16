package com.example.samsung.myapp;

import static android.app.Activity.RESULT_OK;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.samsung.myapp.domain.Login;
import com.example.samsung.myapp.rest.LoginApiService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends DialogFragment {
    Context mContext;
    private int last_index;
    private FirebaseStorage storage;
    private static final int SELECT_PICTURE = 1;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация Firebase
        FirebaseApp.initializeApp(getActivity());
    }
    private Uri selectedImageUri;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);




        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                imageView.setImageURI(selectedImageUri);

            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LoginApiService.getInstance().getlastOrder().enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(MainActivity.REST, response.body().getMsg());

                    // выполнить нужные действия с x здесь
                    last_index = Integer.valueOf(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.d(MainActivity.REST, t.getMessage());
            }
        });
        View view = getLayoutInflater().inflate(R.layout.order, null);
        Button add = view.findViewById(R.id.button3);
        imageView = view.findViewById(R.id.imageview);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_PICTURE);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText desc = view.findViewById(R.id.editTextDesc);
                EditText price = view.findViewById(R.id.editTextPrice);
                EditText name = view.findViewById(R.id.Textname);

                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference imagesRef = storageRef.child("images");

                StorageReference imageRef = imagesRef.child("image" + last_index + ".jpg");
                imageRef.putFile(selectedImageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Log.d("Firebase_photo", "GOOD");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Firebase_photo", "😈");
                            }
                        });

                LoginApiService.getInstance().getOrder(last_index + 1, "gs://neighbours-f1462.appspot.com/images/image"+last_index+".jpg", name.getText().toString(), price.getText().toString(), desc.getText().toString(), currentDate, "false", MainActivity.auth_id).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Log.d(MainActivity.REST, response.body().toString());
                        dismiss();

                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.d(MainActivity.REST, t.getMessage());
                    }
                });
                ;

            }
        });


        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
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