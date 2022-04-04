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
import com.example.attendenceapp.Adapter.StudentRecyclerAdapter;
import com.example.attendenceapp.Utils;
import com.example.attendenceapp.databinding.FragmentAddStudentDetailBinding;
import com.example.attendenceapp.pojo.BatchPOJO;
import com.example.attendenceapp.pojo.StudentPOJO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class AddStudentDetailFragment extends Fragment {
    FragmentAddStudentDetailBinding binding;
    RecyclerView recyclerView;
    BatchPOJO batch;
    StudentRecyclerAdapter adapter;
    private final List<StudentPOJO> studentList = new ArrayList<>();
    StudentDetailViewModel viewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddStudentDetailBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(StudentDetailViewModel.class);
        batch = (BatchPOJO) getActivity().getIntent().getExtras().getSerializable("BatchDetails");
        recyclerView = binding.StudentRecyclerView;
        adapter = new StudentRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getStudentList().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot child : snapshot.getChildren()){
                    StudentPOJO student = child.getValue(StudentPOJO.class);
                    student.setKey(child.getKey());
                    studentList.add(student);
                }
                adapter.setList(studentList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "There is No Data In The List", Toast.LENGTH_SHORT).show();
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

    }

    private void Onclick(View view) {
        int id = view.getId();
        if(id == binding.AddStudent.getId()){
            Intent intent = new Intent(getContext(), AddStudentActivity.class);
            startActivity(intent);
        }
    }
//    public void replaceFrame(Fragment fragment){
//        getChildFragmentManager()
//                .beginTransaction()
//                .replace(R.id.mainFrame,fragment)
//                .addToBackStack(fragment.getClass().getName())
//                .commit();
//    }
}