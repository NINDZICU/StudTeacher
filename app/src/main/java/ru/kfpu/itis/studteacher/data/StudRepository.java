package ru.kfpu.itis.studteacher.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.kfpu.itis.studteacher.data.models.Question;
import ru.kfpu.itis.studteacher.data.models.Student;
import ru.kfpu.itis.studteacher.data.models.Task;
import ru.kfpu.itis.studteacher.data.models.Test;
import ru.kfpu.itis.studteacher.data.models.User;
import ru.kfpu.itis.studteacher.data.network.ApiResult;
import ru.kfpu.itis.studteacher.data.network.StudApi;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyAddStudentsForTask;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyAddTask;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyAuth;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyIdTask;
import ru.kfpu.itis.studteacher.data.network.pojo.IdForBody;
import ru.kfpu.itis.studteacher.data.sharedPreferences.SharedPreferencesProvider;

public class StudRepository {
    private static final String LOG_TAG = StudRepository.class.getSimpleName();
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static StudRepository sInstance;
    private final StudApi mStudApi;
    private final SharedPreferencesProvider preferencesProvider;
    private boolean mInitialized = false;
    private static final int RESULT_OK = 200;


    public StudRepository(StudApi mStudApi, SharedPreferencesProvider preferencesProvider) {
        this.mStudApi = mStudApi;
        this.preferencesProvider = preferencesProvider;
    }

