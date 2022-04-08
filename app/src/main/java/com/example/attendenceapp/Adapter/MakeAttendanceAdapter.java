package com.example.attendenceapp.Adapter;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import com.example.attendenceapp.R;
import com.example.attendenceapp.model.AttendenceModel;
import com.example.attendenceapp.model.StudentModel;
import java.util.ArrayList;
import java.util.List;


public class MakeAttendanceAdapter extends RecyclerBuilder<StudentModel>{
    private List<AttendenceModel> attendenceModelList;

    public MakeAttendanceAdapter() {
        attendenceModelList = new ArrayList<>();
    }

    @Override
    public void OnBind(BaseViewHolder holder, StudentModel model, int position, View view) {
        MakeAttendanceCardBinding binding = DataBindingUtil.bind(view);
        holder.dataBind(BR.model,model);
        AttendenceModel attendenceModel = new AttendenceModel();
        attendenceModel.setStudentId(model.getStudentId());
        attendenceModel.setTeacherId(model.getTeacherID());
        attendenceModelList.add(attendenceModel);
        binding.StudentCheck.setOnClickListener(view1 -> attendenceModel.setPresent(binding.StudentCheck.isChecked()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.make_attendance_card;
    }

    public List<AttendenceModel> getAttendenceModelList() {
        return attendenceModelList;
    }
}
