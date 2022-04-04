package com.example.attendenceapp.Adapter;

import android.graphics.Bitmap;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.attendenceapp.BR;
import com.example.attendenceapp.R;
import com.example.attendenceapp.Utils;
import com.example.attendenceapp.databinding.RecyclerCardBinding;
import com.example.attendenceapp.databinding.StudentRecyclerCardBinding;
import com.example.attendenceapp.pojo.StudentPOJO;

public class StudentRecyclerAdapter extends RecyclerBuilder<StudentPOJO>{
    @Override
    public void OnBind(BaseViewHolder holder, StudentPOJO model, int position, View view) {
        holder.dataBind(BR.model,model);
        StudentRecyclerCardBinding binding = DataBindingUtil.bind(view);
        if(model.getImage() != null){
            String imageInString = model.getImage();
            Bitmap bitmapImage = Utils.convertStringTOBitmap(imageInString);
            binding.StudentImage.setImageBitmap(bitmapImage);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.student_recycler_card;
    }
}
