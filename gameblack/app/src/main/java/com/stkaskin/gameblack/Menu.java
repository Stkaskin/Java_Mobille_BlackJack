package com.stkaskin.gameblack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }
    public  void startGame(View v)
    {
        Intent intent=new Intent(Menu.this,MainActivity.class);
        startActivity(intent);
    }
    public  void profile_Go(View v)
    {
        Intent intent=new Intent(Menu.this,profille.class);
        startActivity(intent);
    }
    public  void  back_menu(View v){
        super.onBackPressed();
    }
}