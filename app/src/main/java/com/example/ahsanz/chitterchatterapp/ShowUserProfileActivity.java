package com.example.ahsanz.chitterchatterapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ShowUserProfileActivity extends AppCompatActivity implements
        SendRequestDialogue.SendRequestDialogueListener{


    DatabaseReference mDBref;
    DatabaseReference mMessageRequestsDbRef;

    TextView nameTextView;
    TextView userNameTextView;
    TextView bioTextView;

    ImageView messageImage;

    String userID;
    String currentUserId;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM, YYYY hh:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_profile);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        bioTextView = (TextView) findViewById(R.id.bioTextView);

        userID = getIntent().getStringExtra("ID");

        mDBref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        mMessageRequestsDbRef = FirebaseDatabase.getInstance().getReference().
                child("MessageRequests");

        mDBref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                try {

                    Log.i("User From DB", user.getUserName());
                }catch (Exception e){e.printStackTrace();}


                nameTextView.setText(user.getName());
                userNameTextView.setText(user.getUserName());
                bioTextView.setText(user.getBio());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        messageImage = (ImageView) findViewById(R.id.message_user);

        messageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialogue();
            }
        });

    }

    @Override
    public void applyTexts(String message) {
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String strDate = sdf.format(calendar.getTime());
                Message messageReq = new Message(currentUserId, userID, message, strDate,
                        "", false);

                mMessageRequestsDbRef.push().setValue(messageReq);
    }

    private void OpenDialogue() {
        SendRequestDialogue sendRequestDialogue = new SendRequestDialogue();
        sendRequestDialogue.show(getSupportFragmentManager(), "Message Request");
    }
}
