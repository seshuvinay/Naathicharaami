package com.pv3.naathicharaami;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactsActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    ListView contactsListView;
    private ArrayList<HashMap<String,Object>> contactsArray;
    private String mobile;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == 0) {

            if (grantResults.length == 1 &&grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                startActivity(new
                        Intent("android.intent.action.CALL", Uri.parse("tel:" + mobile)));
            } else {
                Toast.makeText(ContactsActivity.this,"App needs permission to dial a number for you.",Toast.LENGTH_LONG ).show();

            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contactsListView=findViewById(R.id.contacts_listview);
        contactsArray=new ArrayList<>();

        HashMap<String,Object> map0=new HashMap<>();
        map0.put("name","The Bride");
        map0.put("mobile","9014541264");
        map0.put("image",getResources().getDrawable(R.drawable.sneha));

        contactsArray.add(map0);
        HashMap<String,Object> map1=new HashMap<>();
        map1.put("name","The Groom");
        map1.put("mobile","9652202839");
        map1.put("image",getResources().getDrawable(R.drawable.rohit));

        contactsArray.add(map1);
        HashMap<String,Object> map2=new HashMap<>();
        map2.put("name","Mr. Pardhasaradhi");
        map2.put("mobile","9951032758");
        map2.put("image",getResources().getDrawable(R.drawable.sarathi));

        contactsArray.add(map2);
        HashMap<String,Object> map3=new HashMap<>();
        map3.put("name","Smt. Devika");
        map3.put("mobile","9014272269");
        map3.put("image",getResources().getDrawable(R.drawable.devika));

        contactsArray.add(map3);
        HashMap<String,Object> map4=new HashMap<>();
        map4.put("name","Dr. Soumya");
        map4.put("mobile","7382191375");
        map4.put("image",getResources().getDrawable(R.drawable.soumya));

        contactsArray.add(map4);
        HashMap<String,Object> map5=new HashMap<>();
        map5.put("name","Mr. Ravi Kumar Joshi");
        map5.put("mobile","8125814190");
        map5.put("image",getResources().getDrawable(R.drawable.ravi));

        contactsArray.add(map5);

        HashMap<String,Object> map6=new HashMap<>();
        map6.put("name","Smt. Renuka");
        map6.put("mobile","8125814190");
        map6.put("image",getResources().getDrawable(R.drawable.renuka));

        contactsArray.add(map6);

        HashMap<String,Object> map7=new HashMap<>();
        map7.put("name","Mr. Seshu Vinay");
        map7.put("mobile","9010600027");
        map7.put("image",getResources().getDrawable(R.drawable.vinay));

        contactsArray.add(map7);

        HashMap<String,Object> map8=new HashMap<>();
        map8.put("name","Mr. Bhimsen Joshi");
        map8.put("mobile","9963528105");
        map8.put("image",getResources().getDrawable(R.drawable.bhim));

        contactsArray.add(map8);



        contactsListView.setAdapter(new ContactsAdapter(ContactsActivity.this,contactsArray));

    }

    public void call(final String mobile){
        if((ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)){
                            startActivity(new
                                    Intent("android.intent.action.CALL", Uri.parse("tel:" + mobile)));
                        }else{
                            ContactsActivity.this.mobile=mobile;
                            ActivityCompat.requestPermissions(ContactsActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    0);
                        }
    }
    
   
}
