package ru.kfpu.itis.studteacher.ui.statistics;

import android.arch.lifecycle.ViewModel;

import ru.kfpu.itis.studteacher.data.StudRepository;

public class StatisticsViewModel extends ViewModel {
    private final StudRepository repository;

    public StatisticsViewModel(StudRepository repository) {
        this.repository = repository;
    }
}
