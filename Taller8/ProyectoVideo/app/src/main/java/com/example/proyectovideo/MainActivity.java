package com.example.proyectovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private VideoView vv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText1);
        vv1=(VideoView)findViewById(R.id.videoView);
    }

    public void tomarVideo(View v) {
        Intent intento1 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File video = new File(getExternalFilesDir(null), et1.getText().toString());
        intento1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(video));
        startActivity(intento1);
    }


    public void recuperarVideo(View v) {
        vv1.setVideoURI(Uri.parse(getExternalFilesDir(null)+"/"+et1.getText().toString()));
        vv1.start();
    }
    public void ver(View v) {
        Intent intento1=new Intent(this,Actividad2.class);
        startActivity(intento1);
    }
}