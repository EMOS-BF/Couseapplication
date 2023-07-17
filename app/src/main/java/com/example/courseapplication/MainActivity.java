  package com.example.courseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

  public class MainActivity extends AppCompatActivity {

    EditText username, email, profession, password,repassword;
    Button button_inscription, button_connexion;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        profession = (EditText) findViewById(R.id.profession);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        button_inscription =(Button) findViewById(R.id.button_inscription);
        button_connexion = (Button) findViewById(R.id.button_connexion);
        DB = new DBHelper(this);


        button_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String prof = profession.getText().toString();
                String mdp = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("")||mail.equals("")||prof.equals("")||mdp.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity.this, "Remplisez Tout les champs", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(mdp.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user,mail,prof,mdp);
                            if (insert = true){
                                Toast.makeText(MainActivity.this, "Inscription Réussie", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),AcceuilActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Echec de l'inscription", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "l'utilisateur existe déja! Connectez vous svp", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}