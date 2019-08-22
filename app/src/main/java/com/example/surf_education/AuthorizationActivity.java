package com.example.surf_education;


import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


import androidx.appcompat.app.AppCompatActivity;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

public class AuthorizationActivity extends AppCompatActivity {


    public ProgressBar progressBar;
    public Button signIn;
    public ExtendedEditText loginEdit;
    public ExtendedEditText passwordEdit;
    public TextFieldBoxes passwordBox;
    public TextFieldBoxes loginBox;
    public boolean isPasswordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        initVars();         //инициализация переменных
        initViews();        //инициализация Views
        buildPassword();    //Установка подсказки, endIcon, видимость пароля
        buildSignIn();      //Настройка кнопки
        initListeners();    //Установка слушателей
    }

    private void initVars() {
        isPasswordVisible = false;

    }

    private void initViews() {
        progressBar = findViewById(R.id.signInProgress);
        signIn = findViewById(R.id.signIn);
        loginEdit = findViewById(R.id.loginEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        passwordBox = findViewById(R.id.passwordBox);
        loginBox = findViewById(R.id.loginBox);
    }

    private void buildSignIn() {

        progressBar.setVisibility(View.INVISIBLE);

    }

    //проверка полей
    public void validateFieldsAndLogin(){
        if(loginEdit.getText().toString().length() == 10 && passwordEdit.getText().toString().length() >= 6){
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

    private void initListeners() {

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFieldsAndLogin();
            }
        });

        passwordBox.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPasswordVisible = !isPasswordVisible;
                buildPassword();
            }
        });
    }

    private void buildPassword() {

        passwordBox.setHelperText("Пароль должен содержать 6 символов");

        if(isPasswordVisible) {
            passwordBox.setEndIcon(R.drawable.closed_eye);
            passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            passwordBox.setEndIcon(R.drawable.eye);
            passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

    }


}
