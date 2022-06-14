package InputOutput;

import Game.Board;
import Game.Player;
import Rules.Chilean;
import Rules.Domino;
import Rules.DominoRules;
import Rules.Latin;

public class Output {

    public static void displayInvalidQuantityPlayers(int MIN_PLAYERS, int MAX_PLAYERS) {
        System.out.println();
        System.out.println("PLEASE MIN " + MIN_PLAYERS + " AND MAX " + MAX_PLAYERS + " PLAYERS");
        System.out.print("> ");
    }

    public static void displayDominoModes() {
        System.out.println();
        System.out.println("SELECT DOMINO MODE");
        System.out.println("1. SPANISH  \n" +
                "2. CHILEAN \n" +
                "3. LATIN");
        System.out.print("> ");
    }

    public static void displayTeamsOption() {
        System.out.println();
        System.out.println("DO YOU WANT TO PLAY WITH TEAMS?" +
                "\n" + "> 1. YES." +
                "\n" + "> 2. NO.");
        System.out.print("> ");
    }

    public static void displayOutOfTiles(int length) {
        System.out.println();
        System.out.println("PLEASE YOU HAVE " + (length + 1) + " TILES");
        System.out.print("> ");
    }

    public static void displayPositionSelection() {
        System.out.println();
        System.out.println("WHERE DO YOU WANNA PUT THE TILE?");
        System.out.println(" --> 1.START" + "\n" +
                " <--  2.END");
        System.out.print("> ");
    }


    //GAME

    public static void displayDeckEmpty(Player player) {
        System.out.println();
        System.out.println("YOU CANT PLAY ANY TILE... AND DECK IS EMPTY!!! PASS...");
    }

    public static void dislayIncorrectTile() {
        System.out.println("INCORRECT TILE...");
        System.out.println("TAKE THE CORRECT TILE!");
    }

    public static void displaySummary(Player player, Player... players) {
        System.out.println("");
        System.out.println("WINNER!!! --> " + player.getName());
        System.out.println("**************SUMMARY*************");
        for (Player p : players) {
            System.out.println(p);
            System.out.println();
            System.out.println("************************************");
        }
    }

    public static void displayWinner(DominoRules dominoRules, Player player) {

        if (dominoRules instanceof Domino)
            System.out.println("YOU WIN " + player.getName() + " WITH TEAM " +  player.getTeamName() +" YOU REACH THE MAX POINTS ");

        if (dominoRules instanceof Chilean)
            System.out.println("YOU LOSE " + player.getName() + " YOU REACH THE MAX POINTS ");

        if (dominoRules instanceof Latin)
            System.out.println("YOU WIN " + " YOUR TEAM REACH THE MAX POINTS ");

    }

    public static void displayWinnerDeadGame(Player player, DominoRules dominoRules){

        if (dominoRules instanceof Domino) {
            System.out.println("");
            System.out.println("***IS DEAD GAME!***");
            System.out.println("YOU WIN THIS ROUND!! " + player.getName() + " YOU ARE THE ONE WITH FEWER POINTS!!");
        }
    }

    public static void displayBoard(Board board) {
        System.out.println("");
        System.out.println("++**BOARD**++");
        System.out.println(board);
    }

    public static void displayStartedPlayer(Player player){
        System.out.println(player.getName() + " STARTED!");
    }

    public static void displayAddedTile(Player player){
        System.out.println(player.getName() + " +" + 1 + " TILE");
    }

}
