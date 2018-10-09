package ru.kfpu.itis.studteacher.ui.listClasses;

import android.arch.lifecycle.ViewModel;

import ru.kfpu.itis.studteacher.data.StudRepository;

public class ListClassesViewModel extends ViewModel {
    private final StudRepository repository;

    public ListClassesViewModel(StudRepository repository) {
        this.repository = repository;
    }

    public StudRepository getRepository() {
        return repository;
    }
}
