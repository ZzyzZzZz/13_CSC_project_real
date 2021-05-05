package com.zhu.a13cscproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, email, id;
    Button signOut;
    GoogleSignInClient mGooglesignInClient;

    @Override // this part is to proceed in app after login, fetching data
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView = findViewById(R.id.imageView); //image view not created
        name = findViewById(R.id.textName); //name field not created
        email = findViewById(R.id.textID); //testID not created
        signOut = findViewById(R.id.sign_out_button); // signout button not created
        signOut.setOnclickListener(new View.OnclickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.sign_out_button: //button not created
                        signOut();
                        break;
                    // ...
                }
            }


        signOut = findViewById(R.id.sign_out_button); //sign out button not assigned
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        //three lines resetting variables
        name.setText(personName);
        email.setText(personEmail);
        id.setText(personId);

        Glide.with(this).load(String.valueOf(personPhoto)).into(imageView); //whole function of glide is right here
        }

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SecondActivity.this, "signed out successfully", Toast.LENGTH_SHORT).show();
                    }
                        // ...
                    }
                });
    }


}