package com.example.surf_education.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.surf_education.data.SharedPrefUtil;
import com.example.surf_education.R;
import com.example.surf_education.data.UserStorage;
import com.example.surf_education.network.response.AuthResponse;
import com.example.surf_education.network.request.AuthorizationRequest;
import com.example.surf_education.network.util.ConnectionDetector;
import com.example.surf_education.network.util.NetworkService;
import com.example.surf_education.network.response.UserInfo;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AuthorizationActivity extends FragmentActivity {

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
        SharedPrefUtil.setInstance(user);

        initVars();         //инициализация переменных
        initViews();        //инициализация Views
        buildPassword();    //Установка подсказки, endIcon, видимость пароля
        buildSignIn();      //Настройка кнопки
        initListeners();    //Установка слушателей
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(!savedInstanceState.isEmpty()){
            loginEdit.setText(savedInstanceState.get("login").toString());
            passwordEdit.setText(savedInstanceState.get("password").toString());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("login", loginEdit.getText().toString());
        outState.putString("password", passwordEdit.getText().toString());
    }

    private void initUser(UserInfo userInfo) {
        UserStorage.saveUserData(UserStorage.KEY_USER_ID, userInfo.getId());
        UserStorage.saveUserData(UserStorage.KEY_USER_NAME, userInfo.getUserName());
        UserStorage.saveUserData(UserStorage.KEY_USER_FIRST_NAME, userInfo.getFirstName());
        UserStorage.saveUserData(UserStorage.KEY_USER_LAST_NAME, userInfo.getLastName());
        UserStorage.saveUserData(UserStorage.KEY_USER_DESCRIPTION, userInfo.getUserDescription());
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

    private void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void buildSignIn() {
        hideProgress();
    }

    //проверка полей
    private void validateFieldsAndLogin(){
        if(loginEdit.getText().toString().length() == 10 && passwordEdit.getText().toString().length() >= 6){
            //Изменяем кнопку, переходим на новый layout, если поля корректны
            signIn.setText("");
            showProgress();

            signInRequest(requestPreparation());

        } else {
            if (loginEdit.getText().toString().equals("")) {
                loginBox.setError(getString(R.string.empty_field), false);
            } else {
                if (loginEdit.getText().toString().length() != 10) {
                    loginBox.setError(getString(R.string.uncorrect_phone), false);
                }
            }

            if (passwordEdit.getText().toString().equals("")) {
                passwordBox.setError(getString(R.string.empty_field), false);
            } else {
                if (passwordEdit.getText().toString().length() < 6)
                    passwordBox.setError(getString(R.string.password_is), false);
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

        passwordBox.setHelperText(getText(R.string.password_hint).toString());

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
                        hideProgress();

                        initUser(authResponse.getUserInfo());
                        nextScreen();

                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        signIn.setText(R.string.sign_in);
                        hideProgress();

                        checkError();
                    }
                });
    }

    private void checkError() {

        boolean isConnected =  new ConnectionDetector(this).isConnected();

        String message;

        if(isConnected)
            message = getString(R.string.uncorrect_input);
        else
            message = getString(R.string.connection_lost);

        Snackbar mSnackbar = Snackbar.make( signIn ,message,Snackbar.LENGTH_LONG)
                .setAction("Action",null);

        View snackbarView = mSnackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.colorError));
        mSnackbar.show();

    }

    private void nextScreen(){
        startActivity(new Intent(AuthorizationActivity.this, MainScreenActivity.class ));
        AuthorizationActivity.this.finish();
    }


}
