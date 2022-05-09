package com.example.quwitestjava.screens.list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quwitestjava.data.response.channels.ChannelResponse;
import com.example.quwitestjava.data.response.channels.Channels;
import com.example.quwitestjava.repository.AccountsRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class UsersListViewModel extends ViewModel {

    private final AccountsRepository repository;
    private final MutableLiveData<List<Channels>> _channelResponse = new MutableLiveData<>();
    public LiveData<List<Channels>> channelResponse = _channelResponse;


    @Inject
    public UsersListViewModel(AccountsRepository repository) {
        this.repository = repository;
    }


    public void getChannels(String token) {

        repository.getChannels(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ChannelResponse>() {
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
