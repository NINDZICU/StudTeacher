package ru.kfpu.itis.studteacher.data.util;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import ru.kfpu.itis.studteacher.data.network.ApiResult;

public abstract class ApiResponceObserver<T> implements Observer<ApiResult<T>> {
    @Override
    public void onChanged(@Nullable ApiResult<T> tApiResult) {
        if (tApiResult != null) {
            Log.d("CODE REQUEST", String.valueOf(tApiResult.getCode()));
            if (tApiResult.getBody() != null) {
                success(tApiResult.getBody());
            }
            else if(tApiResult.getCode()==0||tApiResult.getCode()==200){
                success(null);
            }else {
                switch (tApiResult.getCode()) {
                    case 1: {
//                        error("Не верный токен");
                        error("Не верный логин или пароль");
                        break;
                    }
                    case 2: {
                        error("Не достаточно прав");
                        break;
                    }
                    case 3: {
                        error("Информация не найдена");
                        break;
                    }
                    case 4: {
                        error("Не корректный запрос");
                        break;
                    }
                    case 5: {
                        error("Текст отсутсвует");
                        break;
                    }
                    case 10: {
                        error("Логин или пароль не верны");
                        break;
                    }
                    case 11: {
                        error("Логин или пароль некорректны");
                        break;
                    }
                    case 17: {
                        error("Неверный пароль");
                        break;
                    }
                    case 18: {
                        error("Невалидная форма");
                        break;
                    }
                }

                if (tApiResult.getCode() == 404) {
                    error("Not found");
                }
                error("");
            }
        }
    }

    public abstract void success(T body);

    public abstract void error(String message);
}
