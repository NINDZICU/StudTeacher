package ru.kfpu.itis.studteacher.ui.listStudents;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import ru.kfpu.itis.studteacher.data.StudRepository;

public class ListStudentsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final StudRepository repository;

    public ListStudentsViewModelFactory(StudRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListStudentsViewModel(repository);
    }
}
