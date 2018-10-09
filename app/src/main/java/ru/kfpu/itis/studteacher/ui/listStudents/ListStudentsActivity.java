package ru.kfpu.itis.studteacher.ui.listStudents;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.data.models.Student;
import ru.kfpu.itis.studteacher.data.models.User;
import ru.kfpu.itis.studteacher.data.util.ApiResponceObserver;
import ru.kfpu.itis.studteacher.ui.listClasses.ListClassesViewModelFactory;
import ru.kfpu.itis.studteacher.utilities.InjectorUtils;

public class ListStudentsActivity extends AppCompatActivity {

    private ListStudentsAdapter adapter;
    private RecyclerView rvStudents;
    private ProgressBar progressBar;
    private Button btnSave;
    private List<Student> students;

    private ListStudentsViewModel listStudentsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);
        int idTask = getIntent().getIntExtra("ID_TASK", 0);
        int idClass = getIntent().getIntExtra("ID_CLASS", 0);
//        students = new ArrayList<>();
//        students.add(new Student(7, new User("sadasd", "sadasd", "Tolya",
//                "admin", null)));
//        students.add(new Student(7, new User("sadasd", "sadasd", "Nurik",
//                "admin", null)));
        rvStudents = findViewById(R.id.rv_list_students);
        progressBar = findViewById(R.id.pb_list_students);
        btnSave = findViewById(R.id.btn_save_students);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListStudentsAdapter(this.getApplicationContext());
//        adapter.setStudents(students);
        rvStudents.setAdapter(adapter);

        ListStudentsViewModelFactory factory = InjectorUtils.provideListStudentsViewModelFactory(this.getApplicationContext());
        listStudentsViewModel = ViewModelProviders.of(this, factory).get(ListStudentsViewModel.class);

        showLoading();
        //TODO 1 захардкожено
        listStudentsViewModel.getStudents(1)
                .observe(this, new ApiResponceObserver<List<Student>>() {
                    @Override
                    public void success(List<Student> body) {
                        adapter.setStudents(body);
                        hideLoading();
                    }

                    @Override
                    public void error(String message) {
                        Toast.makeText(ListStudentsActivity.this, message,Toast.LENGTH_SHORT).show();
                        hideLoading();
                    }
                });

        btnSave.setOnClickListener(v->{
            showLoading();
            listStudentsViewModel.addStudentForTask(idTask, adapter.getConvertToStudentForBody(adapter.getSelected()))
                    .observe(this, new ApiResponceObserver() {
                @Override
                public void success(Object body) {
                    Toast.makeText(ListStudentsActivity.this, "Добавление выполнено", Toast.LENGTH_SHORT).show();
                    hideLoading();
                    finish();
                }

                @Override
                public void error(String message) {
                    Toast.makeText(ListStudentsActivity.this, message, Toast.LENGTH_SHORT).show();
                    hideLoading();
                }
            });
        });
    }
    private void showLoading() {
        rvStudents.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        rvStudents.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
