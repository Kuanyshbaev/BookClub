package com.example.dialogbeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email_register;
    private EditText password_register;
    private EditText name_register;
    private Button btn_register;
    private TextView login_txt;
    private  FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        email_register = findViewById(R.id.email_register);
        password_register = findViewById(R.id.password_register);
        name_register = findViewById(R.id.name_register);
        btn_register = findViewById(R.id.btn_register);
        login_txt = findViewById(R.id.login_txt);

        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email_register.getText().toString().isEmpty() || password_register.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        ref.child("Users").child(mAuth.getCurrentUser().getUid()).child("email").setValue(email_register.getText().toString());
                                        ref.child("Users").child(mAuth.getCurrentUser().getUid()).child("password").setValue(password_register.getText().toString());
                                        ref.child("Users").child(mAuth.getCurrentUser().getUid()).child("name").setValue(name_register.getText().toString());

                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Ошибка",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}