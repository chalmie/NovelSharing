package com.chalmie.novelsharing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LibraryActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addBookEditText) TextView mAddBookEditText;
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.addBookButton) Button mAddBookButton;
    private String[] books = new String[] {"Infinite Jest", "The Golden Compass",
            "Of Mice and Men", "The Sun Also Rises", "Oliver Twist", "A Tale of Two Cities",
            "Gravity's Rainbow", "Have Space Suit Will Travel", "The Client", "The Left Hand of Darkness",
            "I, Robot", "Do Androids Dream of Electric Sheep", "1984",
            "Brave New World", "We"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String book = ((TextView)view).getText().toString();
                Toast.makeText(LibraryActivity.this, book, Toast.LENGTH_LONG).show();
            }
        });

        mAddBookButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String book = mAddBookEditText.getText().toString();
        Intent intent = new Intent(LibraryActivity.this, AddBookActivity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }
}
