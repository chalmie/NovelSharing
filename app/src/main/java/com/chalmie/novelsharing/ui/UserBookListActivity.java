package com.chalmie.novelsharing.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chalmie.novelsharing.R;
import com.chalmie.novelsharing.models.Book;
import com.chalmie.novelsharing.util.Constants;
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
    public ArrayList<Book> mBooks = new ArrayList<>();
    private DatabaseReference mSearchedLocationReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);

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


}
