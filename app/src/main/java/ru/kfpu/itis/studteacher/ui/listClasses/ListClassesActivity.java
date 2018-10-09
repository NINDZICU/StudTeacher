package ru.kfpu.itis.studteacher.ui.listClasses;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.data.models.StudClass;
import ru.kfpu.itis.studteacher.data.models.Student;
import ru.kfpu.itis.studteacher.data.models.User;
import ru.kfpu.itis.studteacher.ui.listStudents.ListStudentsActivity;
import ru.kfpu.itis.studteacher.utilities.InjectorUtils;

public class ListClassesActivity extends AppCompatActivity implements ListClassesAdapter.ListClassesAdapterOnItemClickHandler {

    private RecyclerView rvClasses;
    private ListClassesAdapter adapter;
    private Button btnSave;
    private ProgressBar progressBar;
    private int idTask;

    private ListClassesViewModel listClassesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_classes);
        idTask = getIntent().getIntExtra("ID_TASK", 0);
        rvClasses = findViewById(R.id.rv_classes);
        progressBar = findViewById(R.id.pb_list_classes);
        adapter = new ListClassesAdapter(this.getApplicationContext(), this);
        rvClasses.setLayoutManager(new LinearLayoutManager(this));
        //Todo убрать хардкод
        List<StudClass> studClassList = new ArrayList<>();
        studClassList.add(new StudClass("7 class", null));
        studClassList.add(new StudClass("9 class", null));
        studClassList.add(new StudClass("11 class", null));
        adapter.setStudClassList(studClassList);
        rvClasses.setAdapter(adapter);
        btnSave = findViewById(R.id.btn_save_classes);

        ListClassesViewModelFactory factory = InjectorUtils.provideListClassesViewModelFactory(this.getApplicationContext());
        listClassesViewModel = ViewModelProviders.of(this, factory).get(ListClassesViewModel.class);
//        showLoading();

//        homeViewModel.getListTasks().observe(this, new ApiResponceObserver<List<Task>>() {

        btnSave.setOnClickListener(v -> {

        });
    }

    private void showLoading() {
        rvClasses.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        rvClasses.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int id) {
        Intent intent = new Intent(this, ListStudentsActivity.class);
        intent.putExtra("ID_TASK", idTask);
        intent.putExtra("ID_CLASS", id);
        startActivity(intent);
    }
}
