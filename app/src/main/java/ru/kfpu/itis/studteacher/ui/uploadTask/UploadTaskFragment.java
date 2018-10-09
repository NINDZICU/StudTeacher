package ru.kfpu.itis.studteacher.ui.uploadTask;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.data.models.Question;
import ru.kfpu.itis.studteacher.data.models.QuestionHandler;
import ru.kfpu.itis.studteacher.data.util.ApiResponceObserver;
import ru.kfpu.itis.studteacher.ui.tabPager.NotifyFragment;
import ru.kfpu.itis.studteacher.utilities.InjectorUtils;

public class UploadTaskFragment extends Fragment {

    private Context context;
    private EditText etTitleTask;
    private Button btnAddQuestion;
    private Button btnSave;
    private RecyclerView mRecyclerView;
    private AddTasksAdapter addTasksAdapter;
    private UploadTaskViewModel uploadTaskViewModel;
    private ProgressBar progressBar;
    private ScrollView scrollViewUploadTask;
    private NotifyFragment notifyFragment;

    public static UploadTaskFragment getInstance(Context context) {
        UploadTaskFragment fragment = new UploadTaskFragment();
        fragment.setContext(context);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_upload_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etTitleTask = view.findViewById(R.id.et_title_task);
        btnAddQuestion = view.findViewById(R.id.btn_add_answer);
        btnSave = view.findViewById(R.id.btn_save);
        mRecyclerView = view.findViewById(R.id.rv_upload_tasks);
        progressBar = view.findViewById(R.id.pb_upload_task);
        scrollViewUploadTask = view.findViewById(R.id.scroll_upload_task);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        addTasksAdapter = new AddTasksAdapter(context);
        mRecyclerView.setAdapter(addTasksAdapter);

        List<QuestionHandler> questions = new ArrayList<>();
        questions.add(new QuestionHandler(false, "","","","","","",1));
        addTasksAdapter.setQuestions(questions);

        UploadViewModelFactory factory = InjectorUtils.provideUploadViewModelFactory(context);
        uploadTaskViewModel = ViewModelProviders.of(this, factory).get(UploadTaskViewModel.class);

        btnAddQuestion.setOnClickListener(v->{
            addTasksAdapter.addItemToListQuestions();
        });
        btnSave.setOnClickListener(v->{
            showLoading();
            uploadTaskViewModel.uploadTask(etTitleTask.getText().toString(), addTasksAdapter.getTests(),
                    addTasksAdapter.getmTasks()).observe(this, new ApiResponceObserver() {
                @Override
                public void success(Object body) {
                    etTitleTask.setText("");
                    Toast.makeText(context, "Добавление прошло успешно!", Toast.LENGTH_SHORT).show();
                    notifyFragment.notifyData();
                    hideLoading();
                }

                @Override
                public void error(String message) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    hideLoading();
                }
            });
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void showLoading() {
        scrollViewUploadTask.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        scrollViewUploadTask.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void setNotifyFragment(NotifyFragment notifyFragment) {
        this.notifyFragment = notifyFragment;
    }
}
