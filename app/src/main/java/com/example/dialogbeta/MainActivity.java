package com.example.dialogbeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dialogbeta.Adapter.GenresAdapter;
import com.example.dialogbeta.model.Genres;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_logout;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private TextView textView;
    private String name;


    RecyclerView genresRecycler;
    GenresAdapter genresAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_logout = findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.textView);

        ref = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("name").getValue().toString();
                textView.setText("Добрый день " + name + "!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        List<Genres> genresList = new ArrayList<>();
        genresList.add(new Genres(1,"Классика"));
        genresList.add(new Genres(2,"Романы"));
        genresList.add(new Genres(3,"Фентези"));
        genresList.add(new Genres(4,"Психология"));
        genresList.add(new Genres(4,"Мистика"));

        setGenreRecycler(genresList);
    }

    private void setGenreRecycler(List<Genres> genresList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        genresRecycler = findViewById(R.id.genresRecycler);
        genresRecycler.setLayoutManager(layoutManager);

        genresAdapter = new GenresAdapter(this, genresList);
        genresRecycler.setAdapter(genresAdapter);


    }
}