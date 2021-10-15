package com.pv3.naathicharaami;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VenueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        findViewById(R.id.navigate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW",
                        Uri.parse("https://goo.gl/maps/HHuaqHrohV52")));
            }
        });
        findViewById(R.id.map_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW",
                        Uri.parse("https://goo.gl/maps/HHuaqHrohV52")));
            }
        });
        findViewById(R.id.ola_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://bit.ly/3CkBAbc";
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url))); startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)));

            }
        });
        findViewById(R.id.uber_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String url="uber://?client_id=Nuz3w03TCXsCA-tPGDOwGxF7l7AXRjD8&action=setPickup&pickup[latitude]=&pickup[longitude]=&pickup[nickname]=Current Location&pickup[formatted_address]=&dropoff[latitude]=17.391853&dropoff[longitude]=78.485322&dropoff[nickname]=Sneha Weds Rohith, Kutchi Bhavan&dropoff[formatted_address]=&product_id=";
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)));
            }
        });

        if(isUberInstalled()){
            findViewById(R.id.uber_btn).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.uber_btn).setVisibility(View.GONE);
        }
    }

    private boolean isUberInstalled() {
        try {
            getPackageManager().getPackageInfo("com.ubercab", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
