package ru.kfpu.itis.studteacher.ui.authentification;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import ru.kfpu.itis.studteacher.data.StudRepository;

public class AuthViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final StudRepository repository;

    public AuthViewModelFactory(StudRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AuthViewModel(repository);
    }
}
