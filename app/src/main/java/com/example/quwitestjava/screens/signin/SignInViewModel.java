package com.example.quwitestjava.screens.signin;

import static com.example.quwitestjava.helper.Constants.PERSISTANT_STORAGE_NAME;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quwitestjava.App;
import com.example.quwitestjava.data.LoginResponse;
import com.example.quwitestjava.helper.Event;
import com.example.quwitestjava.repository.AccountsRepository;
import com.example.quwitestjava.screens.LoginState;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class SignInViewModel extends ViewModel {

    private final AccountsRepository repository;

    private final SharedPreferences settings = App.getAppContext().getSharedPreferences(PERSISTANT_STORAGE_NAME, Context.MODE_PRIVATE);
    private final SharedPreferences.Editor editor = settings.edit();

    private final MutableLiveData<Event<LoginState>> _isSuccess = new MutableLiveData<>();
    public LiveData<Event<LoginState>> isResponseSuccess = _isSuccess;

    @Inject
    public SignInViewModel(AccountsRepository repository) {
        this.repository = repository;
    }


    public void login(String email, String password) {

        repository.login(email, password).enqueue(
                new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> loginResponse) {
                        if (loginResponse.isSuccessful()) {
                            _isSuccess.setValue(new Event<>(LoginState.LoginSuccessfulState));
                            editor.putString("token", Objects.requireNonNull(loginResponse.body()).getToken());
                            editor.apply();
                        } else {
                            _isSuccess.setValue(new Event<>(LoginState.LoginErrorState));
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                        _isSuccess.setValue(new Event<>(LoginState.LoginErrorState));
                    }
                }
        );
    }


}
