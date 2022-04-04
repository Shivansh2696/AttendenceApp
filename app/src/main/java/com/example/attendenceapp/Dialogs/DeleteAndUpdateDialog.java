package com.example.attendenceapp.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;

import com.example.attendenceapp.FirebaseDB.FirebaseCommonDB;
import com.example.attendenceapp.FirebaseDB.FirebaseDB;
import com.example.attendenceapp.R;
import com.example.attendenceapp.databinding.DeleteUpdateDialogBinding;
import com.example.attendenceapp.pojo.BatchPOJO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DeleteAndUpdateDialog extends Dialog implements View.OnClickListener{
    DeleteUpdateDialogBinding binding;
    private BatchPOJO batchPOJO;
    public DeleteAndUpdateDialog(@NonNull Context context,BatchPOJO batchPOJO) {
        super(context);
        this.batchPOJO = batchPOJO;
        setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.delete_update_dialog,null,false);
        setContentView(binding.getRoot());
        binding.setVariable(BR.model,batchPOJO);
        binding.btUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            int id = view.getId();
//            if(id == binding.btDelete.getId()){
//                FirebaseDB.getInstance().getBatchDB().remove(batchPOJO.getKey()).addOnCompleteListener(task -> {
//                    if(task.isSuccessful())
//                        Toast.makeText(getContext(), "Table Deleted", Toast.LENGTH_SHORT).show();
//                    else Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
//                    dismiss();
//                });
//            }

            if(id == binding.btUpdate.getId()){
                batchPOJO.setTeacherName(Objects.requireNonNull(binding.edTeacherName).getText().toString());
                FirebaseDB.getInstance().getBatchDB().update(batchPOJO.getKey(),batchPOJO).addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                        Toast.makeText(getContext(), "Table Updated", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    dismiss();
                });
            }

    }
}
