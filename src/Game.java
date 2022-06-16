
import InputOutput.Input;
import InputOutput.Output;
import Rules.*;
import Game.*;


public class Game {

    private Team team[];
    private Player[] players;
    private Board board;
    private DominoRules domino;
    private DeckTiles deck;
    private static int turn = 0;
    private static boolean isDeadGame = true;

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
            players[n + 2] = new Player(n + 1, team[n]);

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

    private void startGameArea() {
        for (Player player : players) {
            player.clearHand();
        }
        deck.clearDeck();
        board.clearBoard();

        domino.initTiles(deck);
        domino.grabTileFromDeck(deck, players);

        Tile startTile = domino.starterTile(players);
        turn = domino.starterPlayer(startTile, board, players);
        Output.displayStartedPlayer(players[turn]);
    }

    public void initGame() {

        if (domino instanceof Latin) {
            initTeams();
        } else {
            boolean withTeams = Input.playWithTeams();
            if (withTeams)
                initTeams();
            else initPlayers();
        }

        do {
            startGameArea();
            playRound();

            if (!isDeadGame)
                domino.addPointsDeadGame(players[turn],players);
            else domino.addPoints(players[turn],players);

            Output.displaySummary(players[turn], players);
            isDeadGame = true;
        } while (!domino.isPlayerReachPoints(players[turn]));

        Output.displayWinner(domino, players[turn]);
    }

    private void playRound() {

        do {
            changeTurn();
            if (domino.isDeadGame(deck, board, players)) {
                turn = domino.getWinnerOfDeadGame(players);
                isDeadGame = false;
                Output.displayWinnerDeadGame(players[turn], domino);
                break;
            }

            if (!hasTilesToPlay()) {
                if (domino instanceof Latin)
                    ((Latin) domino).addPointsPasoCorrido(players, turn);
                continue;
            }

            System.out.println(players[turn]);
            placeTile();
            System.out.println(board);
        } while (domino.isRoundFinished(players[turn]));
    }

    private void placeTile() {

        Tile tempTile;
        int position;

        tempTile = grabTileFromPlayer();
        position = Input.putPosition();

        if (isValidPlay(tempTile, position))
            players[turn].removeTile(tempTile);
        else tempTile = chooseCorrectTile();
        players[turn].removeTile(tempTile);
    }

    private Tile grabTileFromPlayer() {

        int availableTiles = (players[turn].getNumTiles());
        int selectedTile;
        Tile tempTile;

        selectedTile = Input.selectTile(availableTiles);
        tempTile = players[turn].getTile(selectedTile);
        return tempTile;
    }

    private Tile chooseCorrectTile() {
        Tile correctTile;
        int position;

        do {
            Output.dislayIncorrectTile();
            correctTile = grabTileFromPlayer();
            position = Input.putPosition();
        } while (!isValidPlay(correctTile, position));

        return correctTile;
    }

    private boolean isValidPlay(Tile tempTile, int position) {

        switch (position) {
            case 1 -> {
                if (domino.isValidPlay(tempTile, board, position)) {
                    board.addFirst(tempTile);
                    return true;
                }
            }
            case 2 -> {
                if (domino.isValidPlay(tempTile, board, position)) {
                    board.addLast(tempTile);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasTilesToPlay() {

        while (!domino.hasPlayableTile(players[turn], board)) {

            if (deck.isEmpty()) {
                Output.displayDeckEmpty(players[turn]);
                return false;
            }

            domino.stealTile(players[turn], deck);
            Output.displayAddedTile(players[turn]);
        }
        return true;
    }

    private void changeTurn() {
        turn++;
        turn = turn % players.length;
    }

}
