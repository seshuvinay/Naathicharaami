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

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

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



        HashMap<String,Object> map6=new HashMap<>();
        map6.put("name","Mr. Seshu Vinay");
        map6.put("mobile","9010600027");
        map6.put("image",getResources().getDrawable(R.drawable.vinay));

        contactsArray.add(map6);





        contactsListView.setAdapter(new ContactsAdapter(ContactsActivity.this,contactsArray));

    }

    public void onClick(final String mobile, View anchorView) {
        try {
            String[] array={"Call","Whatsapp"};
            final ListPopupWindow popupWindow = new ListPopupWindow(ContactsActivity.this);
            popupWindow.setAdapter(new ArrayAdapter<>(
                    ContactsActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, array));
            popupWindow.setAnchorView(anchorView);
            popupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
            popupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);

            popupWindow.setModal(true);
            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0){
                        if((ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)){
                            startActivity(new
                                    Intent("android.intent.action.CALL", Uri.parse("tel:" + mobile)));
                        }else{
                            ContactsActivity.this.mobile=mobile;
                            ActivityCompat.requestPermissions(ContactsActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    0);
                        }

                    }else{
                        String url="https://wa.me/91"+mobile;
                        startActivity(new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(url))); startActivity(new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(url)));
                    }

                    popupWindow.dismiss();
                }
            });
            popupWindow.show();
        } catch (Exception ignored) {
        }
    }
}
