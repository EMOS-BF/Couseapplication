package com.example.courseapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        RecyclerView recyclerView = findViewById(R.id.List_user);
        db = new DBHelper(this);

        ArrayList<User> user_list = new ArrayList<>();
        Cursor data = db.getListContents();

        if (data.getCount() == 0) {
            Toast.makeText(this, "La base de données est vide", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                String username = data.getString(1);
                String email = data.getString(2);
                String profession = data.getString(3);

                // Créez un nouvel objet User et ajoutez-le à la liste user_list
                User user = new User(username, email, profession);
                user_list.add(user);
            }
            UserAdapter userAdapter = new UserAdapter(this, user_list);
            recyclerView.setAdapter(userAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
