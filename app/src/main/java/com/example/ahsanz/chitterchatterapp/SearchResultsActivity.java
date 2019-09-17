package com.example.ahsanz.chitterchatterapp;

import android.app.SearchManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {


    ArrayList<User> appUsers;
    ArrayList<User> newList;
    Toolbar toolbar;
    EditText searchEditText;

    private RecyclerView mRecyclerView;
    private SearchUsersRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    DatabaseReference mUsersDatabaseReference;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        searchEditText = (EditText) findViewById(R.id.search_edit_text);

        mUsersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        mRecyclerView = (RecyclerView) findViewById(R.id.search_users_recyclerView);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        newList = new ArrayList<>();
        appUsers = new ArrayList<>();

        mUsersDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    appUsers.add((snapshot.getValue(User.class)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mAdapter = new SearchUsersRecyclerAdapter(newList, new SearchUsersRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(User item) {
                Intent intent = new Intent(SearchResultsActivity.this, ShowUserProfileActivity.class);
                intent.putExtra("ID", item.getUserId());
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String userInput = s.toString();

                newList = new ArrayList<>();

                for (User user : appUsers){
                    if (user.getUserName().contains(userInput) &&
                            !user.getUserId().equals(userID))
                        newList.add(user);
                }

                mAdapter.updateList(newList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
