package com.yavuz.caloriecalculator;

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void massage(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
