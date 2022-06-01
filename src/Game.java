
import Rules.Domino;
import Game.Player;
import Game.Board;
import Game.DeckTiles;

public class Game {

    private Player[] players;
    private Board board;
    private Domino domino;
    private DeckTiles deck;


    public Game() {
        this.players = new Player[2];
        this.board = new Board();
        this.domino = new Domino();
        this.deck = new DeckTiles();

        initPlayers();
        initGame();
    }

    private void initPlayers() {
        for (int i = 0; i < players.length; i++)
            players[i] = new Player(i + 1);
    }

    public void initGame() {

        int turn = 0;
        clearGameArea();
        startGameArea();

        do {
            //play
        } while (!domino.isWinner(players[turn]));
    }

    private void clearGameArea() {
        for (Player player : players)
            if (!player.isHandEmpty())
                player.clearHand();

        deck.clearDeck();
        board.clearBoard();
    }

    private void startGameArea(){
        domino.initTiles(deck);
        domino.drawTileFromDeck(deck,players);
        board.addLast(domino.getInitTile(players));
        board.displayBoard();
    }



}
