package com.example.quwitestjava.screens.list;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quwitestjava.data.response.ChannelResponse;
import com.example.quwitestjava.data.response.Channels;
import com.example.quwitestjava.repository.AccountsRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class UsersListViewModel extends ViewModel {

    private final AccountsRepository repository;
    private final MutableLiveData<List<Channels>> _channelResponse = new MutableLiveData<>();
    public LiveData<List<Channels>> channelResponse = _channelResponse;
    private final SharedPreferences preference;


    @Inject
    public UsersListViewModel(AccountsRepository repository, SharedPreferences preference) {
        this.repository = repository;
        this.preference = preference;
    }


    public void getChannels() {
        String token = preference.getString("token", "");
        repository.getChannels(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<ChannelResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ChannelResponse channelResponse) {
                                _channelResponse.setValue(channelResponse.getChannels());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("TAG", "onError: " + e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        })
        ;
    }

}
