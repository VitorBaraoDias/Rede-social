package com.example.redessocial.objetos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redessocial.R;

public class ToastCustom {

    public void show(Context context, String text, boolean isLong) {
        LayoutInflater inflater = LayoutInflater.from(context);

        @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.toast_layout, null);
        ImageView image = (ImageView) layout.findViewById(com.example.redessocial.R.id.toast_image);
        image.setImageResource(R.drawable.icon_pp);

        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(text);

        Toast toast = new Toast(context);

        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
