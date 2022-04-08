package com.example.attendenceapp.Activities.BatchDetails;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.attendenceapp.FirebaseDB.FirebaseDB;
import com.example.attendenceapp.Fragments.AddStudentDetail.AddStudentDetailFragment;
import com.example.attendenceapp.R;
import com.example.attendenceapp.databinding.ActivityBatchDetailsBinding;
import com.example.attendenceapp.databinding.FragmentAddStudentDetailBinding;
import com.example.attendenceapp.model.BatchModel;

public class BatchDetailsActivity extends AppCompatActivity {
    ActivityBatchDetailsBinding binding;
    BatchModel batch;
    AddStudentDetailFragment detailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBatchDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        batch = (BatchModel) getIntent().getExtras().getSerializable("BatchDetails");
        detailFragment = new AddStudentDetailFragment();
        replaceFrame(detailFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.delete)
        {
            FirebaseDB.getInstance().getBatchDB().remove(batch.getKey()).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(BatchDetailsActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(BatchDetailsActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            });
        }

        return super.onOptionsItemSelected(item);
    }

    public void replaceFrame(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame,fragment)
                .commit();
    }
}
