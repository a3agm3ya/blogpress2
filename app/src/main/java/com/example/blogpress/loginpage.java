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

public class loginpage extends AppCompatActivity {
    TextView txtnew;
    EditText edttemail;
    EditText edttpassword;
    Button btlogin;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        firebaseAuth=FirebaseAuth.getInstance ();
        if(firebaseAuth.getCurrentUser ()!=null)
        {
            Toast.makeText ( loginpage.this , "Logged in.." , Toast.LENGTH_SHORT ).show ( );
            finish();
            startActivity(new Intent ( this,profile.class ));
        }

        progressDialog=new ProgressDialog ( this );

        txtnew=findViewById ( R.id.txt1 );
        edttemail=findViewById ( R.id.edttid );
        edttpassword=findViewById ( R.id.edttpassword );
        btlogin=findViewById ( R.id.bt1 );
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btlogin) {
                    login ( );
                }
                if (v == txtnew) {
                    startActivity ( new Intent ( getApplicationContext ( ) , MainActivity.class ) );
                }
            }
            });
        txtnew.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if (v == txtnew) {
                    startActivity ( new Intent ( getApplicationContext ( ) , MainActivity.class ) );
                }
            }
        } );

    }
    public void login(){

        String Email = edttemail.getText().toString().trim();
        String password  = edttpassword.getText().toString().trim();

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(loginpage.this, "Enter Email ID", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(loginpage.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging You in Please Wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword ( Email,password )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss ();
                //checking if success
                if(task.isSuccessful()){
                    finish();
                    Toast.makeText ( loginpage.this , "Logged in.." , Toast.LENGTH_SHORT ).show ( );
                    startActivity ( new Intent ( getApplicationContext ( ) , profile.class ) );
                    startActivity ( new Intent ( loginpage.this,profile.class ) );
                }
                else{
                    //display some message here
                    Toast.makeText(loginpage.this,"Logging Error",Toast.LENGTH_LONG).show();
                    return;
                }
                progressDialog.dismiss();
            }
        });
    }
}
