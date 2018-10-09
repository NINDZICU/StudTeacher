package ru.kfpu.itis.studteacher.ui.proflie;

import android.arch.lifecycle.ViewModel;

import ru.kfpu.itis.studteacher.data.StudRepository;
import ru.kfpu.itis.studteacher.data.models.User;

public class ProfileViewModel extends ViewModel {
    private final StudRepository repository;

    public ProfileViewModel(StudRepository repository) {
        this.repository = repository;
    }

    public void deleteUserFromStorage(){
        repository.deleteUserFromStorage();
    }
    public User getUserFromStorage(){
        return repository.getUserFromStorage();
    }
}
