package com.timmybeef.flashchatnewfirebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ChatListAdapter extends BaseAdapter {

    private Activity mActivity;
    private ArrayList<DataSnapshot> mDataSnapshots;
    private DatabaseReference mDatabaseReference;
    private String mDisplayName;

    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public ChatListAdapter(Activity activity, DatabaseReference databaseReference, String name) {
        mActivity = activity;
        mDataSnapshots = new ArrayList<>();
        mDatabaseReference = databaseReference.child("messages");
        mDisplayName = name;
    }

    private static class ViewHolder {
        TextView authorName;
        TextView body;
        LinearLayout.LayoutParams params;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public InstantMessage getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_main_chat, parent, false);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.authorName = convertView.findViewById(R.id.author);
            viewHolder.body = convertView.findViewById(R.id.message);
            viewHolder.params = (LinearLayout.LayoutParams) viewHolder.authorName.getLayoutParams();

            convertView.setTag(viewHolder);
        }

        final InstantMessage message = getItem(position);

        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.authorName.setText(message.getAuthor());
        viewHolder.body.setText(message.getMessage());


        return convertView;
    }
}
