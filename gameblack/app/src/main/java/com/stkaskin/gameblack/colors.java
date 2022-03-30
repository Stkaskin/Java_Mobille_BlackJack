package com.stkaskin.gameblack;

import java.util.Locale;

public class colors {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String setColor(String renk) {
        colors colors = new colors();
        switch (renk.toLowerCase(Locale.ROOT)) {
            case "red":
                return colors.ANSI_RED;

            case "black":
                return colors.ANSI_BLACK;

            case "green":
                return colors.ANSI_GREEN;

            case "yellow":
                return colors.ANSI_YELLOW;

            case "blue":
                return colors.ANSI_BLUE;

            case "purple":
                return colors.ANSI_PURPLE;

            case "cyan":
                return colors.ANSI_CYAN;

            case "white":
                return colors.ANSI_WHITE;

            case "":
                return colors.ANSI_RESET;

        }
        return "";

    }

    public static void changeColorText(String col,String text) {

        System.out.print(colors.setColor(col+text));
        System.out.print(colors.ANSI_RESET);
    }
}
