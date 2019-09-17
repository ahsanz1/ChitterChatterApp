package com.example.ahsanz.chitterchatterapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {


    CircleImageView ProfileImage;
    EditText NameEditText;
    EditText UserNameEditText;
    EditText PhoneNmberEditText;
    EditText BioEditText;
    Button DoneButton;

    DatabaseReference mCurrentUserDBref;
    FirebaseAuth mAuth;

    String UserID;
    String Name;
    String UserName;
    String PhoneNo;
    String Bio;
    String ProfilePicUri = null;

    Snackbar snackbar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        linearLayout = (LinearLayout) findViewById(R.id.edit_profile_outerLayout);

        snackbar = Snackbar.make(linearLayout, R.string.editProfile, Snackbar.LENGTH_LONG);
        snackbar.show();

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            UserID = mAuth.getCurrentUser().getUid();
        }/*else{
            Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
            startActivity(intent);
        }*/

        mCurrentUserDBref = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(UserID);

        ProfileImage = (CircleImageView) findViewById(R.id.edit_profile_image);
        NameEditText = (EditText) findViewById(R.id.edit_name_editText);
        UserNameEditText = (EditText) findViewById(R.id.edit_username_editText);
        PhoneNmberEditText = (EditText) findViewById(R.id.edit_phone_number_editText);
        BioEditText = (EditText) findViewById(R.id.edit_bio_editText);

        DoneButton = (Button) findViewById(R.id.done_Button);
        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("User ID: ", UserID);

                Name = NameEditText.getText().toString();
                UserName = "@" + UserNameEditText.getText().toString();
                PhoneNo = PhoneNmberEditText.getText().toString();
                Bio = BioEditText.getText().toString();
                ProfilePicUri = "";

                mCurrentUserDBref.child("name").setValue(Name);
                mCurrentUserDBref.child("userName").setValue(UserName);
                mCurrentUserDBref.child("phoneNo").setValue(PhoneNo);
                mCurrentUserDBref.child("bio").setValue(Bio);
                mCurrentUserDBref.child("profilePicUri").setValue(ProfilePicUri);

                Intent intent = new Intent(EditProfileActivity.this, MessengerHome.class);
                startActivity(intent);

            }
        });


    }
}
