package com.chalmie.novelsharing.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chalmie.novelsharing.R;
import com.chalmie.novelsharing.models.Book;
import com.chalmie.novelsharing.ui.UserBookListActivity;
import com.chalmie.novelsharing.util.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by chalmie on 8/18/16.
 */
public class FirebaseBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseBookViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBook(Book book) {
        TextView bookNameTextView = (TextView) mView.findViewById(R.id.bookNameTextView);
        TextView authorTextView = (TextView) mView.findViewById(R.id.authorTextView);
        bookNameTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Book> Books = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LOCATION_BOOKS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Books.add(snapshot.getValue(Book.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, UserBookListActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("books", Parcels.wrap(Books));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
