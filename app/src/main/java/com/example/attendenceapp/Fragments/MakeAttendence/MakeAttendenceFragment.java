package com.example.attendenceapp.Fragments.MakeAttendence;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.attendenceapp.Adapter.MakeAttendanceAdapter;
import com.example.attendenceapp.databinding.FragmentMakeAttendenceBinding;
import com.example.attendenceapp.model.AttendenceModel;
import com.example.attendenceapp.model.BatchModel;
import com.example.attendenceapp.model.StudentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;


public class MakeAttendenceFragment extends Fragment {
    private FragmentMakeAttendenceBinding binding;
    private MakeAttendanceAdapter adapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMakeAttendenceBinding.inflate(inflater,container,false);
        adapter = new MakeAttendanceAdapter();
        recyclerView = binding.MakeAttendanceRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        binding.btCommit.setOnClickListener(this::OnClick);
        BatchModel batchModel = (BatchModel) getArguments().getSerializable("batchModel");
        FirebaseFirestore.getInstance().collection("StudentList").whereEqualTo("batchID",batchModel.getKey())
                                    .get().addOnCompleteListener(task -> {
                                        if(task.isSuccessful()){
                                            adapter.setList(task.getResult().toObjects(StudentModel.class));
                                        }
                                    });
        return binding.getRoot();
    }

    private void OnClick(View view) {
        int id = view.getId();
        if(id == binding.btCommit.getId()){
            long currentTime = new Date().getTime();
            for (AttendenceModel attendenceModel : adapter.getAttendenceModelList()) {
                attendenceModel.setAttendanceDate(currentTime);
            }
        }
    }
}