    public synchronized static StudRepository getInstance(StudApi studApi, SharedPreferencesProvider provider) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new StudRepository(studApi, provider);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public LiveData<ApiResult<List<Task>>> getTasksList() {
        MutableLiveData<ApiResult<List<Task>>> taskList = new MutableLiveData<>();
        System.out.println("TOKEN "+preferencesProvider.getUser().getToken());
        mStudApi.getTeacherRequests().getMyTasks(preferencesProvider.getUser().getToken()).
                enqueue(new Callback<ApiResult<List<Task>>>() {
            @Override
            public void onResponse(Call<ApiResult<List<Task>>> call, Response<ApiResult<List<Task>>> response) {
                if (response.code() == RESULT_OK) {
                    taskList.postValue(response.body());
                } else {
                    taskList.postValue(new ApiResult<>(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ApiResult<List<Task>>> call, Throwable t) {
                taskList.postValue(new ApiResult<>(400));
            }
        });
        return taskList;
    }

//    public LiveData<ApiResult<List<StudClass>>> getClassesList() {
//        MutableLiveData<ApiResult<List<StudClass>>> classesList = new MutableLiveData<>();
//        mStudApi.getTeacherRequests().get.enqueue(new Callback<ApiResult<List<StudClass>>>() {
//            @Override
//            public void onResponse(Call<ApiResult<List<StudClass>>> call, Response<ApiResult<List<StudClass>>> response) {
//                if (response.code() == RESULT_OK) {
//                    classesList.postValue(response.body());
//                } else {
//                    classesList.postValue(new ApiResult<>(response.code()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResult<List<StudClass>>> call, Throwable t) {
//
//            }
//        });
//        return classesList;
//    }
//
//    public LiveData<ApiResult<List<Student>>> getStudentList() {
//        MutableLiveData<ApiResult<List<Student>>> studentList = new MutableLiveData<>();
//        mStudApi.getTeacherRequests().get().enqueue(new Callback<ApiResult<List<Student>>>() {
//            @Override
//            public void onResponse(Call<ApiResult<List<Student>>> call, Response<ApiResult<List<Student>>> response) {
//                if (response.code() == RESULT_OK) {
//                    studentList.postValue(response.body());
//                } else {
//                    studentList.postValue(new ApiResult<>(response.code()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResult<List<Student>>> call, Throwable t) {
//
//            }
//        });
//        return studentList;
//    }

    public LiveData<ApiResult<User>> auth(BodyAuth bodyAuth) {
        MutableLiveData<ApiResult<User>> user = new MutableLiveData<>();
        mStudApi.getTeacherRequests().authentification(bodyAuth).enqueue(new Callback<ApiResult<User>>() {
            @Override
            public void onResponse(Call<ApiResult<User>> call, Response<ApiResult<User>> response) {
                if (response.code() == RESULT_OK) {
                    Log.d("AUTH", "SUCCES");
                    if (response.body().getCode() == 0)
                        preferencesProvider.saveUser(response.body().getBody());
                    user.postValue(response.body());
                } else {
                    Log.d("AUTH", String.valueOf(response.code()));
                    user.postValue(new ApiResult<>(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ApiResult<User>> call, Throwable t) {
                Log.d("AUTH", "FAILURE " + t.getMessage());
                user.postValue(new ApiResult<>(400));
            }
        });
        return user;
    }

    public LiveData<ApiResult> uploadTask(String name, List<Test> tests, List<Question> questions) {
        MutableLiveData<ApiResult> result = new MutableLiveData<>();
        mStudApi.getTeacherRequests().uploadTask(new BodyAddTask(name, preferencesProvider.getUser().getToken(),
                tests,questions)).enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if (response.code() == RESULT_OK) {
                    Log.d("UPLOAD", "SUCCES");
                    if (response.body().getCode() == 0)
                    result.postValue(new ApiResult<>(response.code()));
                } else {
                    Log.d("UPLOAD", String.valueOf(response.code()));
                    result.postValue(new ApiResult<>(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.d("UPLOAD", "FAILURE " + t.getMessage());
                result.postValue(new ApiResult<>(400));
            }
        });
        return result;
    }

    public LiveData<ApiResult> deleteTask(int id) {
        MutableLiveData<ApiResult> result = new MutableLiveData<>();
        mStudApi.getTeacherRequests().deleteTask(new BodyIdTask(id), preferencesProvider.getUser().getToken())
                .enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if (response.code() == RESULT_OK) {
                    Log.d("DELETE", "SUCCES");
                    if (response.body().getCode() == 0)
                        result.postValue(new ApiResult<>(response.code()));
                } else {
                    Log.d("DELETE", String.valueOf(response.code()));
                    result.postValue(new ApiResult<>(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.d("DELETE", "FAILURE " + t.getMessage());
                result.postValue(new ApiResult<>(400));
            }
        });
        return result;
    }
    public LiveData<ApiResult> addStudentForTask(int id, List<IdForBody> students) {
        MutableLiveData<ApiResult> result = new MutableLiveData<>();
        mStudApi.getTeacherRequests().addStudentsForTask(preferencesProvider.getUser().getToken(),
                new BodyAddStudentsForTask(id, students)).enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if (response.code() == RESULT_OK) {
                    Log.d("ADD STUDENT", "SUCCES");
                    if (response.body().getCode() == 0)
                        result.postValue(new ApiResult<>(response.code()));
                } else {
                    Log.d("ADD STUDENT", String.valueOf(response.code()));
                    result.postValue(new ApiResult<>(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.d("ADD STUDENT", "FAILURE " + t.getMessage());
                result.postValue(new ApiResult<>(400));
            }
        });
        return result;
    }
    public LiveData<ApiResult<List<Student>>> getStudent(int idClass) {
        MutableLiveData<ApiResult<List<Student>>> result = new MutableLiveData<>();
        mStudApi.getTeacherRequests().getStudents(preferencesProvider.getUser().getToken(),
                new IdForBody(idClass)).enqueue(new Callback<ApiResult<List<Student>>>() {
            @Override
            public void onResponse(Call<ApiResult<List<Student>>> call, Response<ApiResult<List<Student>>> response) {
                if (response.code() == RESULT_OK) {
                    Log.d("GET STUDENT", "SUCCES");
                    if (response.body().getCode() == 0)
                        result.postValue(response.body());
                } else {
                    Log.d("GET STUDENT", String.valueOf(response.code()));
                    result.postValue(new ApiResult<>(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ApiResult<List<Student>>> call, Throwable t) {
                Log.d("GET STUDENT", "FAILURE " + t.getMessage());
                result.postValue(new ApiResult<>(400));
            }
        });
        return result;
    }

    public User getUserFromStorage() {
        return preferencesProvider.getUser();
    }

    public void deleteUserFromStorage() {
        preferencesProvider.deleteUser();
    }

}
