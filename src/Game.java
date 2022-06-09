
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
        //team.removePoints
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
            System.out.println(board);
            playRound();
            rules.addPoints(players[turn]);
        } while (!rules.isWinner(players[turn]));
    }

    private void playRound() {

        Tile tempTile;
        System.out.println(players[turn].getName() + " STARTED!");
        changeTurn();

        do {
            System.out.println(players[turn]);
            if (!hasTilesToPlay()) {
                changeTurn();
                continue;
            }

            tempTile = drawTile();
            int position = IO.putPosition();

            if (isValidPlay(tempTile, position))
                players[turn].removeTile(tempTile);
            else  tempTile = chooseCorrectTile();

            players[turn].removeTile(tempTile);
            System.out.println(board);
            changeTurn();
        } while (!rules.isRoundWinner(players[turn]));
    }

    private Tile drawTile() {

        int tiles = (players[turn].getHand().size() - 1);
        int selectedTile;
        Tile tempTile;

        selectedTile = IO.selectTile(tiles);
        tempTile = players[turn].getTile(selectedTile);
        return tempTile;
    }

    private boolean isValidPlay(Tile tempTile, int position) {

        int FIRST = 1;
        int LAST = 2;

        if (position == FIRST)
            if (rules.isValidPlay(tempTile, board, position)) {
                board.addFirst(tempTile);
                return true;
            }

        if (position == LAST)
            if (rules.isValidPlay(tempTile, board, position)) {
                board.addLast(tempTile);
                return true;
            }
        return false;
    }

    private Tile chooseCorrectTile() {
        System.out.println("INCORRECT TILE...");
        Tile correctTile = drawTile();
        int position = IO.putPosition();
        while (!isValidPlay(correctTile, position)) {
            correctTile = drawTile();
            position = IO.putPosition();
        }

        return correctTile;
    }

    private boolean hasTilesToPlay() { //change name
        Tile stealedTile;
        int quantity = 1;
        while (!rules.canPlay(players[turn], board)) {
            if (deck.isEmpty())
                return false;
            stealedTile = deck.getDominoTile();
            System.out.println(players[turn].getName() + " +" + quantity + "TILE" + players[turn].getName());
            players[turn].addTile(stealedTile);
        }
        //System.out.println(players[turn]);
        return true;
    }

    private void changeTurn() {
        turn++;
        turn = turn % players.length;
    }

}
