package ru.kfpu.itis.studteacher.data.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudApi {
    private static final String BASE_URL = "https://forschoo1.herokuapp.com/api/";
    private StudTeacherRequests teacherRequests;
    private static StudApi studApi;

    public StudApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        teacherRequests = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(StudTeacherRequests.class);
    }
    public static StudApi getInstance(){
        if(studApi==null) studApi = new StudApi();
        return studApi;
    }

    public StudTeacherRequests getTeacherRequests() {
        return teacherRequests;
    }
}
