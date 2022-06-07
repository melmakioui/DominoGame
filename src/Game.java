
import Rules.Domino;
import Game.*;

public class Game {

    private Team team[];
    private Player[] players;
    private Board board;
    private Domino domino;
    private DeckTiles deck;


    public Game() {
        this.players = new Player[4];
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

    private void initTeams() {
        this.team = new Team[2];
        for (int i = 0; i < team.length; i++) {
            team[i] = new Team(i + 1);
        }
    }

    public void initGame() {

        clearGameArea();
        startGameArea();

        int turn;
        turn = domino.startPlayer(players);

        Tile initialTile = players[turn].getTile(0);
        board.addLast(initialTile);

        turn++;
        turn = turn % players.length;

        boolean playTeams = false;
        if (playTeams)
            initTeams();


/*
        System.out.println(turn);
        System.out.println(players[0]);
        System.out.println(players[1]);
        System.out.println(players[2]);
        System.out.println(players[3]);*/

        do {
            playRound();
        } while (!domino.isWinner(players[turn]));
    }

    private void playRound() {


    }

    private void clearGameArea() {
        for (Player player : players)
            player.clearHand();
        deck.clearDeck();
        board.clearBoard();
    }

    private void startGameArea() {
        domino.initTiles(deck);
        domino.drawTileFromDeck(deck, players);
        board.displayBoard();
    }


}
