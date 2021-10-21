package com.pv3.naathicharaami;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class LandingActivity extends AppCompatActivity {


    private Calendar weddingDate;
    private long weddingMillis;
    private long currentTimeMillis;
    private CountDownTimer cTimer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cTimer.cancel();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        weddingDate = Calendar.getInstance();
        weddingDate.set(2021,10,10,10,29,0);
        weddingDate.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        weddingMillis=weddingDate.getTimeInMillis();

        currentTimeMillis=System.currentTimeMillis();

        findViewById(R.id.calendar_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
try{Calendar beginTime = Calendar.getInstance();
            beginTime.set(2021,10,10,8,0,0);
            beginTime.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));

            Calendar endTime = Calendar.getInstance();
            endTime.set(2021,10,10,13,0,0);
            endTime.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
              startActivity(new
                      Intent("android.intent.action.INSERT").setData(Uri.parse("content://com.android.calendar/events")).putExtra("beginTime",
                        beginTime.getTimeInMillis()).putExtra("endTime",
                        endTime.getTimeInMillis()).putExtra("title", "Naathicharaami-The promise!").putExtra("description", "Sneha weds Rohith").putExtra("eventLocation", "Kutchi Bhavan, Ramakoti, Hyderabad").putExtra("availability", 0));
            }}catch(Exception e){
            Toast.makeText(LandingActivity.this,"Google calendar not ready.",Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.covid_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingActivity.this,CovidActivity.class));
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        findViewById(R.id.share_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction("android.intent.action.SEND");
                sendIntent.putExtra("android.intent.extra.TEXT",
                        "Sneha and Rohith are getting tangled, knotted, locked and tied to each other. Be part of their joyous celebration with this amazing app. https://play.google.com/store/apps/details?id=com.pv3.naathicharaami");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        findViewById(R.id.rating_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=com.pv3.naathicharaami")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=com.pv3.naathicharaami")));

                }
            }
        });
        findViewById(R.id.location_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingActivity.this,VenueActivity.class));
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
        findViewById(R.id.contacts_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingActivity.this,ContactsActivity.class));
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        if(weddingMillis-currentTimeMillis>10000){
            ((TextView)findViewById(R.id.timer_pretext)).setText("Sneha and Rohith are gonna marry in");
            findViewById(R.id.timer_txt).setVisibility(View.VISIBLE);
            cTimer=new CountDownTimer(weddingMillis-System.currentTimeMillis() , 500){
                @Override
                public void onTick(long millisUntilFinished) {
                    int secondsRemaining= (int) ((weddingMillis-System.currentTimeMillis())/1000);
                    int day = (int) TimeUnit.SECONDS.toDays(secondsRemaining);
                    long hours = TimeUnit.SECONDS.toHours(secondsRemaining) - (day *24);
                    long minute = TimeUnit.SECONDS.toMinutes(secondsRemaining) - (TimeUnit.SECONDS.toHours(secondsRemaining)* 60);
                    long second = TimeUnit.SECONDS.toSeconds(secondsRemaining) - (TimeUnit.SECONDS.toMinutes(secondsRemaining) *60);

                    ((TextView)findViewById(R.id.timer_txt)).setText(day+"d "+hours+"h "+minute+"m "+second+"s");


                }

                @Override
                public void onFinish() {
                    ((TextView)findViewById(R.id.timer_pretext)).setText("Sneha weds Rohith");
                    findViewById(R.id.timer_txt).setVisibility(View.GONE);
                }
            };
            cTimer.start();


        }else{
            ((TextView)findViewById(R.id.timer_pretext)).setText("Sneha weds Rohith");
            findViewById(R.id.timer_txt).setVisibility(View.GONE);
        }


    }
}
