package com.pv3.naathicharaami;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactsAdapter extends BaseAdapter {
    private final Context ctx;
    private final ArrayList<HashMap<String, Object>> contactsList;

    public ContactsAdapter(Context ctx, ArrayList<HashMap<String,Object>> contaactsList) {
        this.ctx=ctx;
        this.contactsList=contaactsList;
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(ctx).inflate(R.layout.contacts_li,
                    parent, false);
            holder.img=convertView.findViewById(R.id.contact_img);
            holder.name=convertView.findViewById(R.id.contact_name);
            holder.root=convertView.findViewById(R.id.contact_root);
            holder.whatsapp=convertView.findViewById(R.id.whatsapp);
            holder.call=convertView.findViewById(R.id.call);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final HashMap<String,Object> map= (HashMap<String, Object>) getItem(position);
        holder.name.setText(map.get("name").toString());
        holder.img.setImageDrawable((Drawable) map.get("image"));
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ContactsActivity)ctx).call(map.get("mobile").toString());
            }
        });
        holder.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://wa.me/91"+map.get("mobile").toString();
                        ctx.startActivity(new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(url))); startActivity(new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(url)));
            }
        });


        return convertView;
    }

    class ViewHolder{
        RoundedImageView img;
        TextView name;
        View root;
        View whatsapp;
        View call;
    }

}
