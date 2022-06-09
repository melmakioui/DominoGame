package InputOutput;

import java.util.Scanner;

public class IO {

    private static Scanner input = new Scanner(System.in);
    private final static int MIN_MODE_OPTION = 1;
    private final static int MAX_MODE_OPTION = 3;


    public static int setQuantityPlayers() {
        System.out.println("HOW MANY PLAYERS??");
        System.out.print("> ");
        int players = input.nextInt();

        while (players < 2 || players > 4) {
            System.out.println("PLEASE MINIM " + 2 + " AND MAX " + 4);
            System.out.print("> ");
            players = input.nextInt();
        }

        return players;
    }

    public static int selectMode() {
        System.out.println("SELECT DOMINO MODE");
        System.out.println("\n 1. SPANISH" +
                "\n 2. CHILEAN" +
                "\n 3. LATINO");
        System.out.print("> ");
        int mode = input.nextInt();

        while (mode < MIN_MODE_OPTION || mode > MAX_MODE_OPTION) {
            System.out.println("PLEASE SELECT A CORRECT MODE.");
            System.out.print(">");
            mode = input.nextInt();
        }

        return mode;
    }


    public static boolean playWithTeams() {
        System.out.println("DO YOU WANT TO PLAY WITH TEAMS?" +
                "\n" + " --> 1. YES." +
                "\n" + " --> 2. NO.");
        System.out.print("> ");

        int teams = input.nextInt();

        return teams == 1;
    }

    public static int selectTile(int length) {
        System.out.println("\n" + "SELECT ONE TILE.");
        int tileIdx = input.nextInt();
        while (tileIdx < 0 || tileIdx > (length + 1)) {
            System.out.println("PLEASE YOU HAVE " + (length + 1) + " TILES");
            System.out.print("> ");
            tileIdx = input.nextInt();
        }
        return tileIdx - 1;
    }

    public static int putPosition() {
        System.out.println("WHERE DO YOU WANNA PUT THE TILE?");
        System.out.println(" --> 1.START" + "\n" +
                " --> 2.END");
        System.out.print("> ");
        int pos = input.nextInt();
        return pos;
    }


}
