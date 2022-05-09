package com.example.quwitestjava.screens.list;

import static com.example.quwitestjava.helper.Constants.PERSISTANT_STORAGE_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quwitestjava.data.response.channels.Channels;
import com.example.quwitestjava.databinding.FragmentUsersListBinding;

import java.util.ArrayList;
import java.util.List;


public class UsersListFragment extends Fragment{


    private UsersListViewModel viewModel;
    protected RecyclerView recyclerView;
    private final List<Channels> channelsList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentUsersListBinding binding = FragmentUsersListBinding.inflate(getLayoutInflater(), container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(UsersListViewModel.class);
        recyclerView = binding.recyclerView;

        SharedPreferences settings = requireContext().getSharedPreferences(PERSISTANT_STORAGE_NAME, Context.MODE_PRIVATE);
        settings.edit().apply();

        getChannels(settings.getString("token", ""));

        viewModel.channelResponse.observe(getViewLifecycleOwner(), channels -> {
            channelsList.clear();
            channelsList.addAll(channels);
            initRecyclerView();
        });


        return binding.getRoot();

    }


    @SuppressLint("NotifyDataSetChanged")
    public void initRecyclerView() {
        UsersListAdapter adapter = new UsersListAdapter(channelsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.notifyDataSetChanged();
    }

    private void getChannels(String token) {
        viewModel.getChannels(token);
    }

}