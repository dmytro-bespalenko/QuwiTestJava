package com.example.quwitestjava.screens.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quwitestjava.R;
import com.example.quwitestjava.data.response.channels.Channels;
import com.example.quwitestjava.data.response.channels.MessageLast;
import com.example.quwitestjava.databinding.ItemUserBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersViewHolder> {


    private final List<Channels> channelsList;


    public UsersListAdapter(List<Channels> channelsList) {
        this.channelsList = channelsList;
    }

    @NonNull
    @Override
    public UsersListAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersViewHolder(ItemUserBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListAdapter.UsersViewHolder holder, int position) {
        Channels currentChannels = channelsList.get(position);
        ItemUserBinding userBinding = holder.binding;
        MessageLast messageLast = currentChannels.getMessage_last();


        if (messageLast != null) {
            String date = messageLast.dta_create;

            LocalDateTime datetime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            String formatToday = datetime.format(DateTimeFormatter.ofPattern("HH:mm"));
            String formatNotToday = datetime.format(DateTimeFormatter.ofPattern("dd-MM"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            if (dateTime.toLocalDate().equals(LocalDate.now())) {
                userBinding.timeIndicatorTextView.setText(formatToday);
            } else {
                userBinding.timeIndicatorTextView.setText(formatNotToday);

            }
            userBinding.userNameTextView.setText(messageLast.getUser().getName());
            userBinding.userNameLastMessage.setText(messageLast.user.name);
            userBinding.lastMessageTextView.setText(messageLast.text);

            if (messageLast.is_read == 1) {
                userBinding.readIndicatorTextView.setVisibility(View.VISIBLE);
            }

            if (currentChannels.pin_to_top){
                userBinding.pinnedImageViewButton.setVisibility(View.VISIBLE);
            }

            if (messageLast.getUser().getAvatar_url() != null) {

                Glide.with(userBinding.photoImageView.getContext())
                        .load(messageLast.getUser().getAvatar_url())
                        .into(userBinding.photoImageView);
            } else {
                Glide.with(userBinding.photoImageView.getContext()).clear(userBinding.photoImageView);
                userBinding.photoImageView.setImageResource(R.drawable.ic_user_avatar);
            }
        }
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        public ItemUserBinding binding;

        public UsersViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }


}
