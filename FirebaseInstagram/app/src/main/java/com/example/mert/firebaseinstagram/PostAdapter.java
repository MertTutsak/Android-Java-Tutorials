package com.example.mert.firebaseinstagram;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<String> {

    private final ArrayList<String> userEMail;
    private final ArrayList<String> userComment;
    private final ArrayList<String> userImage;

    private final Activity activity;

    public PostAdapter(@NonNull Activity activity, ArrayList<String> userEMail, ArrayList<String> userComment, ArrayList<String> userImage) {
        super(activity, R.layout.listview_item,userEMail);

        this.userEMail = userEMail;
        this.userComment = userComment;
        this.userImage = userImage;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.listview_item, null, true);

        TextView textViewUserName = view.findViewById(R.id.textViewUserName);
        TextView textViewComment = view.findViewById(R.id.textVieCommentText);

        ImageView imageView = view.findViewById(R.id.imageViewFeed);

        textViewUserName.setText(userEMail.get(position));
        textViewComment.setText(userComment.get(position));

        Picasso.with(activity.getApplicationContext()).load(userImage.get(position)).into(imageView);

        return view;
    }

}
