
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
        initPlayers();
        initGame();
    }

    private void initMode(){
        int mode = 0;

        switch (mode){
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
        for (int i = 0; i < team.length; i++) {
            team[i] = new Team(i + 1);
        }
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

        do {
            clearGameArea();
            startGameArea();

            turn = rules.startPlayer(players);

            Tile initialTile = players[turn].getTile(0);
            players[turn].removeTile(initialTile);
            board.addLast(initialTile);
            board.displayBoard();
            System.out.println(players[turn].getName() + " STARTED!");

            turn++;
            turn = turn % players.length;
            playRound();
        } while (!rules.isWinner(players[turn]));
    }



    private void playRound() {

        do {

            //TODO SIMPLIFY IO, CHECK DOMINO CLASS, START PLAYING WITHOUT TEAM.
        } while (!rules.isRoundWinner(players[turn]));
    }


    private int changeTurn(int turn) {
        turn++;
        return turn % players.length;
    }

}
