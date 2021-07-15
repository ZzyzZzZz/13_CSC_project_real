package com.zhu.a13cscproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SendData extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

    }

    @Override
    public void onBackPressed() {
        exitConfirm();
    }

    void exitConfirm(){//exit?
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You cannot go back on the order");
        builder.setMessage("Exit by home buttons");
        //stay
        builder.setPositiveButton("stay", (dialog, which) -> {
            //nothing here as well, back to food selection.
        });
        // the following don't work as I stacked all of my activities together, this is exactly what I feared. Too late now.

//        builder.setNegativeButton("exit", new DialogInterface.OnClickListener() {//leave
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                System.exit(0);
//            }
//        });
        builder.create().show();//must have for all alertDialog
    }
}