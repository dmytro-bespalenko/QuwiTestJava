package com.example.quwitestjava.screens.signin;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quwitestjava.data.LoginResponse;
import com.example.quwitestjava.helper.Event;
import com.example.quwitestjava.repository.AccountsRepository;
import com.example.quwitestjava.screens.LoginState;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class SignInViewModel extends ViewModel {

    private final AccountsRepository repository;
    private final SharedPreferences preference;


    private final MutableLiveData<Event<LoginState>> _isSuccess = new MutableLiveData<>();
    public LiveData<Event<LoginState>> isResponseSuccess = _isSuccess;

    @Inject
    public SignInViewModel(AccountsRepository repository, SharedPreferences preference) {
        this.repository = repository;
        this.preference = preference;
    }


    public void login(String email, String password) {

        repository.login(email, password)
                .map(LoginResponse::getToken)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onNext(String token) {
                                   preference.edit().putString("token", Objects.requireNonNull(token)).apply();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   _isSuccess.postValue(new Event<>(LoginState.LoginErrorState));
                               }

                               @Override
                               public void onComplete() {
                                   _isSuccess.postValue(new Event<>(LoginState.LoginSuccessfulState));

                               }
                           }
                );
    }


}
