package com.example.attendenceapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.ByteArrayOutputStream;

public class Utils {

    public static String convertImageToString(ImageView capturedImage) {
        capturedImage.buildDrawingCache();
        Bitmap bitmap = capturedImage.getDrawingCache();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,80,byteArrayOutputStream);
        byte[] b = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(b,Base64.DEFAULT);
    }

    public static Bitmap convertStringTOBitmap(String stringImage){
        byte[] decodedString = Base64.decode(stringImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
    }

}
