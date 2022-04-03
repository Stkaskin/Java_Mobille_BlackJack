package com.stkaskin.gameblack.model;

import java.util.ArrayList;
import java.util.Locale;

public class cart_operations {
    private  ArrayList type_new(ArrayList typeList, String symbol, String renk) {
        String[] t = new String[2];
        t[0] = symbol;
        t[1] = renk.toLowerCase(Locale.ROOT);
        typeList.add(t);
        return typeList;
    }
    public  ArrayList<cart> Liste() {
        ArrayList<cart> list = new ArrayList();

        list.add(new cart("A", "", 11, ""));
        list.add(new cart("2", "", 2, ""));
        list.add(new cart("3", "", 3, ""));
        list.add(new cart("4", "", 4, ""));
        list.add(new cart("5", "", 5, ""));
        list.add(new cart("6", "", 6, ""));
        list.add(new cart("7", "", 7, ""));
        list.add(new cart("8", "", 8, ""));
        list.add(new cart("9", "", 9, ""));
        list.add(new cart("10", "", 10, ""));
        list.add(new cart("J", "", 10, ""));
        list.add(new cart("Q", "", 10, ""));
        list.add(new cart("K", "", 10, ""));

        ArrayList<String[]> typeList = new ArrayList();

        typeList = type_new(typeList, "♣", "black");
        typeList = type_new(typeList, "♥", "red");
        typeList = type_new(typeList, "♦", "blue");
        typeList = type_new(typeList, "♠", "green");

        ArrayList<cart> total = new ArrayList();
        for (String[] typeItem : typeList) {
            String[] a = (String[]) typeItem;
            for (cart item : list) {
                cart _cart = ((cart) item);
                total.add(new cart(_cart.type, a[0], _cart.puan, a[1]));

            }

        }
        return total;
    }
    public  player specialCart(player player, cart cart) {

        int count = player.puans.size();
        if (cart.type.equals("A"))
            for (int i = 0; i < count; i++) {
                int puan_temp = ((risk) player.puans.get(i)).puan;
                player.addPuan(i, 1);
                player.newPuan(puan_temp + 11);
            }


        return player;
    }
    public  boolean specialCards(cart cart) {
        if (cart.type.equals("A"))
            return true;
        return false;

    }
}
