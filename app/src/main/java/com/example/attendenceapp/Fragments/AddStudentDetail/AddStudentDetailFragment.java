package com.example.attendenceapp.Fragments.AddStudentDetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.attendenceapp.Activities.AddStudent.AddStudentActivity;
import com.example.attendenceapp.Activities.BatchDetails.BatchDetailsActivity;
import com.example.attendenceapp.Adapter.StudentRecyclerAdapter;
import com.example.attendenceapp.Fragments.MakeAttendence.MakeAttendenceFragment;
import com.example.attendenceapp.Utils;
import com.example.attendenceapp.databinding.FragmentAddStudentDetailBinding;
import com.example.attendenceapp.model.BatchModel;
import com.example.attendenceapp.model.StudentModel;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddStudentDetailFragment extends Fragment {
    FragmentAddStudentDetailBinding binding;
    RecyclerView recyclerView;
    BatchModel batch;
    StudentRecyclerAdapter adapter;
    private List<StudentModel> studentList = new ArrayList<>();
    StudentDetailViewModel viewModel;
    MakeAttendenceFragment makeAttendanceFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddStudentDetailBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(StudentDetailViewModel.class);
        batch = (BatchModel) getActivity().getIntent().getExtras().getSerializable("BatchDetails");
        recyclerView = binding.StudentRecyclerView;
        makeAttendanceFragment =  new MakeAttendenceFragment();
        adapter = new StudentRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getStudentList(batch.getKey()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null) {
                    studentList = value.toObjects(StudentModel.class);
                    adapter.setList(studentList);
                }else{
                    Toast.makeText(getContext(), "Error :" + error, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(batch.getImage() != null){
            String imageInString = batch.getImage();
            Bitmap bitmapImage = Utils.convertStringTOBitmap(imageInString);
            binding.teacherImage.setImageBitmap(bitmapImage);
        }
        binding.BatchName.setText(batch.getBatchName());
        binding.AddStudent.setOnClickListener(this::Onclick);
        binding.MakeAttendence.setOnClickListener(this::Onclick);

    }

    private void Onclick(View view) {
        int id = view.getId();
        Bundle bundle = new Bundle();
        bundle.putSerializable("batchModel", batch);
        if (id == binding.AddStudent.getId()) {
            Intent intent = new Intent(getContext(), AddStudentActivity.class);
            startActivity(intent.putExtras(bundle));
        }
        if (id == binding.MakeAttendence.getId()) {
            BatchDetailsActivity activity = (BatchDetailsActivity) getActivity();
            makeAttendanceFragment.setArguments(bundle);
            activity.replaceFrame(makeAttendanceFragment);
        }
    }
}