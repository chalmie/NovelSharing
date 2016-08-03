package com.chalmie.novelsharing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LibraryActivity extends AppCompatActivity {
    private EditText mAddBookEditText;
    private Button mAddBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        mAddBookEditText = (EditText) findViewById(R.id.addBookEditText);
        mAddBookButton = (Button) findViewById(R.id.addBookButton);

        mAddBookButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String book = mAddBookEditText.getText().toString();
                Intent intent = new Intent(LibraryActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }
}
