package com.example.samsung.myapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsung.myapp.adapter.JobsAdapter;
import com.example.samsung.myapp.domain.Order;
import com.example.samsung.myapp.domain.UserInfo;
import com.example.samsung.myapp.rest.LoginApiService;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_fragment extends Fragment {
    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        //MapKitFactory.initialize(this);


        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        LoginApiService.getInstance().geUserInfo(MainActivity.auth_id).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                Log.d(MainActivity.REST, response.body().getEmail());
                TextView name = rootView.findViewById(R.id.textViewName);
                name.setText(response.body().getLogin());
                TextView phone = rootView.findViewById(R.id.textViewPhone);
                phone.setText(response.body().getPhone());
                TextView tg = rootView.findViewById(R.id.textViewTG3);
                tg.setText(response.body().getTg());


            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.d(MainActivity.REST, t.getMessage());
            }
        });
        return rootView;
    }
}
