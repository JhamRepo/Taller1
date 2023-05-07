package com.example.proyectoaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void presionGato(View v) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.gato);
        mp.start();
    }

    public void presionLeon(View v) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.leon);
        mp.start();
    }
}