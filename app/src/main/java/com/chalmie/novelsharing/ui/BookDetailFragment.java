package com.chalmie.novelsharing.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chalmie.novelsharing.R;
import com.chalmie.novelsharing.models.Book;
import com.chalmie.novelsharing.util.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chalmie on 8/3/16.
 */
public class BookDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.bookImageView)
    ImageView mImageLabel;
    @Bind(R.id.authorTextView)
    TextView mAuthorLabel;
    @Bind(R.id.bookNameTextView) TextView mBookNameLabel;
    @Bind(R.id.categoryTextView) TextView mCategoryTextView;
    @Bind(R.id.publishedDateTextView) TextView mPublishedDateTextView;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.pageCountTextView) TextView mPageCountLabel;
    @Bind(R.id.saveBookButton)
    Button mSaveBookButton;

    public static final String TAG = BookDetailFragment.class.getSimpleName();

    @Bind(R.id.previewButton) Button mPreviewButton;

//    private SharedPreferences mSharedPreferences;
    private Book mBook;

    private ArrayList<Book> mBooks;
    private Integer mPosition;
    private String mSource;

    public static BookDetailFragment newInstance(ArrayList<Book> books, Integer position, String source) {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();

        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_BOOKS, Parcels.wrap(books));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, source);
        bookDetailFragment.setArguments(args);
        return bookDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBooks = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_BOOKS));
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mBook = mBooks.get(mPosition);
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        ButterKnife.bind(this, view);
        mSaveBookButton.setOnClickListener(this);
        mPreviewButton.setOnClickListener(this);

        final String imageUrl = mBook.getImage();

        if (TextUtils.isEmpty(imageUrl)) {
            Picasso.with(view.getContext()).load(R.drawable.noimage).resize(600, 800).into(mImageLabel);
//                           mImageLabel.setImageResource(R.drawable.noimage);
        } else {
            Picasso.with(view.getContext()).load(mBook.getImage()).resize(600, 800).into(mImageLabel);
        }

        mAuthorLabel.setText(mBook.getAuthor());
        mBookNameLabel.setText(mBook.getTitle());
        mDescriptionLabel.setText("Description:\n" + mBook.getDescription());
        mCategoryTextView.setText(mBook.getCategory());
        mPageCountLabel.setText(mBook.getPageCount()+" pgs");
        mPublishedDateTextView.setText("Published: " + mBook.getPublishedDate());

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBookButton:
              FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                Log.d("uid",uid);
                DatabaseReference bookRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_LOCATION_BOOKS)
                        .child(uid);

                DatabaseReference pushRef = bookRef.push();
                String pushId = pushRef.getKey();
                mBook.setPushId(pushId);
                pushRef.setValue(mBook);

                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                break;
            case R.id.previewButton:
                String link = mBook.getPreviewLink();
                Log.d(TAG, link);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(webIntent);
            default:
                break;
        }
    }
}
