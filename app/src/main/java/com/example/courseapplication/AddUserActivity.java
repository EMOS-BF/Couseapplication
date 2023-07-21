package com.example.courseapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;
import android.os.Bundle;

public class AddUserActivity extends AppCompatActivity {
    EditText username, email, profession, password,repassword;
    Button button_inscription;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        profession = (EditText) findViewById(R.id.profession);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        button_inscription =(Button) findViewById(R.id.button_inscription);
        DB = new DBHelper(getApplicationContext());

        button_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String prof = profession.getText().toString();
                String mdp = password.getText().toString();
                String repass = repassword.getText().toString();
                DB = new DBHelper(getApplicationContext());

                if (user.equals("")||mail.equals("")||prof.equals("")||mdp.equals("")||repass.equals("")){
                    Toast.makeText(AddUserActivity.this, "Remplisez Tout les champs", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(mdp.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertuserData(user,mail,prof,mdp);
                            if (insert = true){
                                Toast.makeText(AddUserActivity.this, "Inscription Réussie", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),AcceuilActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(AddUserActivity.this, "Echec de l'inscription", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(AddUserActivity.this, "l'utilisateur existe déja! Connectez vous svp", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(AddUserActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}