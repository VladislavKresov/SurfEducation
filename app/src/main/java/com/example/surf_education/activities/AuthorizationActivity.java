package com.example.surf_education.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.surf_education.Data.User;
import com.example.surf_education.R;
import com.example.surf_education.network.AuthResponse;
import com.example.surf_education.network.AuthorizationRequest;
import com.example.surf_education.network.NetworkService;
import com.example.surf_education.network.MemesResponse;
import com.example.surf_education.network.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AuthorizationActivity extends AppCompatActivity {


    public ProgressBar progressBar;
    public Button signIn;
    public ExtendedEditText loginEdit;
    public ExtendedEditText passwordEdit;
    public TextFieldBoxes passwordBox;
    public TextFieldBoxes loginBox;
    public boolean isPasswordVisible;
    private SharedPreferences user;
    private final String APP_PREFERENCES = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        user = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        User.setInstance(user);

        initVars();         //инициализация переменных
        initViews();        //инициализация Views
        buildPassword();    //Установка подсказки, endIcon, видимость пароля
        buildSignIn();      //Настройка кнопки
        initListeners();    //Установка слушателей
    }

    private void initUser(UserInfo userInfo) {
        User.pushData("id", userInfo.getId());
        User.pushData("userName", userInfo.getUserName());
        User.pushData("userFirstName", userInfo.getFirstName());
        User.pushData("userLastName", userInfo.getLastName());
        User.pushData("userDescription", userInfo.getUserDescription());
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

            signInRequest(requestPreparation());

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

    private AuthorizationRequest requestPreparation(){
        AuthorizationRequest request = new AuthorizationRequest();
        request.setLogin(loginEdit.getText().toString());
        request.setPassword(passwordEdit.getText().toString());
        return request;
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

    private void signInRequest(AuthorizationRequest authorizationRequest){
        NetworkService.getInstance()
                .getJSONApi()
                .postAuthorizationRequest(authorizationRequest)
                .enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        AuthResponse authResponse = response.body();

                        signIn.setText(R.string.sign_in);
                        progressBar.setVisibility(View.INVISIBLE);

                        initUser(authResponse.getUserInfo());

                        Toast.makeText(AuthorizationActivity.this, User.pullData("userName"), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        Toast.makeText(AuthorizationActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                        signIn.setText(R.string.sign_in);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////

    private void getMemes(){
        NetworkService.getInstance()
                .getJSONApi()
                .getPost()
                .enqueue(new Callback<MemesResponse>() {
                    @Override
                    public void onResponse(Call<MemesResponse> call, Response<MemesResponse> response) {
                        MemesResponse memesResponse = response.body();

                        Toast.makeText(AuthorizationActivity.this, memesResponse.getMemeTitle(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<MemesResponse> call, Throwable t) {
                        Toast.makeText(AuthorizationActivity.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
