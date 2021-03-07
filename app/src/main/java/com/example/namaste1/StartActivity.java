package com.example.namaste1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class StartActivity extends AppCompatActivity {

    TextView tv;
    Button login,register;

    FirebaseUser firebaseUser;

    protected void onStart() {
        super.onStart();
        //login automatically when you one time login by using passward
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //check if user is null
        if(firebaseUser !=null){
            Intent intent=new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar tbr=findViewById(R.id.toolbar);
        setSupportActionBar(tbr);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv=(TextView)findViewById(R.id.t1);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,login.class);

                startActivity(intent);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,Register.class);
                startActivity(intent);

            }
        });
    }

}
