package com.example.ahsanz.chitterchatterapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class SearchUsersRecyclerAdapter extends
        RecyclerView.Adapter<SearchUsersRecyclerAdapter.UserHolder> {

    ArrayList<User> UsersList;
    OnItemClickListener clickListener;

    public SearchUsersRecyclerAdapter(ArrayList<User> list, OnItemClickListener listener){
        UsersList = list;
        clickListener = listener;
    }

    public interface OnItemClickListener{
        void OnItemClick(User item);
    }

    public static class UserHolder extends RecyclerView.ViewHolder{

        CircularImageView userImageView;
        TextView NameTextView;
        TextView userNameTextView;


        public UserHolder(View itemView) {
            super(itemView);

            userImageView = (CircularImageView) itemView.findViewById(R.id.search_user_circluar_image);
            NameTextView = (TextView) itemView.findViewById(R.id.search_user_Name);
            userNameTextView = (TextView) itemView.findViewById(R.id.search_user_username);
        }

        public void bindUser(final User user, final OnItemClickListener listener){
            NameTextView.setText(user.getName());
            userNameTextView.setText(user.getUserName());
            Glide.with(itemView.getContext()).load(user.getProfilePicUri()).into(userImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(user);
                }
            });
        }
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.search_contacts_recycler_row, parent, false);

        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = UsersList.get(position);
        holder.bindUser(user, clickListener);
    }

    @Override
    public int getItemCount() {
        return UsersList.size();
    }

    public void updateList(ArrayList<User> list){
        UsersList = new ArrayList<>();
        UsersList.addAll(list);
        notifyDataSetChanged();
    }
}
