package com.chalmie.novelsharing.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chalmie.novelsharing.R;
import com.chalmie.novelsharing.adapters.BookPagerAdapter;
import com.chalmie.novelsharing.models.Book;
import com.chalmie.novelsharing.util.Constants;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchBookDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private BookPagerAdapter adapterViewPager;
    ArrayList<Book> mBooks = new ArrayList<>();
    private String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mSource = intent.getStringExtra(Constants.KEY_SOURCE);
        mBooks = Parcels.unwrap(intent.getParcelableExtra(Constants.EXTRA_KEY_BOOKS));
        Integer startingPosition = intent.getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        adapterViewPager = new BookPagerAdapter(getSupportFragmentManager(), mBooks, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
