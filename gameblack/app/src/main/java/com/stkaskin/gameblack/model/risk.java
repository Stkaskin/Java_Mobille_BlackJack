package com.stkaskin.gameblack.model;

public class risk {
    public int puan = 0;
    public  int risk = 0;
    public   int win = 0;

    public int riskCalculator() {
//normal şartlarda kart sayısı hesaplanır
        // kombinasyonlar tahmin edilir
        //örnek 17 olan biri 5-4-3-2-1 sayılarından kaçar tane olduğu
        // buna göre olaslıkların riski etkilemesi vs vs

        //burada yaptıgımız düz mantık hesaplama
        if (this.puan > 11)
            if (puan < 17)
                return (puan - 11) * 5;
            else
                return 50 + ((puan - 16) * 10);
      else if (puan<11){
          return  0;
        }
        else if (puan == 11)
            return 0;
        else if (risk == 11)
            return 10;



        return 0;
    }
}
