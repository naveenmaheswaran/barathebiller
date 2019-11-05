package com.example.ebiller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        //getActionBar().hide();
        getSupportActionBar().hide();
        Handler h= new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(splashScreen.this,MainActivity.class));
                }
                else {
                    startActivity(new Intent(splashScreen.this,login.class));

                }

                finish();
            }
        },2500);
    }
}
