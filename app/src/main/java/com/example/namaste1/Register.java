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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {


    EditText username, userpassward, usermail;
    TextView tv;
    Button btn;
    ImageView img;

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    //FirebaseDatabase db;
    //DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar tbr=findViewById(R.id.toolbar);
        setSupportActionBar(tbr);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = (EditText) findViewById(R.id.userName);
        userpassward = (EditText) findViewById(R.id.userPassword);
        usermail = (EditText) findViewById(R.id.userEmail);
        img = (ImageView) findViewById(R.id.imageView);
        tv = (TextView) findViewById(R.id.nextPage);
        btn = (Button) findViewById(R.id.buttonsignup);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String txt_username = username.getText().toString();
                String txt_email = usermail.getText().toString();
                String txt_password = userpassward.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(Register.this, "All field is required", Toast.LENGTH_SHORT).show();

                } else if (txt_password.length() < 6) {

                    Toast.makeText(Register.this, "passwordmust be at least 6 characters", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.createUserWithEmailAndPassword
                            (usermail.getText().toString(), userpassward.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                String userid = firebaseUser.getUid();
                                reference = FirebaseDatabase.getInstance().getReference("users").child(userid);

                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("id", userid);
                                hashMap.put("username_", txt_username);
                                hashMap.put("imageURL", "default");


                                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(Register.this, MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });


                                usermail.setText("");
                                username.setText("");
                                userpassward.setText("");
                                Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
      /*  btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = username.getText().toString();
                String txt_email = usermail.getText().toString();
                String txt_password = userpassward.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(Register.this, "All field is required", Toast.LENGTH_SHORT).show();

                } else if (txt_password.length() < 6) {

                    Toast.makeText(Register.this, "passwordmust be at least 6 characters", Toast.LENGTH_SHORT).show();
                } else {

                    Register(txt_username, txt_email, txt_password);
                }


            }
        });


    }
    private void Register(final String usrname,String usrmail , String usrpassword){
                auth.signInWithEmailAndPassword(usrmail,usrpassword)
                        //(.getText().toString(),userpassward.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid= firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("users").child(userid);

                            HashMap<String , String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",usrname);
                            hashMap.put("imageURL","default");

                            Toast.makeText(Register.this,"Account Created",Toast.LENGTH_SHORT).show();


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(Register.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }
                        else {

                            Toast.makeText(Register.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

    }

    public void gotologin(View view) {
        Intent intent=new Intent(Register.this,login.class);
        startActivity(intent);
    }
}