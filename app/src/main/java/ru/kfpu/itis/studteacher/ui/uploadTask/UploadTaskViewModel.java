package ru.kfpu.itis.studteacher.ui.uploadTask;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ru.kfpu.itis.studteacher.data.StudRepository;
import ru.kfpu.itis.studteacher.data.models.Question;
import ru.kfpu.itis.studteacher.data.models.Task;
import ru.kfpu.itis.studteacher.data.models.Test;

public class UploadTaskViewModel extends ViewModel {
    private final StudRepository repository;

    public UploadTaskViewModel(StudRepository repository) {
        this.repository = repository;
    }

    public LiveData uploadTask(String name, List<Test> tests, List<Question> questions) {
        return repository.uploadTask(name, tests, questions);
    }
}
