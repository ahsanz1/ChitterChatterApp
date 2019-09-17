package com.example.ahsanz.chitterchatterapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendRequestDialogue extends AppCompatDialogFragment{

    EditText MessageText;
    Button sendButton;
    Button cancelButton;
    SendRequestDialogueListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.send_request_dialogue, null);

        MessageText = (EditText) view.findViewById(R.id.send_request_message_editText);
        sendButton = (Button) view.findViewById(R.id.send_req_Button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = MessageText.getText().toString();
                listener.applyTexts(message);
                dismiss();
            }
        });
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
        listener = (SendRequestDialogueListener) context;
    }


    public interface SendRequestDialogueListener{
        void applyTexts(String message);
    }
}
