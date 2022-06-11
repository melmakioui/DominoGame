package Rules;

import Game.Board;
import Game.DeckTiles;
import Game.Player;

public class Chilean extends Domino{

    protected final int MAX_POINTS = 121;

    @Override
    public void initTiles(DeckTiles deckTiles) {
        super.initTiles(deckTiles);
    }

    @Override
    public void drawTileFromDeck(DeckTiles deckTiles, Player... players) {
        super.drawTileFromDeck(deckTiles, players);
    }


    @Override
    public void stealTile(Player player, DeckTiles deckTiles) {
        super.stealTile(player, deckTiles);
    }

    @Override
    public boolean hasPlayableTile(Player player, Board board) {
        return super.hasPlayableTile(player, board);
    }


/*    public boolean isValidPlay(Tile tile, Board board, int position) {
        return super.isValidPlay(tile, board,position);
    }*/

    @Override
    public void addPoints(Player player, Player... players) {
        super.addPoints(player, players);
    }

    @Override
    public boolean isRoundWinner(Player player) {
        return super.isRoundWinner(player);
    }

    @Override
    public boolean isWinner(Player player) {
        return super.isWinner(player);
    }
}
