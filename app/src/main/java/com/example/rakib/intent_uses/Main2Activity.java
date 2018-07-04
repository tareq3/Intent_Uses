package com.example.rakib.intent_uses;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();

          String s=  intent.getStringExtra(Intent.EXTRA_TEXT); //by using the inBuilt key

            String s2 = intent.getStringExtra(MainActivity.MY_KEY); //By using the custom key

        Toast.makeText(this, s2+" and "+s, Toast.LENGTH_SHORT).show();

    }

}
