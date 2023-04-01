package com.example.datastorage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.datastorage.R;
import com.example.datastorage.model.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context,  List<User> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, @Nullable View convertView,  ViewGroup parent) {
        User user = getItem(position);
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext())
                         .inflate(R.layout.layout_listviewuser,parent,false);
        }
        TextView txvNamaUser = (TextView) convertView.findViewById(R.id.txvNama);
        TextView txvNoTelp = (TextView) convertView.findViewById(R.id.txvNotelp);
        txvNamaUser.setText(user.getNama());
        txvNoTelp.setText(user.getNotlp());
        return convertView;

    }
}
