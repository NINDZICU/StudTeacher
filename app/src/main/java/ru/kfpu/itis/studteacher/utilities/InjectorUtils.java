package ru.kfpu.itis.studteacher.utilities;

import android.content.Context;

import ru.kfpu.itis.studteacher.data.StudRepository;
import ru.kfpu.itis.studteacher.data.network.StudApi;
import ru.kfpu.itis.studteacher.data.sharedPreferences.SharedPreferencesProvider;
import ru.kfpu.itis.studteacher.ui.authentification.AuthViewModelFactory;
import ru.kfpu.itis.studteacher.ui.home.HomeViewModelFactory;
import ru.kfpu.itis.studteacher.ui.listClasses.ListClassesViewModelFactory;
import ru.kfpu.itis.studteacher.ui.listStudents.ListStudentsViewModelFactory;
import ru.kfpu.itis.studteacher.ui.proflie.ProfileViewModelFactory;
import ru.kfpu.itis.studteacher.ui.statistics.StatisticsViewModelFactory;
import ru.kfpu.itis.studteacher.ui.uploadTask.UploadViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    public static StudRepository provideRepository(Context context) {
        StudApi studApi = StudApi.getInstance();
        SharedPreferencesProvider provider = SharedPreferencesProvider.getInstance(context);
        return StudRepository.getInstance(studApi, provider);
    }

    public static UploadViewModelFactory provideUploadViewModelFactory(Context context) {
        StudRepository repository = provideRepository(context.getApplicationContext());
        return new UploadViewModelFactory(repository);
    }

    public static ListStudentsViewModelFactory provideListStudentsViewModelFactory(Context context) {
        StudRepository repository = provideRepository(context.getApplicationContext());
        return new ListStudentsViewModelFactory(repository);
    }

    public static ListClassesViewModelFactory provideListClassesViewModelFactory(Context context) {
        StudRepository repository = provideRepository(context.getApplicationContext());
        return new ListClassesViewModelFactory(repository);
    }

    public static ProfileViewModelFactory provideProfileViewModelFactory(Context context) {
        StudRepository repository = provideRepository(context.getApplicationContext());
        return new ProfileViewModelFactory(repository);
    }

    public static HomeViewModelFactory provideHomeViewModelFactory(Context context) {
        StudRepository repository = provideRepository(context.getApplicationContext());
        return new HomeViewModelFactory(repository);
    }

    public static AuthViewModelFactory provideAuthViewModelFactory(Context context) {
        StudRepository repository = provideRepository(context.getApplicationContext());
        return new AuthViewModelFactory(repository);
    }

    public static StatisticsViewModelFactory provideStatisticsViewModelFactory(Context context){
        StudRepository repository = provideRepository(context.getApplicationContext());
        return new StatisticsViewModelFactory(repository);
    }
}