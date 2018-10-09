package ru.kfpu.itis.studteacher.data.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.kfpu.itis.studteacher.data.models.Student;
import ru.kfpu.itis.studteacher.data.models.Task;
import ru.kfpu.itis.studteacher.data.models.User;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyAddStudentsForTask;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyAddTask;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyAuth;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyIdTask;
import ru.kfpu.itis.studteacher.data.network.pojo.IdForBody;

public interface StudTeacherRequests {

    @POST("sign_in")
    Call<ApiResult<User>> authentification(@Body BodyAuth body);

    @POST("task")
    Call<ApiResult> uploadTask(@Body BodyAddTask task);

    @HTTP(method = "DELETE", path = "task", hasBody = true)
    Call<ApiResult> deleteTask(@Body BodyIdTask task, @Header("teacher_token") String token);

    @GET("task")
    Call<ApiResult<List<Task>>> getMyTasks(@Header("teacher_token") String token);

    @POST("students")
    Call<ApiResult> addStudentsForTask(@Header("teacher_token") String token, @Body BodyAddStudentsForTask task);

    @POST("students")
    Call<ApiResult<List<Student>>> getStudents(@Header("teacher_token") String token, @Body IdForBody idClass);
}

