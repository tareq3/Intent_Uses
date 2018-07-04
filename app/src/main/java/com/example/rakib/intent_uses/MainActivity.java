package com.example.rakib.intent_uses;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public  static  final String MY_KEY="my_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       findViewById(R.id.my_Text).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent=new Intent(MainActivity.this, Main2Activity.class);

               intent.putExtra(Intent.EXTRA_TEXT,"I am from 1"); //A inBuilt key. we can use that when we have to pass single element

               intent.putExtra(MY_KEY,"My name is Tareq"); //custom Key Reciever Activity should have the Access of this key.

               startActivity(intent);
           }
       });
    }

}
