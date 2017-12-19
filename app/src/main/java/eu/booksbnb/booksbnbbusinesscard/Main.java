package eu.booksbnb.booksbnbbusinesscard;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends AppCompatActivity {
    /**
     * Initialize the image list, picNames, picDescriptions, and index int for the carousel
     */
    int index = 0;
    int[] images = new int[6];
    String[] picNames = new String[6];
    String[] picDesc = new String[6];

    /**
     * In the onCreate method declare the images in the image list, together with all the
     * names of the pics and descriptions.
     * Set up the swipe left and right methods for the carousel
     */
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
        //Select the section that should scroll the image
        LinearLayout logoScrolling = (LinearLayout) findViewById(R.id.logo_section);
        logoScrolling.setOnTouchListener(new OnSwipeTouchListener(Main.this) {
            public void onSwipeRight() {
                //If the index is 0, set it ti 5 so the image carousel wraps
                if (index == 0) {
                    index = 5;
                } else {
                    index--;
                }
                changeImage();
            }
            public void onSwipeLeft() {
                //If the index is 5, set it to 0 so it wraps
                if (index < 5) {
                    index++;
                } else {
                    index = 0;
                }
                changeImage();
            }
        });
        //B&B Name Span
        String bName = getResources().getString(R.string.booksBedsBreakfast);
        SpannableString ss1 = new SpannableString(bName);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,1, 0);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 7,8, 0);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 11,15, 0);
        TextView mainName = (TextView) findViewById(R.id.booksBedsBreakfast);
        mainName.setText(ss1);
        //Address Name Span
        String address = getResources().getString(R.string.address);
        SpannableString ss2 = new SpannableString(address);
        ss2.setSpan(new RelativeSizeSpan(1.3f), 0, 1, 0);
        ss2.setSpan(new RelativeSizeSpan(1.3f), 4, 5, 0);
        ss2.setSpan(new RelativeSizeSpan(1.3f), 10, 11, 0);
        ss2.setSpan(new RelativeSizeSpan(1.3f), 24, 27, 0);
        ss2.setSpan(new RelativeSizeSpan(1.3f), 29, 30, 0);
        TextView addressView = (TextView) findViewById(R.id.address_view);
        addressView.setText(ss2);
    }

    /**
     * All the Intents methods to link to website, facebook, instagram, maps, phone and email
     */
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
        if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        } else {
            intent.setData(Uri.parse("http://facebook.com/booksbnb"));
            startActivity(intent);
        }
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

    /**
     * Method to change the image when swipes occur
     */
    public void changeImage() {
        //Select the ImageView, TextView for name, and TextView for description
        ImageView logoView = (ImageView) findViewById(R.id.bnb_logo);
        TextView nameView = (TextView) findViewById(R.id.pic_name);
        TextView descView = (TextView) findViewById(R.id.pic_desc);
        //Set new image, name, and description
        logoView.setImageResource(images[index]);
        nameView.setText(picNames[index]);
        descView.setText(picDesc[index]);
    }
}
