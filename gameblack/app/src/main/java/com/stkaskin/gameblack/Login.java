package com.stkaskin.gameblack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;



import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent=new Intent(Login.this,Menu.class);
        startActivity(intent);
    }

// ...
// Initialize Firebase Auth

    public  void girisYap(View view)
    {
        EditText pass=findViewById(R.id.password_Login);
        EditText mail=findViewById(R.id.username_Login);
        String email=mail.getText().toString();
        String password=pass.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").whereEqualTo("mail",email).whereEqualTo("password",password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(Login.this, "Giriş Başarılı.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Login.this,Menu.class);
                                startActivity(intent);
                                break;

                                        }
                        } else {
                            Toast.makeText(Login.this, "Giriş Başarısız.",
                                    Toast.LENGTH_SHORT).show();
                                  }
                    }
                });

    }
    public  void kaydol_git(View view)
    {
        Intent intent=new Intent(Login.this,Register.class);
        startActivity(intent);


    }
}