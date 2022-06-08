
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

        initPlayers();
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

            Tile initialTile = players[turn].putTile(0);
            board.addLast(initialTile);
            System.out.println("-" + board + "-");;

            playRound();
        } while (!rules.isWinner(players[turn]));
    }

    private void playRound() {

        Tile tempTile;

        System.out.println(players[turn].getName() + " STARTED!");
        changeTurn();
        System.out.println(board);//io

        do {
            System.out.println(players[turn]);
            if (!hasTilesToPlay()){
                changeTurn();
                continue;
            }
            tempTile = drawCorrectTile();
            placeTile(tempTile);

            players[turn].removeTile(tempTile);

            System.out.println(board);
            changeTurn();
        } while (!rules.isRoundWinner(players[turn]));

    }

    private void placeTile(Tile tempTile){
       int position = IO.putPosition();
        if (position == 1) // verificar numero  1-2 | mirar si son compatibles otra vez
            board.addFirst(tempTile);
        if (position == 2) // si es valido al final
            board.addLast(tempTile);
    }

    private Tile drawCorrectTile(){

        int tiles;
        int selected;
        Tile tempTile;
        do {
            tiles = (players[turn].getHand().size() - 1);
            selected = IO.selectTile(tiles);
            tempTile = players[turn].getTile(selected);

        } while (!(isValidPlay(tempTile, selected)));

        return tempTile;
    }

    private boolean isValidPlay(Tile tempTile, int tile) {

        while (!rules.isValidPlay(tempTile, board)) {
            System.out.println("NOT EQUAL...");
            tile = (IO.selectTile(players[turn].getHand().size()) - 1);
            tempTile = players[turn].getTile(tile);
        }

        return true;
    }

    private boolean hasTilesToPlay( ){ //change name
        Tile stealedTile;
        while (!rules.canPlay(players[turn], board)) {
            if (deck.isEmpty())
                return false;
            stealedTile = deck.getDominoTile();
            System.out.println("+1 TILE " + players[turn].getName());
            players[turn].addTile(stealedTile);
        }
        return true;
    }

    private void changeTurn() {
        turn++;
        turn = turn % players.length;
    }

}
