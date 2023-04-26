package com.example.samsung.myapp;

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

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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
import androidx.fragment.app.Fragment;
public class MapActivityOfMainActivity extends AppCompatActivity {
    private final Point TARGET_LOCATION = new Point(59.957086, 30.308234);
    private PlacemarkMapObject placemark;
    private MapView mapView;
    private MapView yandexMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        //MapKitFactory.initialize(this);
        Intent intent = new Intent(this, MapActivityOfMainActivity.class);

        setContentView(R.layout.map_activity_of_main);
        super.onCreate(savedInstanceState);
        mapView = (MapView)findViewById(R.id.mapview);


        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
        MapObjectCollection markers = mapView.getMap().getMapObjects();
        placemark = markers.addPlacemark(new Point(59.957086, 30.308234));
        //placemark.setOpacity(0.5f);

        //placemark.setIconStyle(new IconStyle().setAnchor(new PointF(0.5f, 0.0f)));
        placemark.setIcon(ImageProvider.fromResource(this, R.drawable.flag_russia));
        Log.e("ERREREE", "Получено исключение");

    }

    @Override
    protected void onStop() {

        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {

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
