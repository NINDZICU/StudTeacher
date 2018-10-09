package ru.kfpu.itis.studteacher.ui.authentification;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.kfpu.itis.studteacher.R;
import ru.kfpu.itis.studteacher.data.models.User;
import ru.kfpu.itis.studteacher.data.network.pojo.BodyAuth;
import ru.kfpu.itis.studteacher.data.util.ApiResponceObserver;
import ru.kfpu.itis.studteacher.ui.tabPager.MainActivity;
import ru.kfpu.itis.studteacher.utilities.InjectorUtils;

public class AuthActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private Button btnAuth;
    private ProgressBar progressBar;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_text_password);
        btnAuth = findViewById(R.id.btn_auth);
        progressBar = findViewById(R.id.pb_auth);


        AuthViewModelFactory factory = InjectorUtils.provideAuthViewModelFactory(this.getApplicationContext());
        authViewModel = ViewModelProviders.of(this, factory).get(AuthViewModel.class);

        if(authViewModel.getUserFromStorage()!=null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            btnAuth.setOnClickListener(v -> {
                if (etLogin.length() != 0 && etPassword.length() != 0) {
                    showLoading();
                    authViewModel.authentification(new BodyAuth(etLogin.getText().toString(), etPassword.getText().toString()))
                            .observe(this, new ApiResponceObserver<User>() {
                                @Override
                                public void success(User body) {
                                    //TODO Проверку что это учитель
                                    System.out.println("ROLE "+body.getRole());
                                    Log.d("AUTH ACTIVITY", "SUCCESS");
                                    hideLoading();
                                    if(body.getRole().equals("TEACHER")) {
                                        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else Toast.makeText(AuthActivity.this,
                                            "У вас нет прав для пользования этим приложением", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void error(String message) {
                                    Toast.makeText(AuthActivity.this, message, Toast.LENGTH_SHORT).show();
                                    hideLoading();
                                }
                            });
                } else {
                    Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(){
        btnAuth.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
    private void hideLoading(){
        btnAuth.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
