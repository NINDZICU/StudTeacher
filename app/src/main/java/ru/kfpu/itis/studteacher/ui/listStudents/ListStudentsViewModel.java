package ru.kfpu.itis.studteacher.ui.listStudents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ru.kfpu.itis.studteacher.data.StudRepository;
import ru.kfpu.itis.studteacher.data.network.pojo.IdForBody;

public class ListStudentsViewModel extends ViewModel {
    private final StudRepository repository;

    public ListStudentsViewModel(StudRepository repository) {
        this.repository = repository;
    }

    public LiveData addStudentForTask(int id, List<IdForBody> students) {
        return repository.addStudentForTask(id, students);
    }
    public LiveData getStudents(int idClass){
        return repository.getStudent(idClass);
    }
}
