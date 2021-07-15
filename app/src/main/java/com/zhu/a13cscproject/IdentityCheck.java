package com.zhu.a13cscproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.Objects;

import static android.view.View.GONE;

public class IdentityCheck extends AppCompatActivity {

    ImageView imageView;//declaring variables for different functions.
    TextView name, email;
    Button signOut, cont_FoodChoice, dev_mode_f_btn;
    GlobalClass gc;
    Integer dev_mode;

    GoogleSignInClient mGoogleSignInClient;

    @Override // this part is to proceed in app after login, fetching data
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identitycheck);

        GlobalClass globalClass = (GlobalClass)getApplicationContext();//my developer mode
        this.gc = globalClass;
        gc.setDev_mode(0);
        dev_mode = gc.getDev_mode();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)// these lines are from google, they directed the integration
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imageView = findViewById(R.id.google_pfp); //image view not created
        name = findViewById(R.id.textName); //name field not created
        email = findViewById(R.id.textEmail); //test email not created
        signOut = findViewById(R.id.sign_out_button);
        signOut.setOnClickListener(v -> {//if signOut clicked
            if (v.getId() == R.id.sign_out_button) {
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(IdentityCheck.this, task -> {
                            Toast.makeText(IdentityCheck.this, "signed out successfully", Toast.LENGTH_LONG).show();//notify user signOut successful
                            finish();
                        });
            }
        });


        cont_FoodChoice();//to food choice

        dev_mode_f_btn = findViewById(R.id.dev_mode_activate_f_btn);//dev_mode floating button
        dev_mode_f_btn.setVisibility(GONE);// just to get rid of it when distributing the app.
        dev_mode_f_btn.setOnClickListener(v -> {
            if (dev_mode == 0){//activate dev_mode
                gc.setDev_mode(1);
                Toast.makeText(IdentityCheck.this, "dev mode activated", Toast.LENGTH_SHORT).show();
            }
            if (dev_mode == 1){//deactivate dev_mode
                gc.setDev_mode(0);
                Toast.makeText(IdentityCheck.this, "dev mode deactivated", Toast.LENGTH_SHORT).show();
            }
        });




        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);//last signed in account if so login with it.
        if (account != null) {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            if (Objects.equals(personName, personEmail)){//this will detect and stop username and email being displayed together, since school name and email are the same.
                personName = null;
            }
            Uri personPhoto = account.getPhotoUrl();
            //three lines resetting variables
            name.setText(personName);
            email.setText(personEmail);



            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView); //whole function of glide is right here
        }

    }


    private void cont_FoodChoice() { // method to open Food Choice
        cont_FoodChoice = findViewById(R.id.cont_FoodChoice); // continue button to FoodChoice_v2 activity
        cont_FoodChoice.setOnClickListener(v -> { //if button clicked go launch the activity
            Intent intent = new Intent(IdentityCheck.this, FoodChoice_v2.class); //create intent of FoodChoice_v2 and start it
            startActivity(intent);
        });
    }
}
