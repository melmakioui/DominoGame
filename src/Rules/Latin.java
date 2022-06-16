package Rules;

import Game.Board;
import Game.DeckTiles;
import Game.Player;
import Game.Tile;
import InputOutput.Output;

public class Latin extends Domino {

    protected final int MAX_POINTS = 200;
    private final int POINTS_PASO_CORRIDO = 8;

    @Override
    public void initTiles(DeckTiles deckTiles) {
        super.initTiles(deckTiles);
    }

    @Override
    public void grabTileFromDeck(DeckTiles deckTiles, Player... players) {
        super.grabTileFromDeck(deckTiles, players);
    }

    @Override
    public void stealTile(Player player, DeckTiles deckTiles) {
        super.stealTile(player, deckTiles);
    }

    @Override
    public Tile starterTile(Player... player) {
        return super.starterTile(player);
    }

    @Override
    public int starterPlayer(Tile tile, Board board, Player... players) {
        return super.starterPlayer(tile, board, players);
    }

    @Override
    public boolean hasPlayableTile(Player player, Board board) {
        return super.hasPlayableTile(player, board);
    }

    @Override
    public boolean isValidPlay(Tile tile, Board board, int position) {
        return super.isValidPlay(tile, board, position);
    }

    @Override
    public boolean isDeadGame(DeckTiles deckTiles, Board board, Player... players) {
        return super.isDeadGame(deckTiles, board, players);
    }

    @Override
    public boolean isPlayableTile(Tile tile, Board board) {
        return super.isPlayableTile(tile, board);
    }

    @Override
    public int getWinnerOfDeadGame(Player... players) { //LA PAREJA QUE TENGA MENOS PUNTOS SERA LA QUE SE SALVA DE SUMAR PUNTOS
        return super.getWinnerOfDeadGame(players);
    }

    @Override
    public boolean isPointsGreaterThanPlayers(Player playerWinner, Player[] players) {
        return super.isPointsGreaterThanPlayers(playerWinner, players);
    }

    @Override
    public void addPointsDeadGame(Player playerWinner, Player[] players) {
        super.addPointsDeadGame(playerWinner, players);
    }

    @Override
    public boolean isRoundWinner(Player player) {
        return super.isRoundWinner(player);
    }

    @Override
    public boolean isPlayerReachPoints(Player player) {
        return player.getPoints() >= MAX_POINTS;
    }

    public void addPointsPasoCorrido(Player[] players, int turn) {

        turn = turn - 1;

        if (turn == -1) turn = players.length - 1;

        players[turn].addPoints(POINTS_PASO_CORRIDO);
        Output.displayAddedPointsPasoCorrido(players[turn]);

    }
}
