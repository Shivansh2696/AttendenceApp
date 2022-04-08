package com.example.attendenceapp.Adapter;

import android.graphics.Bitmap;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.attendenceapp.BR;
import com.example.attendenceapp.R;
import com.example.attendenceapp.Utils;
import com.example.attendenceapp.databinding.StudentRecyclerCardBinding;
import com.example.attendenceapp.model.StudentModel;

public class StudentRecyclerAdapter extends RecyclerBuilder<StudentModel>{
    @Override
    public void OnBind(BaseViewHolder holder, StudentModel model, int position, View view) {
        holder.dataBind(BR.model,model);
        StudentRecyclerCardBinding binding = DataBindingUtil.bind(view);
        if(model.getStudentImage() != null){
            String imageInString = model.getStudentImage();
            Bitmap bitmapImage = Utils.convertStringTOBitmap(imageInString);
            binding.StudentImage.setImageBitmap(bitmapImage);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.student_recycler_card;
    }
}
