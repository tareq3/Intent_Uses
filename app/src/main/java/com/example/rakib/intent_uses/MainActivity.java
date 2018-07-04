package com.example.rakib.intent_uses;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.w3c.dom.Text;

// For knowing about the example of Implicit intent
// please visit :
//          https://developer.android.com/guide/components/intents-common
//


public class MainActivity extends AppCompatActivity {


    String url="http://www.google.com";

    String addressString="Sector 14, Dhaka 1230"; //use the same address you can see in map



    // TODO: 4/14/2018 permission Constants
    private static final int FILE_READ_REQUEST_CODE=42;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       findViewById(R.id.webBrowser).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Uri webPage = Uri.parse(url);

               /* Implicit Intent constructor has two Paremeter:
                    1. Action : what u r trying to do
                    2. Data : what u r passing on to the action.

               */
               Intent intent = new Intent(Intent.ACTION_VIEW, webPage); //action ACTION_VIEW used when we wanna view content

               intent.setPackage("com.android.chrome"); //if you want to complete this action by only this app then use this line

               if (intent.resolveActivity(getPackageManager()) != null) //this condition ask the android is there any app that can handle my action
               {
                   startActivity(intent);

               }
           }
       });





        findViewById(R.id.Map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri.Builder builder=new Uri.Builder();

                builder.scheme("geo")
                        .path("0,0")
                        .appendQueryParameter("q", addressString);

                Uri addressUri=builder.build();

                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(addressUri);//this is just another way to pass the data


                if (intent.resolveActivity(getPackageManager()) != null) //this condition ask the android is there any app that can handle my action
                {
                    startActivity(intent);

                }
            }
        });

        //Todo: Share Intent
        findViewById(R.id.shareText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mimeType = "text/plain"; // mime type is the thing that really sort the choosing app for us
                String title= "Learning how to share";
                String textToShare= "Hello there";

             Intent shareIntent=   ShareCompat.IntentBuilder.from(MainActivity.this)
                                            .setChooserTitle(title)
                                            .setType(mimeType)
                                            .setText(textToShare)
                                            .getIntent();

                if (shareIntent.resolveActivity(getPackageManager()) != null) //this condition ask the android is there any app that can handle my action
                {
                    startActivity(shareIntent);

                }

            }
        });

        findViewById(R.id.share2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sebjectText="My Birthday";
                String body="My description";

                Intent intent=new Intent(Intent.ACTION_SEND); //as we wanna send that like sms or mms or mail

                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,sebjectText);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intent, "Share Text")); //by using a chooser u can customise option panel
            }
        });

        imageView=findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
            }
        });
    }
    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    public void performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");
        intent.putExtra("return-data", true);

        startActivityForResult(intent,FILE_READ_REQUEST_CODE); //we have to use unique key
    }

    //Get the Result from other apps according to our request
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==FILE_READ_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (data != null) {
                uri = data.getData();

                Log.d("" +getClass().getName(), "Uri: " + uri.toString());
                getAttachment(uri);
            }
        }
    }

    private void getAttachment(Uri uri){


        GlideApp.with(MainActivity.this).load(uri).into(imageView);
    }
}
