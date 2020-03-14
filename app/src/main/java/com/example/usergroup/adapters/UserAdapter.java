package com.example.usergroup.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usergroup.R;
import com.example.usergroup.room.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    List<UserEntity> users = new ArrayList<>();

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        UserEntity currentUser = users.get(position);
        holder.nameUser.setText(currentUser.getNameUser());
        holder.surnameUser.setText(currentUser.getSurnameUser());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public UserEntity getUserat(int position) {
        return users.get(position);
    }

    public void setUsers(List<UserEntity> users) {

        this.users = users;
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView nameUser;
        private TextView surnameUser;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            nameUser = itemView.findViewById(R.id.nameUser);
            surnameUser = itemView.findViewById(R.id.surnameUser);
        }
    }
}
