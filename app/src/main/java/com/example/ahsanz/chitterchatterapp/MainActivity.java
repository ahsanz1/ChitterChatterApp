package com.example.ahsanz.chitterchatterapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    FirebaseAuth mAuth;
    FirebaseDatabase mUsersDatabase;
    DatabaseReference mUsersDatabaseReference;

    ArrayList<User> UsersList;
    boolean found;
    Snackbar snackbar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.main_activity_layout);

        mAuth = FirebaseAuth.getInstance();
        mUsersDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mUsersDatabase.getReference().child("Users");

        UsersList = new ArrayList<>();
        found = false;

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mAuth.getCurrentUser() == null) {

            new CountDownTimer(4000, 1000) {
                @Override
                public void onTick(long l) {}
                @Override
                public void onFinish() {
                    SignIn();
                }
            }.start();
        }else{

            Intent intent = new Intent(MainActivity.this, MessengerHome.class);
            startActivity(intent);
            finish();
        }

        mUsersDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UsersList.add(snapshot.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                String id = user.getUid();

                FirebaseUserMetadata metadata = user.getMetadata();

                for (User user1 : UsersList){

                    if (user1.getUserId().equals(id)){
                        Toast.makeText(this, "Existing User!", Toast.LENGTH_SHORT).show();
                        found = true;
                        Intent intent = new Intent(MainActivity.this, MessengerHome.class);
                        startActivity(intent);
                        finish();
                    }
                }

                if (!found) {
                    Toast.makeText(this, "New User!", Toast.LENGTH_SHORT).show();

                    mUsersDatabaseReference.child(id).setValue(new User(id, "", "",
                            "", "", ""));

                    Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                    startActivity(intent);
                    finish();
                }

                // ...
            }else if (resultCode == RESULT_CANCELED){
                finish();
            }else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    private void SignIn() {
        // Choose authentication providers


        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());


// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .setTheme(R.style.AppTheme)
                        .build(),
                RC_SIGN_IN);

    }

    private boolean isNetworkAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected)
            return true;
        return false;
    }
}
