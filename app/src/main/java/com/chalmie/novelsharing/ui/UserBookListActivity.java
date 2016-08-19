package com.chalmie.novelsharing.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chalmie.novelsharing.R;
import com.chalmie.novelsharing.adapters.FirebaseBookViewHolder;
import com.chalmie.novelsharing.models.Book;
import com.chalmie.novelsharing.util.Constants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserBookListActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addBookEditText) TextView mAddBookEditText;
    @Bind(R.id.addBookButton) Button mAddBookButton;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private DatabaseReference mSearchedBookReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mSearchedBookReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_LOCATION_BOOKS)
                .child(uid);

        setUpFirebaseAdapter();

        mAddBookButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mAddBookButton) {
            String book = mAddBookEditText.getText().toString();
            Intent intent = new Intent(UserBookListActivity.this, SearchBookListActivity.class);
            intent.putExtra("book", book);
            startActivity(intent);
        }
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Book, FirebaseBookViewHolder>
                (Book.class, R.layout.book_list_item, FirebaseBookViewHolder.class,
                        mSearchedBookReference) {

            @Override
            protected void populateViewHolder(FirebaseBookViewHolder viewHolder,
                                              Book model, int position) {
                viewHolder.bindBook(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}



