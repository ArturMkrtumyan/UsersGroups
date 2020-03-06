package com.example.usergroup.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usergroup.R;
import com.example.usergroup.room.GroupEntity;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {
    private OnItemClickLongListener listener;
    private OnItemClickListener onItemClickListener;
    private List<GroupEntity> groups = new ArrayList<>();

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new GroupHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        GroupEntity currentGroup = groups.get(position);
        holder.name.setText(currentGroup.getName());
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
        notifyDataSetChanged();
    }

    public GroupEntity getGroupat(int position) {
        return groups.get(position);
    }

    class GroupHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipientName);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemLongClick(groups.get(getAdapterPosition()));
                    }
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null&& getAdapterPosition()!=RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(groups.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickLongListener {
        void onItemLongClick(GroupEntity group);
    }
    public interface OnItemClickListener{
        void onItemClick(GroupEntity group);
    }

    public void setOnItemClickLongListener(OnItemClickLongListener listener) {
        this.listener = listener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
