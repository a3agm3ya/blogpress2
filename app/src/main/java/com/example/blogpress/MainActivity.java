package com.example.blogpress;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity  {
    EditText email;
    EditText Password;
    TextView newuser;
    Button register;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance ();
        if(firebaseAuth.getCurrentUser ()!=null)
        {
           // Toast.makeText ( MainActivity.this , "Logged in.." , Toast.LENGTH_SHORT ).show ( );
            finish();
            startActivity(new Intent(getApplicationContext(), profile.class));
        }


        progressDialog=new ProgressDialog ( this );
        email=findViewById(R.id.edttid);
        Password=findViewById(R.id.edttpassword);
        newuser=findViewById(R.id.txt1);
        register=findViewById(R.id.bt1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==register)
                { register();
                }
                if (v==newuser) {
                    startActivity(new Intent(getApplicationContext(), loginpage.class));
                }
                }
        });
        newuser.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if(v==newuser)
                {startActivity(new Intent(getApplicationContext(), loginpage.class));}
            }
        } );
    }
    public void register() {
        String Email = email.getText().toString().trim();
        String password  = Password.getText().toString().trim();

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(MainActivity.this, "Enter Email ID", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(Email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()) {
                            Toast.makeText ( MainActivity.this , "Registered..." , Toast.LENGTH_SHORT ).show ( );
                            finish ( );
                            startActivity ( new Intent ( getApplicationContext ( ) , profile.class ) );

                        }
                        else{
                            //display some message here
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

}

