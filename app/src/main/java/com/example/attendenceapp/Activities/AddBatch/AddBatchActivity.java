package com.example.attendenceapp.Activities.AddBatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attendenceapp.databinding.ActivityAddBatchBinding;
import com.example.attendenceapp.model.BatchModel;
import com.example.attendenceapp.R;
import com.example.attendenceapp.Utils;

import java.util.Objects;

public class AddBatchActivity extends AppCompatActivity {
     ActivityAddBatchBinding binding;
    AddBatchViewModel viewModel;
   // MutableLiveData<BatchPOJO> batch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AddBatchViewModel.class);
        binding.CapturedImage.setOnClickListener(this::Onclick);
        binding.Create.setOnClickListener(this::Onclick);
        viewModel.getCompleteLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.Progress.setVisibility(View.GONE);
                Toast.makeText(AddBatchActivity.this, "Batch Added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void Onclick(View view) {
        int id = view.getId();
        if(id == binding.Create.getId()){
            BatchModel batch = new BatchModel();
            batch.setTeacherName(Objects.requireNonNull(binding.TeacherName.getText()).toString());
            batch.setClassTime(Objects.requireNonNull(binding.ClassTime.getText()).toString());
            batch.setBatchName(Objects.requireNonNull(binding.BatchName.getText()).toString());
            batch.setImage(Utils.convertImageToString(binding.CapturedImage));
            viewModel.setBatch(batch);
            binding.Progress.setVisibility(View.VISIBLE);
        }

        if(id == binding.CapturedImage.getId()){
                chooseProfilePicture();
        }
    }


    public void chooseProfilePicture() {
        // Creating Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.alert_dialog_profile_picture,null);
        builder.setCancelable(true);
        builder.setView(dialogView);
        // finding buttons via ID
        ImageView imageCamera = dialogView.findViewById(R.id.ImageCamera);
        ImageView imageGallery = dialogView.findViewById(R.id.ImageGallery);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //  When Clicked On Camera
        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermission();
                alertDialog.dismiss();
            }

            private void askCameraPermission() {
                if(ContextCompat.checkSelfPermission(AddBatchActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AddBatchActivity.this,
                            new String[] {Manifest.permission.CAMERA}, 101);
                }
                else openCamera(); }
        });

        // When Clicked On Gallery
        imageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityIfNeeded(pickPhoto,103);
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == 101){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }
            else Toast.makeText(this, "Camera Permission is Required To Use Camera", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void openCamera() {
        Intent Camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(Camera.resolveActivity(getPackageManager()) != null) {
            startActivityIfNeeded(Camera, 102);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 103:
                if(resultCode == RESULT_OK)
                {
                    assert data != null;
                    Uri selectedImage = data.getData();
                    binding.CapturedImage.setImageURI(selectedImage);
                }
                break;
            case 102:
                if(resultCode == RESULT_OK)
                {
                    assert data != null;
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    binding.CapturedImage.setImageBitmap(bitmapImage);
                }
        }
    }

}