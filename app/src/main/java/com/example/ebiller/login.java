package com.example.ebiller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    public FirebaseAuth mAuth;
    Button b;
    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


        b = findViewById(R.id.button);
        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(String.valueOf(et1.getText()),String.valueOf(et2.getText()));

            }
        });


    }

    public void login(String x, String y){
        mAuth.signInWithEmailAndPassword(x, y)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                            startActivity(new Intent(login.this,MainActivity.class));

                        } else {
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                    }


                    }
                });


    }
}
