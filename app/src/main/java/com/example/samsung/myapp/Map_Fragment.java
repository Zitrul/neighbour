package com.example.samsung.myapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.samsung.myapp.adapter.JobsAdapter;
import com.example.samsung.myapp.domain.Order;
import com.example.samsung.myapp.domain.Spot;
import com.example.samsung.myapp.rest.LoginApiService;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Map_Fragment extends Fragment {
    private final String MAPKIT_API_KEY = "4337699f-6238-40c5-be87-6effac21ff24";
    private final Point TARGET_LOCATION = new Point(59.957086, 30.308234);
    private PlacemarkMapObject placemark;
    private MapView mapView;
    private MapView yandexMap;
    private  Context mContext;
    public static Spot clicked_spot;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Установите ключ API
        if(MapActivity.status_of_factory == 1){
            MapKitFactory.setApiKey(MAPKIT_API_KEY);
            // Инициализируйте MapKit
            MapKitFactory.initialize(getContext());
            MapActivity.status_of_factory = 0;
        }


        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = rootView.findViewById(R.id.mapview);


        MapObjectCollection markers = mapView.getMap().getMapObjects();

        LoginApiService.getInstance().getPoint().enqueue(new Callback<List<Spot>>() {
            @Override
            public void onResponse(Call<List<Spot>> call, Response<List<Spot>> response) {
                Log.d(MainActivity.REST+"SPOT", response.body().toString());



                for(int i = 0; i < response.body().size(); i ++){
                    Log.d(MainActivity.REST,response.body().get(i).getName());
                    System.out.println(response.body().get(i).getX()+"Y:"+ response.body().get(i).getY());
                    PlacemarkMapObject placemark = markers.addPlacemark(new Point(response.body().get(i).getX(), response.body().get(i).getY()));
                    placemark.setIcon(ImageProvider.fromResource(getActivity(), R.drawable.img_loc));
                    placemark.setUserData(response.body().get(i));
                    placemark.addTapListener(new MapObjectTapListener() {


                        @Override
                        public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
                            Spot s = (Spot) placemark.getUserData();
                            OrderTextDialog dialogFragment = OrderTextDialog.newInstance(s);
                            dialogFragment.show(getFragmentManager(), "spot_fragment");
                            return false;
                        }
                    });
                }
                System.out.println(markers);


            }

            @Override
            public void onFailure(Call<List<Spot>> call, Throwable t) {
                Log.d(MainActivity.REST, t.getMessage());
            }
        });

        //placemark = markers.addPlacemark(new Point(59.957086, 30.308234));
        //placemark.setIcon(ImageProvider.fromResource(getActivity(), R.drawable.flag_russia));

        return rootView;
    }

    @Override
    public void onStop() {

        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {

        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

}