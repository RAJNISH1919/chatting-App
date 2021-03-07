package com.example.namaste1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {


    EditText  loginpassward, loginmail;
    TextView tv;
    Button btn;
    ImageView img;

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar tbr=findViewById(R.id.toolbar);
        setSupportActionBar(tbr);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginmail=(EditText)findViewById(R.id.loginEmail);
        loginpassward=(EditText)findViewById(R.id.loginPassword);
        tv=(TextView)findViewById(R.id.previousPage);
        img=(ImageView)findViewById(R.id.imageView1);
        btn=(Button)findViewById(R.id.buttonlogin);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email= loginmail.getText().toString();
                String txt_password = loginpassward.getText().toString();

                if ( TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(login.this, "All field is required", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.signInWithEmailAndPassword(txt_email,txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(login.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {

                                        Toast.makeText(login.this, "Authantication faield", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    public void gotoregister(View view) {
        Intent intent = new Intent(login.this,Register.class);
        startActivity(intent);
    }
}