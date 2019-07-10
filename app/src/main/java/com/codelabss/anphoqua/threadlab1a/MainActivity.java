package com.codelabss.anphoqua.threadlab1a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onUserClick(View view) {

        Toast.makeText(MainActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();

        Thread thread_download = new Thread(){

            @Override
            public void run() {
                try {
                    //download image
                    URL url = new URL("https://www.pngarts.com/files/4/Android-PNG-Picture.png");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream inStream = connection.getInputStream();

                    //convert image to bitmap
                    final Bitmap bitmap = BitmapFactory.decodeStream(inStream);
                    final ImageView imageView = findViewById(R.id.image_view);

                    //show bitmap on image view
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                } catch (IOException e) {
                    Log.e("maxter", "exeption: " +e.toString());
                    e.printStackTrace();
                }
            }
        };

        thread_download.start();
    }
}
