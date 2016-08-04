package com.chalmie.novelsharing.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.chalmie.novelsharing.R;
import com.chalmie.novelsharing.adapters.BookListAdapter;
import com.chalmie.novelsharing.models.Book;
import com.chalmie.novelsharing.services.GoogleBookService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchBookListActivity extends AppCompatActivity {
    @Bind(R.id.bookTextView) TextView mBookTextView;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    public ArrayList<Book> mBooks = new ArrayList<>();
    private BookListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        String searchParam = intent.getStringExtra("book");
        getBook(searchParam);
    }

        private void getBook(String searchParam) {
        final GoogleBookService googleBookService = new GoogleBookService();

        googleBookService.findBook(searchParam, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.d("RESPONSE", response+"");
                mBooks = googleBookService.processResults(response);
                SearchBookListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new BookListAdapter(getApplicationContext(), mBooks);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchBookListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
