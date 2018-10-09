package ru.kfpu.itis.studteacher.ui.listClasses;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import ru.kfpu.itis.studteacher.data.StudRepository;
import ru.kfpu.itis.studteacher.ui.home.HomeViewModel;

public class ListClassesViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final StudRepository repository;

    public ListClassesViewModelFactory(StudRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListClassesViewModel(repository);
    }
}
