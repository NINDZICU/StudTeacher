package ru.kfpu.itis.studteacher.ui.authentification;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import ru.kfpu.itis.studteacher.data.StudRepository;
import ru.kfpu.itis.studteacher.data.models.User;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyAuth;

public class AuthViewModel extends ViewModel {
    private final StudRepository repository;

    public AuthViewModel(StudRepository repository) {
        this.repository = repository;
    }

    public LiveData authentification(BodyAuth bodyAuth){
        return repository.auth(bodyAuth);
    }
    public User getUserFromStorage(){
        return repository.getUserFromStorage();
    }
}
