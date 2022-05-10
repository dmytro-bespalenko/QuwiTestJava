package com.example.quwitestjava.screens.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quwitestjava.databinding.FragmentUsersListBinding;
import com.example.quwitestjava.model.ChannelsModel;


public class UsersListFragment extends Fragment {


    private UsersListViewModel viewModel;
    protected RecyclerView recyclerView;
    private UsersListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentUsersListBinding binding = FragmentUsersListBinding.inflate(getLayoutInflater(), container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(UsersListViewModel.class);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        getChannels();

        initRecyclerView();

        viewModel.channelResponse.observe(getViewLifecycleOwner(), channels -> {
            adapter.setList(ChannelsModel.mapToLastChat(channels));
        });

        return binding.getRoot();

    }

    public void initRecyclerView() {
        adapter = new UsersListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void getChannels() {
        viewModel.getChannels();
    }

}