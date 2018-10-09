package ru.kfpu.itis.studteacher.ui.home;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.data.models.Task;
import ru.kfpu.itis.studteacher.data.util.ApiResponceObserver;
import ru.kfpu.itis.studteacher.ui.authentification.AuthActivity;
import ru.kfpu.itis.studteacher.utilities.InjectorUtils;

public class HomeFragment extends Fragment implements TasksAdapter.TasksAdapterOnItemClickHandler {
    private Context context;
    private RecyclerView recyclerView;
    private TasksAdapter adapter;
    private HomeViewModel homeViewModel;
    private ProgressBar progressBar;

    public static HomeFragment getInstance(Context context) {
        HomeFragment fragment = new HomeFragment();
        fragment.setContext(context);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_tasks);
        progressBar = view.findViewById(R.id.pb_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TasksAdapter(context, this);
        HomeViewModelFactory factory = InjectorUtils.provideHomeViewModelFactory(context.getApplicationContext());
        homeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        showLoading();
        homeViewModel.getListTasks().observe(this, new ApiResponceObserver<List<Task>>() {
            @Override
            public void success(List<Task> body) {
                adapter.setmTasks(body);
                hideLoading();
            }

            @Override
            public void error(String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        });
        recyclerView.setAdapter(adapter);

    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void showLoading() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int id) {
        showLoading();
        homeViewModel.deleteTask(id).observe(this, new ApiResponceObserver() {
            @Override
            public void success(Object body) {
                Toast.makeText(context, "Удаление прошло успешно!", Toast.LENGTH_SHORT).show();
                adapter.deleteTaskById(id);
                hideLoading();
            }

            @Override
            public void error(String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        });
    }
}
