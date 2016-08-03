package com.chalmie.novelsharing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddBookActivity extends AppCompatActivity {
    @Bind(R.id.bookTextView) TextView mBookTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String book = intent.getStringExtra("book");
        mBookTextView.setText("Results for " + book);
    }
}
