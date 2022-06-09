
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
            rules.addPoints(players[turn]); // --
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

            if (position == 1) {
                if (rules.isValidPlay(tempTile,board,position))
                    board.addFirst(tempTile);
            } else if (position == 2)
                if (rules.isValidPlay(tempTile,board,position))
                    board.addLast(tempTile);

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

    private boolean hasTilesToPlay() { //change name
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
