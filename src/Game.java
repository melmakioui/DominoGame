
import InputOutput.IO;
import Rules.Domino;
import Game.*;
import Rules.Rules;
import Rules.Chilean;

public class Game {

    private Team team[];
    private Player[] players;
    private Board board;
    private Rules rules;
    private DeckTiles deck;
    private static int turn = 0;


    public Game() {
        this.board = new Board();
        this.deck = new DeckTiles();

        initMode();
        initGame();
    }

    private void initMode() {
        int mode = IO.selectMode();

        switch (mode) {
            case 1 -> rules = new Domino();
            case 2 -> rules = new Chilean();
        }

    }

    private void initPlayers() {
        int min = 2;
        int max = 4;

        int numPlayers = IO.setQuantityPlayers(min, max);
        this.players = new Player[numPlayers];

        for (int i = 0; i < players.length; i++)
            players[i] = new Player(i + 1);
    }

    private void initTeams() {
        if (players.length < 4)
            System.out.println("SORRY YOU NEED 4 PLAYERS...");
        this.team = new Team[2];

        for (int j = 0; j < team.length; j++)
            team[j] = new Team();
    }


    private void clearGameArea() {
        for (Player player : players) {
            player.clearHand();
            player.removePoints();
        }
        deck.clearDeck();
        board.clearBoard();
    }

    private void startGameArea() {
        rules.initTiles(deck);
        rules.drawTileFromDeck(deck, players);
        board.displayBoard();
    }


    public void initGame() {

        if (IO.isTeam()) initTeams();
        else initPlayers();

        do {
            clearGameArea();
            startGameArea();

            turn = rules.startPlayer(players);

            Tile initialTile = players[turn].putTile(0);
            board.addLast(initialTile);
            System.out.println("-" + board + "-");;

            playRound();
        } while (!rules.isWinner(players[turn]));
    }

    private void playRound() {

        int selTile;
        int position;
        Tile tempTile;
        System.out.println(players[turn].getName() + " STARTED!");
        changeTurn();

        do {
            System.out.println(board);
            System.out.println(players[turn]);

            while (!rules.canPlay(players[turn], board)) {
                tempTile = deck.getDominoTile();
                System.out.println("+1 TILE " + players[turn].getName());
                players[turn].addTile(tempTile); //para de ROBAR fichas aunque no consiga la equivalente al board
            }


            do {
                selTile = (IO.selectTile(players[turn].getHand().size()) - 1);
                tempTile = players[turn].getTile(selTile);
            } while (!(isValidPlay(tempTile, selTile)));

            position = IO.putPosition();
            if (position == 1) // verificar numero  1-2 | mirar si son compatibles otra vez
                board.addFirst(tempTile);
            if (position == 2) // si es valido al final
                board.addLast(tempTile);

            players[turn].removeTile(tempTile);

            System.out.println(board);
            changeTurn();
            //TODO SIMPLIFY IO, CHECK DOMINO CLASS, START PLAYING WITHOUT TEAM.

        } while (!rules.isRoundWinner(players[turn]));

    }

    private boolean isValidPlay(Tile tempTile, int tile) {

        while (!rules.isValidPlay(tempTile, board)) {
            System.out.println("NOT EQUAL...");
            tile = (IO.selectTile(players[turn].getHand().size()) - 1);
        }

        return true;
    }


    private void changeTurn() {
        turn++;
        turn = turn % players.length;
    }

}
