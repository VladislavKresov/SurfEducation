package com.example.surf_education;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class authorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization_layout);
        initViews();        //инициализация Views
    }

    private void initViews() {
        buildPassword();    //Установка подсказки, листенер на показ пароля (не функционален)
        buildSignIn();      //Установка слушателя на кнопку, проверка корректности ввода

    }

    private void buildSignIn() {
        final ProgressBar progressBar = findViewById(R.id.signInProgress);
        final Button signIn = findViewById(R.id.signIn);
        final ExtendedEditText loginEdit = findViewById(R.id.loginEdit);
        final ExtendedEditText passwordEdit = findViewById(R.id.passwordEdit);
        final TextFieldBoxes passwordBox = findViewById(R.id.passwordBox);
        final TextFieldBoxes loginBox = findViewById(R.id.loginBox);

        progressBar.setVisibility(View.INVISIBLE);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginEdit.getText().toString().length() == 10 && passwordEdit.getText().toString().length() > 6){
                    //Изменяем кнопку, переходим на новый layout, если поля корректны
                    signIn.setText("");
                    progressBar.setVisibility(View.VISIBLE);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            signIn.setText("Войти");
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }, 2000);
                } else {
                    if (loginEdit.getText().toString().equals("")) {
                        loginBox.setError("Поле не может быть пустым", false);
                    } else {
                        if (loginEdit.getText().toString().length() != 10) {
                            loginBox.setError("Номер телефона содержит 10 цифр", false);
                        }
                    }

                    if (passwordEdit.getText().toString().equals("")) {
                        passwordBox.setError("Поле не может быть пустым", false);
                    } else {
                        if (passwordEdit.getText().toString().length() < 6)
                            passwordBox.setError("Пароль должен состоять из не менее 6 символов", false);
                    }
                }

            }
        });

    }

    private void buildPassword() {
        TextFieldBoxes passwordBox = findViewById(R.id.passwordBox);

        passwordBox.setHelperText("Пароль должен содержать 6 символов");
        passwordBox.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(authorizationActivity.this, "Уупс, а в TextFieldBoxes пока еще нет методов для реализации данной функции((", Toast.LENGTH_LONG).show();
            }
        });
//        passwordBox.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
//            @Override
//            public void onTextChanged(String theNewText, boolean isError) {
//
//            }
//        });
    }

}
