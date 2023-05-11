package com.example.samsung.myapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
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
import java.util.zip.Inflater;

import androidx.fragment.app.Fragment;
public class Map_Fragment extends Fragment {
    private final String MAPKIT_API_KEY = "4337699f-6238-40c5-be87-6effac21ff24";
    private final Point TARGET_LOCATION = new Point(59.957086, 30.308234);
    private PlacemarkMapObject placemark;
    private MapView mapView;
    private MapView yandexMap;
    private  Context mContext;
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

        mapView.getMap().move(new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5), null);

        MapObjectCollection markers = mapView.getMap().getMapObjects();
        placemark = markers.addPlacemark(new Point(59.957086, 30.308234));
        placemark.setIcon(ImageProvider.fromResource(getActivity(), R.drawable.flag_russia));

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
    private void sendMessage(final String msg) {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Socket s = new Socket("192.168.123.61", 5000);

                    OutputStream out = s.getOutputStream();

                    PrintWriter output = new PrintWriter(out);

                    output.println(msg);
                    output.flush();
                    /*BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String s = mTextViewReplyFromServer.getText().toString();
                            if (st.trim().length() != 0)
                                mTextViewReplyFromServer.setText(s + "\nFrom Server : " + st);
                        }
                    });*/

                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}