package com.example.ahsanz.chitterchatterapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MessageRequestsDialogue extends AppCompatDialogFragment {


    Button cancelButton;
    SendRequestDialogue.SendRequestDialogueListener listener;

    RecyclerView mMessageRequestsRecyclerView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.message_requests_dialogue, null);

        cancelButton = (Button) view.findViewById(R.id.cancel_req_Button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (SendRequestDialogue.SendRequestDialogueListener) context;
    }


    public interface SendRequestDialogueListener{
        void applyTexts(String message);
    }
}
