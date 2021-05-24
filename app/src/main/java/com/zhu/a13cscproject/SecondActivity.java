package com.zhu.a13cscproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, email, id;
    Button signOut;
    Button cont_FoodChoice;

    GoogleSignInClient mGoogleSignInClient;

    @Override // this part is to proceed in app after login, fetching data
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)// these lines are from google, they directed the integration
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imageView = findViewById(R.id.imageView); //image view not created
        name = findViewById(R.id.textName); //name field not created
        email = findViewById(R.id.textemail); //test email not created
        id = findViewById(R.id.textID); //bug fix, maybe get rid of this in the future @todo
        signOut = findViewById(R.id.sign_out_button);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_out_button:
                        signOut();
                        break;
                }

            }
        });

        cont_FoodChoice();




        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            //three lines resetting variables
            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId); //@TODO this id is giving off null which is causing crashing



            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView); //whole function of glide is right here
        }

    }


    private void signOut() {

        mGoogleSignInClient.signOut() //signOut function
                .addOnCompleteListener(this, new OnCompleteListener<Void>() { //code from google, basically just signing out and close activity
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SecondActivity.this, "signed out successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                        // ...
                    });
                }

    private void cont_FoodChoice() { // method to open Food Choice
        cont_FoodChoice = findViewById(R.id.cont_FoodChoice); // continue button to FoodChoice activity
        cont_FoodChoice.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) { //if button clicked go launch the activity
               launchFoodChoice();
           }
        });

    }

    public void launchFoodChoice(){ // method to open Food Choice
        Intent intent = new Intent(this, FoodChoice.class); //create intent of FoodChoice and start it
        startActivity(intent);
    }
    }
    // todo: how to connect sellers and buyers, and how to communicate
// here is the idea, I need to upload to firebase a set of food menu once, then
//the buyers and sellers can just communicate with simple digits, for example, if I have
//a dictionary (python) in firebase, I can just refer the key as food name and value as how many,
//price and image are local data since firebase limits amount of uploads and downloads per day.
//so more like {1:1,2:1,3:0} and such, but now I need a way so I can connect two people together,
//buyer and seller, where they can have a private room of their own for data to transfer without
// getting mixed up. So matching people together is also a challenge, I am thinking of creating
// a list full of sellers and buyers can just connect as a child class under firebase to give info
// to only that seller.
//.push() under firebase with unique key might come in handy

//suggestion to Miss: next year tell students what are activities, what is override, what is
// listeners and toast messages, logcat, then they should be fine no problem. The main problem with
//this year is the information overload, it is not that important to understand everything from
//scratch, I learn more when I started to use them, create small projects or watching tutorials
//on how to make a specific thing filters out the other unrelated parts, which will make the
//simplicity of android studio stand out.
//the most complicated part this year is the fast progression, if we spend only one day on something
//specific such as how location manager works then we will have no problem, or maybe just focusing on
//keeping the progress fast but explain everything once, for people who got it let them move on
// and those who didn't can come sit around a table once a week to catch up. This way is the most
// efficient, as for people who are ahead they just ask questions.