package com.example.attendenceapp.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.attendenceapp.Activities.AddBatch.AddBatchActivity;
import com.example.attendenceapp.Adapter.MainRecyclerAdapter;
import com.example.attendenceapp.databinding.ActivityMainBinding;
import com.example.attendenceapp.model.BatchModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RecyclerView recyclerView;
    MainRecyclerAdapter adapter;
    MainViewModel viewModel;
    private List<BatchModel> batchList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        recyclerView = binding.MainRecycler;
        adapter = new MainRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        binding.AddBatch.setOnClickListener(this::OnClick);

       viewModel.getBatch().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if(task.isSuccessful()){
                   batchList.clear();
                   batchList = task.getResult().toObjects(BatchModel.class);

                   adapter.setList(batchList);
               }
           }
       });


    }

    private void OnClick(View view) {
       int id = view.getId();
        if(id == binding.AddBatch.getId()){
            Intent intent = new Intent(this, AddBatchActivity.class);
            startActivity(intent);
        }
    }
}