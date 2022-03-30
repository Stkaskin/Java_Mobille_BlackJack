package com.stkaskin.gameblack;

import android.widget.TextView;

import java.util.ArrayList;

public class player {
    public  String player_name;
    public boolean play = false;
      public boolean wait = false;
    public  boolean win = false;
    public  boolean lose = false;
    public  ArrayList<cart> carts = new ArrayList<cart>();
    public  ArrayList puans = new ArrayList();
    public TextView view;
    public  boolean goster=false;


    public player(String name) {
        this.player_name=name;
        this.play = true;
      risk risk=new risk();
      risk.puan=0;
        risk.risk=risk.riskCalculator();
        this.puans.add(risk);
    }

    public void addPuan(int index, int puan) {
        int old_puan =  ((risk)this.puans.get(index)).puan;
        risk risk=new risk();
        risk.puan=old_puan + puan;
        risk.risk=risk.riskCalculator();

        this.puans.set(index, risk);
    }

    public void newPuan(int puan) {
        risk risk=new risk();
        risk.puan= puan;
        risk.risk=risk.riskCalculator();

        this.puans.add(risk);
    }



}
