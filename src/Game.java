
import InputOutput.IO;
import Rules.*;
import Game.*;


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

    private void initTeams() {

        this.team = new Team[2];
        this.players = new Player[4];

        for (int i = 0; i < 2; i++) {
            team[i] = new Team(i + 1);
            players[i] = new Player(i, team[i]);
            players[i + 2] = new Player(i, team[i]);
            team[i].addPlayer(players[i], players[i + 2]);
        }
    }


    private void initPlayers() {
        int numPlayers = IO.setQuantityPlayers();

        this.team = new Team[numPlayers];
        this.players = new Player[numPlayers];

        for (int i = 0; i < players.length; i++) {
            team[i] = new Team(i + 1);
            players[i] = new Player(i, team[i]);
            team[i].addPlayer(players[i]);
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
        rules.initTiles(deck);
        rules.drawTileFromDeck(deck, players);
        board.displayBoard();
    }

    public void initGame() {
        if (rules instanceof Latin) {
            initTeams();
        } else {
            boolean withTeams = IO.playWithTeams();
            if (withTeams)
                initTeams();
            else initPlayers();
        }

        do {
            clearGameArea();
            startGameArea();

            turn = rules.startPlayer(players);

            Tile initialTile = players[turn].putTile(0);
            board.addLast(initialTile);
            System.out.println(board);
            playRound();
            rules.addPoints(players[turn]);

            for (int i = 0; i < team.length; i++)
                System.out.println(team);

        } while (!rules.isWinner(players[turn]));

        //DISPLAY WINNER
    }

    private void playRound() {

        Tile tempTile;
        int position;
        System.out.println(players[turn].getName() + " STARTED!");
        changeTurn();

        do {
            changeTurn();
            System.out.println(players[turn]);
            if (!hasTilesToPlay()) {
                changeTurn();
                continue;
            }

            tempTile = drawTile();
            position = IO.putPosition();

            if (isValidPlay(tempTile, position))
                players[turn].removeTile(tempTile);
            else tempTile = chooseCorrectTile();

            players[turn].removeTile(tempTile);
            System.out.println(board);
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
        Tile correctTile;
        int position;

        do {
            System.out.println("INCORRECT TILE...");
            correctTile = drawTile();
            position = IO.putPosition();
        } while (!isValidPlay(correctTile, position));

        return correctTile;
    }

    private boolean hasTilesToPlay() { //change name
        Tile stealedTile;

        while (!rules.canPlayTile(players[turn], board)) {
            if (deck.isEmpty()){
                System.out.println("YOU CANT PLAY TILE... AND DECK IS EMPTY!!! PASS...");
                return false;
            }
            stealedTile = deck.getDominoTile();
            System.out.println(players[turn].getName() + " +" + 1 + "TILE" + players[turn].getName());
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
