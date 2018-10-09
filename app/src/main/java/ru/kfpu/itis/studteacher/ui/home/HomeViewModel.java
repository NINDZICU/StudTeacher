package ru.kfpu.itis.studteacher.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ru.kfpu.itis.studteacher.data.StudRepository;
import ru.kfpu.itis.studteacher.data.models.Task;
import ru.kfpu.itis.studteacher.data.network.ApiResult;

public class HomeViewModel extends ViewModel {
    private final StudRepository repository;
    private LiveData<ApiResult<List<Task>>> liveDataTasks;

    public HomeViewModel(StudRepository repository) {
        this.repository = repository;
    }

    public LiveData getListTasks(){
        return repository.getTasksList();
    }
    public LiveData deleteTask(int id) {
        return repository.deleteTask(id);
    }

}
