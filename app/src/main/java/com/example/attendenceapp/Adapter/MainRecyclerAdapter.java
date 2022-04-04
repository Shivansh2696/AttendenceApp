package com.example.attendenceapp.Adapter;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import com.example.attendenceapp.Activities.BatchDetails.BatchDetailsActivity;
import com.example.attendenceapp.BR;
import com.example.attendenceapp.Fragments.AddStudentDetail.AddStudentDetailFragment;
import com.example.attendenceapp.Utils;
import com.example.attendenceapp.databinding.RecyclerCardBinding;
import com.example.attendenceapp.pojo.BatchPOJO;
import com.example.attendenceapp.R;


public class MainRecyclerAdapter extends RecyclerBuilder<BatchPOJO>  {

    @Override
    public void OnBind(BaseViewHolder holder, BatchPOJO model, int position,View view) {
        holder.dataBind(BR.model,model);
        RecyclerCardBinding binding = DataBindingUtil.bind(view);
        if(model.getImage() != null){
            String imageInString = model.getImage();
            Bitmap bitmapImage = Utils.convertStringTOBitmap(imageInString);
            binding.Image.setImageBitmap(bitmapImage);
        }
        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), BatchDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("BatchDetails",model);
            intent.putExtras(bundle);
            getContext().startActivity(intent);
        });

    }
    @Override
    public int getLayoutId() {
        return R.layout.recycler_card;
    }
}
