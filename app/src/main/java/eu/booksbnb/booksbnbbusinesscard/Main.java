package eu.booksbnb.booksbnbbusinesscard;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    int index = 0;
    int[] images = new int[6];
    String[] picNames = new String[6];
    String[] picDesc = new String[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images[0] = R.drawable.booksbnblogonew;
        images[1]= R.drawable.salaapp;
        images[2]= R.drawable.tolkien;
        images[3]= R.drawable.folletttwin;
        images[4] = R.drawable.follettdouble;
        images[5]= R.drawable.gaiman;
        picNames[0] = "";
        picNames[1] = getResources().getString(R.string.hall);
        picNames[2] = getResources().getString(R.string.tolkien);
        picNames[3] = getResources().getString(R.string.follett);
        picNames[4] = getResources().getString(R.string.follett);
        picNames[5] = getResources().getString(R.string.gaiman);
        picDesc[0] = "";
        picDesc[1] = getResources().getString(R.string.hallDesc);
        picDesc[2] = getResources().getString(R.string.tolkienDesc);
        picDesc[3] = getResources().getString(R.string.follettTwinDesc);
        picDesc[4] = getResources().getString(R.string.follettDoubleDesc);
        picDesc[5] = getResources().getString(R.string.gaimanDesc);
    }

    public void openWebsite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.booksbnb.eu"));
        startActivity(intent);
    }
    public void callBnB(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "+393703438349"));
        startActivity(intent);
    }

    public void openFacebook(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/489907131199202"));
        startActivity(intent);
    }

    public void openInstagram(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com/_u/booksbnb/"));
        intent.setPackage("com.instagram.android");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/booksbnb/")));
        }
    }
    public void openMaps(View view) {
        String address = "http://maps.google.com/maps?q=Books+Beds+Breakfast+Roma";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
        startActivity(intent);
    }
    public void sendEmail(View view) {
        String email = "info@booksbnb.eu";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "New Inquiry");
        startActivity(intent);
    }

    public void changeImage(View view) {
        if (index < 5) {
            index++;
        } else {
            index = 0;
        }
        ImageView logoView = (ImageView) findViewById(R.id.bnb_logo);
        TextView nameView = (TextView) findViewById(R.id.pic_name);
        TextView descView = (TextView) findViewById(R.id.pic_desc);
        logoView.setImageResource(images[index]);
        nameView.setText(picNames[index]);
        descView.setText(picDesc[index]);
    }
}
