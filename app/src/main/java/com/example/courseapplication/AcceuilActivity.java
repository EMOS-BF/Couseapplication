package com.example.courseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcceuilActivity extends AppCompatActivity {

    Button Ajout_user, Modifier_user, Supprimer_user, user_list;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        Ajout_user =(Button) findViewById(R.id.Ajout_user);
        Modifier_user = (Button) findViewById(R.id.Modifier_user);
        Supprimer_user =(Button) findViewById(R.id.Supprimer_user);
        user_list =(Button) findViewById(R.id.user_list);
        DB = new DBHelper(this);
        Ajout_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(),AddUserActivity.class);
                startActivity(intent);
            }
        });

        Modifier_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(),AddUserActivity.class);
                startActivity(intent);
            }
        });

        Supprimer_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(),AddUserActivity.class);
                startActivity(intent);
            }
        });

        user_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(),UserListActivity.class);
                startActivity(intent);
            }
        });

    }
}