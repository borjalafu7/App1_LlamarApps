package com.borjalapa.app1_llamarapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
    AÑADIR TODO ESTO AL MANIFEST.XML PARA QUE SE PUEDAN ENVIAR LAS COSAS
            <intent-filter>
                <action android:name="android.intent.action.SEND" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="image/*" />
            </intent-filter>
            <intent-filter>
                <action android:name="android.intent.action.SEND" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="text/plain" />
            </intent-filter>
            <intent-filter>
                <action android:name="android.intent.action.SEND_MULTIPLE" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:mimeType="image/*" />
            </intent-filter>
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retrasar la actividad 3 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                empezar();
            };
        }, 3000);
    }

    private void empezar() {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        //intent con su accion y su tipo
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        //si la accion es de tipo texto plano o imagen hace cosas diferentes
        if ( Intent.ACTION_SEND.equals(action) && type != null){
            if ("text/plain".equals(type)){
                handleSendText(intent);
            }else if (type.startsWith("image/")){
                handleSendImage(intent);
            }
        }else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null){
            if (type.startsWith("image/")){
                handleSendMultipleImages(intent);
            }
        }else{

        }

    }

    //manejador de texto para cuando la otra app te pasa start mostrar el boton que estaba oculto
    private void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText != null){
                if (sharedText.toLowerCase().compareTo("start")==0){
                    ((Button)findViewById(R.id.button)).setVisibility(View.VISIBLE);
                }
            }
    }


    //crea un intent para cuando apretes el boton le pasa el texto del editText
    public void devolver(View view) {
        Intent i = new Intent();
        i.putExtra("RESULTADO", ((EditText)findViewById(R.id.textInput)).getText().toString());
        setResult(RESULT_OK,i);
        finish();
    }


    private void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
            if (imageUris != null){
                //Update UI to reflect multiple images being shared
            }
    }

    private void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
            if (imageUri != null){
                //Update UI to reflect image being shared
            }
    }


}