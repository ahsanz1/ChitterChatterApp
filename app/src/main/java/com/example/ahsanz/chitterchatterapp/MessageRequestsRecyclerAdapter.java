package com.example.ahsanz.chitterchatterapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageRequestsRecyclerAdapter extends
        RecyclerView.Adapter<MessageRequestsRecyclerAdapter.RequestHolder> {


    public static class RequestHolder extends RecyclerView.ViewHolder{

        CircleImageView userImageView;
        TextView NameTextView;
        TextView MessageTextView;

        public RequestHolder(View itemView){
            super(itemView);
            userImageView = (CircleImageView) itemView.findViewById(R.id.message_requester_image);
            NameTextView = (TextView) itemView.findViewById(R.id.msg_requester_user_Name);
            MessageTextView = (TextView) itemView.findViewById(R.id.message_req_message);
        }

        public void bindMessageRequest(){

        }


    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_contacts_recycler_row, parent, false);


        return new RequestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}