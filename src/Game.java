
import InputOutput.Input;
import Rules.*;
import Game.*;


public class Game {

    private Team team[];
    private Player[] players;
    private Board board;
    private DominoRules domino;
    private DeckTiles deck;
    private static int turn = 0;


    public Game() {
        this.board = new Board();
        this.deck = new DeckTiles();

        initMode();
        initGame();
    }

    private void initMode() {
        int mode = Input.selectMode();
        switch (mode) {
            case 1 -> domino = new Domino();
            case 2 -> domino = new Chilean();
            case 3 -> domino = new Latin();
        }
    }

    private void initTeams() {

        this.team = new Team[2];
        this.players = new Player[4];

        for (int n = 0; n < 2; n++) {
            team[n] = new Team(n + 1);
            players[n] = new Player(n + 1, team[n]);
            players[n + 2] = new Player(n, team[n]);

            team[n].addPlayer(players[n], players[n + 2]);
        }
    }


    private void initPlayers() {
        int numPlayers = Input.setQuantityPlayers();

        this.team = new Team[numPlayers];
        this.players = new Player[numPlayers];

        for (int n = 0; n < players.length; n++) {
            team[n] = new Team(n + 1);
            players[n] = new Player(n + 1, team[n]);
            team[n].addPlayer(players[n]);
        }
    }

    private void clearGameArea() {
        for (Player player : players) {
            player.clearHand();
        }
        deck.clearDeck();
        board.clearBoard();
    }

    private void startGameArea() {
        domino.initTiles(deck);
        deck.shuffleDeck();
        domino.drawTileFromDeck(deck, players);
        board.displayBoard();
    }

    public void initGame() {
        if (domino instanceof Latin)
            initTeams();

        boolean withTeams = Input.playWithTeams();
        if (withTeams)
            initTeams();
        else initPlayers();


        do {
            clearGameArea();
            startGameArea();

            turn = domino.startPlayer(players);

            Tile initialTile = players[turn].putTile(0);
            board.addLast(initialTile);
            System.out.println(board);

            playRound();
            domino.addPoints(players[turn]);

            System.out.println("WINNERS!!!");
            for (Player p : players) { //DISPLAY RESUMEN
                System.out.println(p);
            }

        } while (!domino.isWinner(players[turn]));

        //DISPLAY WINNER
    }

    private void playRound() {

        Tile tempTile;
        int position;
        System.out.println(players[turn].getName() + " STARTED!");
        changeTurn();

        do {
            if (domino.isDeadGame(deck, board, players))
                break; //BUSCAR EL JUGADOR CON MAS PUNTOS

            changeTurn();

            if (!hasTilesToPlay()) {
                changeTurn();
                continue;
            }

            System.out.println(players[turn]);

            tempTile = drawTile();
            position = Input.putPosition();

            if (isValidPlay(tempTile, position))
                players[turn].removeTile(tempTile);
            else tempTile = chooseCorrectTile();

            players[turn].removeTile(tempTile);
            System.out.println(board);
        } while (!domino.isRoundWinner(players[turn]));
    }

    private Tile drawTile() {

        int tiles = (players[turn].getHand().size() - 1);
        int selectedTile;
        Tile tempTile;

        selectedTile = Input.selectTile(tiles);
        tempTile = players[turn].getTile(selectedTile);
        return tempTile;
    }

    private boolean isValidPlay(Tile tempTile, int position) {

        final int FIRST = 1;
        final int LAST = 2;

        if (position == FIRST)
            if (domino.isValidPlay(tempTile, board, position)) {
                board.addFirst(tempTile);
                return true;
            }

        if (position == LAST)
            if (domino.isValidPlay(tempTile, board, position)) {
                board.addLast(tempTile);
                return true;
            }
        return false;
    }

    private Tile chooseCorrectTile() {
        Tile correctTile;
        int position;

        do {
            System.out.println("INCORRECT TILE...");
            correctTile = drawTile();
            position = Input.putPosition();
        } while (!isValidPlay(correctTile, position));

        return correctTile;
    }

    private boolean hasTilesToPlay() { //change name

        while (!domino.hasPlayableTile(players[turn], board)) {
            if (deck.isEmpty()) {
                System.out.println("YOU CANT PLAY ANY TILE... AND DECK IS EMPTY!!! PASS...");
                return false;
            }
            domino.stealTile(players[turn],deck); //conseguir que ficha es
            System.out.println(players[turn].getName() + " +" + 1 + " TILE");
        }
        System.out.println(board);
        return true;
    }

    private void changeTurn() {
        turn++;
        turn = turn % players.length;
    }

}
