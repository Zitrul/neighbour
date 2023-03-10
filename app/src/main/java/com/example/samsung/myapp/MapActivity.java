package com.example.samsung.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import com.yandex.mapkit.mapview.MapView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class MapActivity extends Activity {

    private final String MAPKIT_API_KEY = "4337699f-6238-40c5-be87-6effac21ff24";
    private final Point TARGET_LOCATION = new Point(59.957086, 30.308234);

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        Intent intent = new Intent(this, MainActivity2.class);
        setContentView(R.layout.activity_map);
        super.onCreate(savedInstanceState);
        mapView = (MapView)findViewById(R.id.mapview);


        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraPosition position = mapView.getMap().getCameraPosition();

                Log.e("ERREREE",   String.format("lat: %f, lon: %f, zoom: %f, tilt: %f",
                        position.getTarget().getLatitude(),
                        position.getTarget().getLongitude(), position.getZoom(),
                        position.getTilt()));

                sendMessage( String.format("position reg %f, %f ",position.getTarget().getLatitude(),
                        position.getTarget().getLongitude())
                );
                startActivity(intent);
            }
        });
        Log.e("ERREREE", "???????????????? ????????????????????");

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