package Rules;

import Game.Board;
import Game.DeckTiles;
import Game.Player;
import Game.Tile;

public class Chilean extends Domino{

    protected final int MAX_POINTS = 121;

    @Override
    public void initTiles(DeckTiles deckTiles) {
        super.initTiles(deckTiles);
    }

    @Override
    public void grabTileFromDeck(DeckTiles deckTiles, Player[] players) {
        super.grabTileFromDeck(deckTiles, players);
    }

    @Override
    public void stealTile(Player player, DeckTiles deckTiles) {
        super.stealTile(player, deckTiles);
    }

    @Override
    public Tile starterTile(Player[] player) {
        return super.starterTile(player);
    }

    @Override
    public boolean hasPlayableTile(Player player, Board board) {
        return super.hasPlayableTile(player, board);
    }

    @Override
    public boolean isPlayableTile(Tile tile, Board board) {
        return super.isPlayableTile(tile, board);
    }

    @Override
    public boolean isValidPlay(Tile tile, Board board, int position) {
        return super.isValidPlay(tile, board, position);
    }

    @Override
    public boolean isDeadGame(DeckTiles deckTiles, Board board, Player[] players) {
        return super.isDeadGame(deckTiles, board, players);
    }

    @Override
    public int getWinnerOfDeadGame(Player[] players) {
        int max = -1;
        int idx = 0;

        for (int i = 0; i < players.length; i++)
            if (max < players[i].getPoints()) {
                max = players[i].getPoints();
                idx = i;
            }

        addPointsDeadGame(players[idx],players);
        return idx;
    }


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
    public boolean isWinner(Player player) {
        return player.getPoints() >= MAX_POINTS;
    }

    public boolean isPlayerPass(Player player) {

    }
}
