package com.controlvet.notific;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.notific.R;

public class Mainwebloc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainwebloc);
        WebView myWebView = (WebView) findViewById(R.id.webView1);
        myWebView.loadUrl("https://maps.app.goo.gl/hbefHUj5dkQe4ksr9");
    }
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}