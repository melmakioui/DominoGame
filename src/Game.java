
import Rules.Domino;
import Game.*;

import java.util.Scanner;

public class Game {

    private Player[] players;
    private Board board;
    private Domino domino;
    private DeckTiles deck;
    private boolean team;

    public Game() {
        this.players = new Player[4];
        this.board = new Board();
        this.domino = new Domino();
        this.deck = new DeckTiles();
        this.team = false;

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
        turn = startGameArea();
        playRound();

        System.out.println(turn);
        System.out.println(players[0]);
        System.out.println(players[1]);
        System.out.println(players[2]);
        System.out.println(players[3]);

        do {

        } while (!domino.isWinner(players[turn]));
    }

    private void playRound(){
        Scanner in = new Scanner(System.in);
        int turn = 0;
        int type = in.nextInt();
        System.out.println("current tiles");
        System.out.println(players[turn]);
        Tile temp;
        if (!domino.canPlay(players[turn],board))
            players[turn].addTile(deck.getDominoTile());

        System.out.println("sel your tile");
        int seltile = in.nextInt();
        int pos = in.nextInt();

        if (seltile == 1) {
            if (domino.isValidPlay(players[turn].getTile(pos), board))
                board.addFirst(players[turn].getTile(pos));
        }else if (seltile == 2) {
            if (domino.isValidPlay(players[turn].getTile(pos), board))
                board.addFirst(players[turn].getTile(pos));
        }


        board.displayBoard();

        do{



        }while (!domino.isRoundWinner(players[turn]));



    }

    private void clearGameArea() {
        for (Player player : players)
            if (!player.isHandEmpty())
                player.clearHand();

        deck.clearDeck();
        board.clearBoard();
    }

    private int startGameArea(){
        domino.initTiles(deck);
        domino.drawTileFromDeck(deck,players);
        int idxInit = domino.startPlayer(players);
        board.addLast(players[idxInit].putTile(0));
        board.displayBoard();
        return idxInit;
    }



}
