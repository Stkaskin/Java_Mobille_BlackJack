package com.stkaskin.gameblack;

import static java.lang.Thread.*;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.stkaskin.gameblack.model.cart;
import com.stkaskin.gameblack.model.cart_operations;
import com.stkaskin.gameblack.model.colors;
import com.stkaskin.gameblack.model.player;
import com.stkaskin.gameblack.model.player_score;
import com.stkaskin.gameblack.model.risk;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn_baslatdurdur;
    TextView oyuncukartlar;
    TextView bot1_kartlar;
    TextView bot2_kartlar;
    TextView kazanan_view;
    TextView bot3_kartlar;

    int oyuncuSecim = 0;

public  void  back_Button_Game_Click(View v){
    super.onBackPressed();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_baslatdurdur = findViewById(R.id.button_baslat);
        oyuncukartlar = findViewById(R.id.player_kart);
        bot1_kartlar = findViewById(R.id.bot1_kart);
        bot2_kartlar = findViewById(R.id.bot2_kart);
        bot3_kartlar = findViewById(R.id.bot3_kart);
        kazanan_view = findViewById(R.id.kazanan_goster_view);
        setText(kazanan_view, "");
        loadPlayers();
        for (player p : listPlayer) {
            setText(p.view, "");
            setText(p.view_puan, "");
            setText(p.view_durum, "");

        }
    }

    com.stkaskin.gameblack.model.cart_operations cart_operations = new cart_operations();
    Thread thread = new gameThread();
    int tur = 0;
    public static ArrayList<cart> list;
    public int getSecili;
    public static int active_player;
    public static int wait_playercount;
    public static int loser_playercount;
    public static ArrayList<player> listPlayer;
    public static long sleep_time;
    public static String player_name = "Kullanıcı";

    private void loadPlayers() {
        tur = 0;
        listPlayer = new ArrayList<>();
        addListPlayer(player_name, oyuncukartlar, findViewById(R.id.player_durum_text), findViewById(R.id.player_puan_text), true, findViewById(R.id.player1_imageView));
        addListPlayer("BOT1", bot1_kartlar, findViewById(R.id.bot1_durum_text), findViewById(R.id.bot1_puan_text), false, findViewById(R.id.bot1_imageView));
        addListPlayer("BOT2", bot2_kartlar, findViewById(R.id.bot2_durum_text), findViewById(R.id.bot2_puan_text), false, findViewById(R.id.bot2_imageView));
        addListPlayer("BOT3", bot3_kartlar, findViewById(R.id.bot3_durum_text), findViewById(R.id.bot3_puan_text), false, findViewById(R.id.bot3_imageView));

    }

    public void addListPlayer(String name, TextView kart_text, TextView viewDurumText, TextView viewPuanText, Boolean goster, ImageView imageView) {
        player player = new player(name);
        player.view = kart_text;
        player.goster = goster;
        player.view_durum = viewDurumText;
        player.view_puan = viewPuanText;
        player.view_image = imageView;
        listPlayer.add(player);
        setText(viewDurumText, "");
        setText(kazanan_view, "Başlamak için Başlata basın.!.");
        setText(viewPuanText, "");
        setText(kart_text, "");
        setImageView(player.view_image,R.mipmap.table);


    }


    public void baslat(View view) {


        if (!thread.isAlive()) {
            thread = new gameThread();
            thread.start();
            setText(btn_baslatdurdur, "Bitir");

        } else {
            thread.interrupt();
            setText(btn_baslatdurdur, "Başlat");
        }


    }

    public class gameThread extends Thread {
        @Override
        public void run() {
            try {
                waiz();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }

    }

    public void devamet(View view) {
        oyuncuSecim = 1;
    }

    public void pasgec(View view) {
        oyuncuSecim = 2;
        sleep_time = 200;
    }

    private void loading() {
        sleep_time = 1500;
        list = cart_operations.Liste();
        getSecili = -1;
        active_player = 1;
        wait_playercount = 0;
        loser_playercount = 0;
    }

    public void waiz() throws InterruptedException {
        // write your code here
        loadPlayers();
        loading();


        active_player = listPlayer.size();
        int start = 0;
        for (int i = 0; i < list.size(); ) {

            for (int j = 0; j < active_player; j++) {

                getSecili = j;
                //başlangiç kartlari dagitiliyor
                if (start < active_player * 2) {
                    player player_ = (player) listPlayer.get(j);
                    player_ = player_next(player_, false);
                    listPlayer.set(j, player_);
                    start++;
                    getCartsPlayer(player_);
                } else if
                (

                        startProgram(j)

                )
                    break;

            }
            tur++;
            if (wait_playercount == active_player || loser_playercount == active_player - 1)
                break;
        }
        int winner = winnerNumber();
        for (player pp : listPlayer
        ) {
            getCartsPlayer(pp);
        }
        allWinner(winner);
        winnerNumber();
        thread.interrupt();
        setText(btn_baslatdurdur, "Başlat");

    }

    public void setImageView(ImageView view, int imageId) {
        runOnUiThread(() -> view.setImageResource(imageId));

    }

    public void durum(int sira, player player) throws InterruptedException {

        if (player.wait && !player.lose) {
            System.out.println((sira + 1) + " .Player Bekliyor.");
            setText(kazanan_view, (player.player_name) + " Bekliyor.");
        } else if (!player.wait) {
            System.out.println((sira + 1) + " .Player Oynuyor.");
            setText(kazanan_view, (player.player_name) + " Oynuyor.");
        }
        boolean t1 = !player.player_name.equals(player_name);
        boolean t2 = !(player.wait || player.win || player.lose);

        if (t1 && (t2 || tur == 2)) {
            sleep(sleep_time);
        }


    }


    private void setText(final TextView text, final String value) {
        runOnUiThread(() -> text.setText(value));
    }

    private void setText(final Button text, final String value) {
        runOnUiThread(() -> text.setText(value));
    }


    @SuppressWarnings("BusyWait")
    public boolean startProgram(int index) throws InterruptedException {

        player player_ = (player) listPlayer.get(index);
        setImageView(player_.view_image, R.mipmap.table_active);
        durum(index, player_);
        getCartsPlayer(player_);


        if (player_.player_name.equals(player_name)) {

            if (!player_.wait && !player_.win && !player_.lose) {

                System.out.println("Kartlarınız: ");


                System.out.println("Kart çek = 1 \n Bekle = 2");

                //   t.start();
                oyuncuSecim = 0;
                while (oyuncuSecim == 0) sleep(500);
                //değğiştirildi
                // Scanner scanner = new Scanner(System.in);
                boolean swich_ = false;
                optimizasyon();
                while (!swich_) {

                    switch (oyuncuSecim) {
                        case 1:
                            player_ = player_next(player_, true);
                            System.out.println("Yeni Kartlarınız: ");
                            listPlayer.set(index, player_);

                            swich_ = true;
                            break;
                        case 2:
                            player_.wait = true;
                            wait_playercount++;
                            listPlayer.set(index, player_);
                            swich_ = true;
                            break;
                    }
                    if (!swich_) {
                        System.out.println("Kart çek = 1 \nBekle = 2");
                        optimizasyon();
                    }
                }
            }
        } else {
            player_ = player_next(player_, false);
            listPlayer.set(index, player_);
        }

        getCartsPlayer(player_);

        setImageView(player_.view_image, R.mipmap.table);
        return wait_playercount == active_player || loser_playercount == active_player - 1;
    }

    public void getCartsPlayerPuan() {
        ArrayList<player_score> scores = scoreList();

        for (int i = 0; i < scores.size(); i++) {
            player_score score = ((player_score) scores.get(i));
            if (score.player.goster)
                setText(score.player.view_puan, (score.puan + ""));
            else
                setText(score.player.view_puan, "");

        }


    }

    public void getCartsPlayer(player player_) {
        StringBuilder carts = new StringBuilder();
        for (int j = 0; j < player_.carts.size(); j++) {
            if (player_.goster) {
                carts.append(player_.carts.get(j).symbol).append(player_.carts.get(j).type).append("\n");

                // setText(player_.view_puan, getCartsPlayerPuan(player_));
            } else {
                carts.append("*" + "*" + "\n");

            }

        }
        setText(player_.view, carts + "\n");
        getCartsPlayerPuan();
        if ((player_.lose || player_.win))
            if (player_.win)
                setText(player_.view_durum, "PAS  ");
            else
                setText(player_.view_durum, "Battı ");

        else if (player_.wait)
            setText(player_.view_durum, "PAS ");
        else
            setText(player_.view_durum, "Devam  ");
        if ((player_.wait || player_.lose || player_.win) && player_.player_name.equals(player_name))
            sleep_time = 200;
        setText(player_.view, carts.toString());
    }

    public void allWinner(int winner_point) {
        ArrayList<player_score> list = scoreList();
        int count = 0;
        ArrayList<player_score> listeKazanan = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            player_score score = ((player_score) list.get(i));

            if (winner_point == score.puan) {
                System.out.println("Kazanan oyuncu " + score.player.player_name + " puanı: " + score.puan);
                listeKazanan.add(score);
                count++;
            }
        }
        if (count > 1) {
            StringBuilder mesaj = new StringBuilder("Beraberlik: ");
            for (player_score score_ : listeKazanan)
                mesaj.append(score_.player.player_name).append("  ");
            mesaj.append(winner_point).append(" Puan ile Berabere kaldı");
            System.out.println("Beraberlik");
            setText(kazanan_view, mesaj.toString());

        } else
            setText(kazanan_view, "Kazanan oyuncu " + listeKazanan.get(0).player.player_name + " puanı: " + winner_point);

    }

    public int winnerNumber() {
        ArrayList<player_score> scores = scoreList();
        int winner = 0;
        for (int i = 0; i < scores.size(); i++) {
            player_score score = ((player_score) scores.get(i));
            player_score score_w = ((player_score) scores.get(winner));

            if (21 - score.puan < 21 - score_w.puan)
                winner = i;
            System.out.print(score.player.player_name + " oyuncu puan " + score.puan + " kartlar: ");
            score.player.goster = true;
            getCartsPlayer(score.player);
            System.out.println(" ");
        }
        return ((player_score) scores.get(winner)).puan;
    }

    public ArrayList<player_score> scoreList() {
        ArrayList<player_score> scores = new ArrayList<>();
        for (int i = 0; i < active_player; i++) {
            player player_ = (player) listPlayer.get(i);
            if (player_.lose)
                scores.add(new player_score(player_, 0));
            else if (player_.win)
                scores.add(new player_score(player_, 21));
            else {
                int count = player_.puans.size();
                int big = 0;
                for (int j = 0; j < count; j++) {
                    risk item = (risk) player_.puans.get(j);
                    risk item2 = (risk) player_.puans.get(big);
                    if (item.puan > item2.puan)
                        big = j;
                }

                scores.add(new player_score(player_, ((risk) player_.puans.get(big)).puan));


            }

        }
        return scores;
    }

    public player player_next(player player1, boolean player_active) {
        if (player1.play && !player1.wait) {
            Random random = new Random();
            int randomCart = random.nextInt(list.size());

            if (!player_active) {
                player1 = continue_cart(player1);
                if (!player1.wait)
                    player1 = playerCartAdd((cart) list.get(randomCart), player1);
                else {
                    System.out.println("Bekliyor.");
                }
            }
            if (player_active) {
                player1 = playerCartAdd((cart) list.get(randomCart), player1);
            }

        }
        return player1;
    }

    public player continue_cart(player player) {
        if (!player.wait) {
            int total = 0;
            int count = 0;
            for (int i = 0; i < player.puans.size(); i++) {
                risk item = (risk) player.puans.get(i);
                if (item.risk > 80) {
                    wait_playercount++;
                    player.wait = true;

                    break;
                } else {
                    total += item.risk;
                    count++;
                }
            }
            if (total > 0) total /= count;
            if (!player.wait) {
                Random random = new Random();
                int random_n = (random.nextInt(500000) % 100) + 1;
                if (random_n > total)
                    player.wait = false;

                else {
                    player.wait = true;
                    wait_playercount++;
                }
            }
        }
        return player;

    }

    public player playerCartAdd(cart cart, player player) {

        player.carts.add(cart);
        //  System.out.println(cart.symbol + cart.type);
        if (cart_operations.specialCards(cart)) {
            player = cart_operations.specialCart(player, cart);
        } else {
            int count = player.puans.size();
            for (int i = 0; i < count; i++)
                player.addPuan(i, cart.puan);
        }
        player = playerControl(player);
        list.remove(cart);
        return player;
    }


    public void optimizasyon() {
        wait_playercount = 0;
        for (int i = 0; i < active_player; i++) {
            player player = (player) listPlayer.get(i);
            if (player.wait)
                wait_playercount++;
        }
    }

    public player playerControl(player player) {

        int count = player.puans.size();
        for (int i = 0; i < count; i++) {
            int _puan = ((risk) player.puans.get(i)).puan;
            if (_puan > 21) {
                player.puans.remove(i);
                i--;
                count--;
                if (count == 0) {
                    System.out.println();
                    colors.changeColorText(colors.ANSI_PURPLE, player.player_name + " Kaybetti 21'i aştı\n puanı:" + _puan);
                    player.lose = true;
                    player.wait = true;
                    wait_playercount++;
                    loser_playercount++;
                }
            } else if (_puan == 21) {
                player.puans.clear();
                player.newPuan(21);
                player.win = true;
                player.wait = true;
                wait_playercount++;
                break;
            }

        }
        return player;
    }


}