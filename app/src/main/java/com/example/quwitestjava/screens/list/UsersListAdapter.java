package com.example.quwitestjava.screens.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quwitestjava.R;
import com.example.quwitestjava.databinding.ItemUserBinding;
import com.example.quwitestjava.model.ChannelsModel;
import com.example.quwitestjava.model.ChatModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersViewHolder> {


    private final List<ChannelsModel> channelsList = new ArrayList<>();

    public void setList(List<ChannelsModel> list) {
        channelsList.clear();
        channelsList.addAll(list);
        notifyDataSetChanged();
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
        ChannelsModel currentChannels = channelsList.get(position);
        ItemUserBinding userBinding = holder.binding;
        ChatModel currentChat = ChatModel.mapToChatModel(currentChannels.getMessageLast());


        String date = currentChat.dtaCreate;
        userBinding.timeIndicatorTextView.setText(compareDate(date));

        userBinding.userNameTextView.setText(currentChat.getUser().getName());
        userBinding.userNameLastMessage.setText(currentChat.getUser().getName());
        userBinding.lastMessageTextView.setText(currentChat.getText());

        if (currentChat.isRead == 1) {
            userBinding.readIndicatorTextView.setVisibility(View.VISIBLE);
        }

        if (currentChannels.pinToTop) {
            userBinding.pinnedImageViewButton.setVisibility(View.VISIBLE);
        }

        if (currentChat.getUser().getAvatarUrl() != null) {

            Glide.with(userBinding.photoImageView.getContext())
                    .load(currentChat.getUser().getAvatarUrl())
                    .into(userBinding.photoImageView);
        } else {
            Glide.with(userBinding.photoImageView.getContext()).clear(userBinding.photoImageView);
            userBinding.photoImageView.setImageResource(R.drawable.ic_user_avatar);
        }
    }

    private String compareDate(String date) {
        LocalDateTime datetime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        String formatToday = datetime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String formatNotToday = datetime.format(DateTimeFormatter.ofPattern("dd LLL"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        if (dateTime.toLocalDate().equals(LocalDate.now())) {
            return formatToday;
        }

        return formatNotToday;
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
