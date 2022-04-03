package com.stkaskin.gameblack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.*;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
//    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void kayitOl_Click(View view)
    {
      //  mAuth=FirebaseAuth.getInstance();
        EditText pass=findViewById(R.id.mail_Register_TextEdit);
        EditText mail=findViewById(R.id.password_Register_TextEdit);
        String email=mail.getText().toString();
        String password=pass.getText().toString();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("mail", email);
        user.put("password", password);


// Add a new document with a generated ID
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Register.this, "Kayıt Başarılı.",
                                Toast.LENGTH_SHORT).show();

                        backbtn(view);
                            }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Kayıt Başarısız.",
                                Toast.LENGTH_SHORT).show();
                    }
                });



    }public  void  backbtn(View v){
        super.onBackPressed();
    }
}