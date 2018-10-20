package com.example.mert.firebaseinstagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private ArrayList<String> userEMailFromFB;
    private ArrayList<String> userCommentFromFB;
    private ArrayList<String> userImageFromFB;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private PostAdapter adapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        userEMailFromFB = new ArrayList<>();
        userCommentFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        adapter = new PostAdapter(this, userEMailFromFB, userCommentFromFB, userImageFromFB);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        getDataFromFirebase();
    }

    protected void getDataFromFirebase() {

        DatabaseReference newReference = firebaseDatabase.getReference("Posts");

        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(FeedActivity.this, "START", Toast.LENGTH_LONG).show();

                /*
                System.out.println("children :" + dataSnapshot.getChildren());
                System.out.println("key :" + dataSnapshot.getKey());
                System.out.println("value :" + dataSnapshot.getValue());
                */

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();

                    userEMailFromFB.add(hashMap.get("usermail"));
                    userCommentFromFB.add(hashMap.get("comment"));
                    userImageFromFB.add(hashMap.get("downloadurl"));

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FeedActivity.this, "ERROR :" + databaseError.getMessage().toString(), Toast.LENGTH_LONG).show();
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_post) {

            Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }
}
