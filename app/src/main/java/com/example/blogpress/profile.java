package com.example.blogpress;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {
   private FirebaseAuth firebaseAuth;
    Button blogout;
    TextView txtt01;
    EditText Name,hobbies,aboutu;
    Button next;
    private DatabaseReference databaseReference;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
       setContentView ( R.layout.activity_profile );

       firebaseAuth=FirebaseAuth.getInstance ();

       if(firebaseAuth.getCurrentUser()==null)
       {
           finish ();
           startActivity ( new Intent ( getApplicationContext ( ) ,loginpage.class ) );

       }
       FirebaseUser user=firebaseAuth.getCurrentUser ();
        databaseReference= FirebaseDatabase.getInstance ().getReference("userinf");
        txtt01=findViewById ( R.id.txt01);
        Name=findViewById ( R.id.name );
        hobbies=findViewById (R.id.hob);
        aboutu=findViewById ( R.id.abtu );
        next=findViewById ( R.id.bt02 );


        txtt01.setText ( "Welcome "+user.getEmail ());


        blogout=findViewById ( R.id.bt01);


        blogout.setOnClickListener ( new View.OnClickListener ( ) {
           @Override
           public void onClick(View v) {
               finish ();
               Toast.makeText ( profile.this , "Logged out." , Toast.LENGTH_SHORT ).show ( );

               if(v==blogout){
                   firebaseAuth.signOut ();
                   finish ();
                   startActivity ( new Intent ( getApplicationContext ( ) ,loginpage.class ) );
               }
           }
       } );


       next.setOnClickListener ( new View.OnClickListener ( ) {
           @Override
           public void onClick(View v) {
            if(v==next){
                SaveUserInfo();
            }
           }
       } );



    }
    private void SaveUserInfo() {
        String name = Name.getText ( ).toString ( ).trim ( );
        String hobb = hobbies.getText ( ).toString ( ).trim ( );
        String abutu = aboutu.getText ( ).toString ( ).trim ( );
        userinf userinf = new userinf ( name , hobb , abutu );
        FirebaseUser User = firebaseAuth.getCurrentUser ( );
        databaseReference.child ( User.getUid ( ) ).setValue ( userinf );
        Toast.makeText ( this , "Profile Updated." , Toast.LENGTH_SHORT ).show ( );
    }
}
