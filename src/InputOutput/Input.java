package InputOutput;

import java.util.Scanner;

public class Input {

    private static Scanner input = new Scanner(System.in);
    private final static int MIN_MODE_OPTION = 1;
    private final static int MAX_MODE_OPTION = 3;
    private final static int MIN_PLAYERS = 2;
    private final static int MAX_PLAYERS = 4;
    private final static int MIN_POSITION = 1;
    private final static int MAX_POSITION = 4;

    public static int setQuantityPlayers() {
        System.out.println("HOW MANY PLAYERS??");
        System.out.print("> ");
        int players = input.nextInt();

        while (players < MIN_PLAYERS || players > MAX_PLAYERS) {
            Output.displayInvalidQuantityPlayers(MIN_PLAYERS,MAX_PLAYERS);
            players = input.nextInt();
        }

        return players;
    }

    public static int selectMode() {
        Output.displayDominoModes();
        int mode = input.nextInt();

        while (mode < MIN_MODE_OPTION || mode > MAX_MODE_OPTION) {
            Output.displayDominoModes();
            mode = input.nextInt();
        }

        return mode;
    }

    public static boolean playWithTeams() {
        Output.displayTeamsOption();
        int teams = input.nextInt();
        return teams == 1;
    }

    public static int selectTile(int length) {
        System.out.println("\n" + "SELECT TILE TO PLAY.");
        System.out.print("> ");
        int tileIdx = input.nextInt();
        while (tileIdx < 0 || tileIdx > (length + 1)) {
            Output.displayOutOfTiles(length +1);
            tileIdx = input.nextInt();
        }
        return tileIdx - 1;
    }

    public static int putPosition() {
        Output.displayPositionSelection();
        int pos = input.nextInt();

        while (pos < MIN_POSITION || pos > MAX_POSITION){
            Output.displayPositionSelection();
            pos = input.nextInt();
        }
        return pos;
    }




}
