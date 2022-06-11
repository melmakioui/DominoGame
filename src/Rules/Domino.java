package Rules;

import Game.Board;
import Game.DeckTiles;
import Game.Player;
import Game.Tile;

public class Domino implements Rules {

    protected final int MAX_POINTS = 12;
    protected final int POINTS = 12;
    protected final int QUANTITY_TO_DEAL = 7;

    public Domino() {


    }

    @Override
    public void initTiles(DeckTiles deckTiles) { //rule
        for (int i = 6; i >= 0; i--)
            for (int j = 0; j <= i; j++)
                deckTiles.addTile(new Tile(i, j));
    }

    @Override
    public void drawTileFromDeck(DeckTiles deckTiles, Player... players) { //rule
        for (int i = 0; i < players.length; i++)
            for (int j = 0; j < QUANTITY_TO_DEAL; j++)
                players[i].addTile(deckTiles.getDominoTile());
    }

    @Override
    public void stealTile(Player player, DeckTiles deckTiles) {
        if (!deckTiles.isEmpty())
            player.addTile(deckTiles.getDominoTile());
    }

    @Override
    public int startPlayer(Player... player) {

        int max = 0;
        int idx = 0;

        for (int i = 0; i < player.length; i++)
            if (max < player[i].getMaxTile()) {
                max = player[i].getMaxTile();
                idx = i;
            }

        return idx;
    }

    @Override
    public boolean hasPlayableTile(Player player, Board board) {

        int first = board.getFirst().getLeftNum();
        int last = board.getLast().getRightNum();

        for (Tile tile : player.getHand())
            if (tile.getLeftNum() == last
                    || tile.getRightNum() == first
                    || tile.getLeftNum() == first
                    || tile.getRightNum() == last)
                return true;

        return false;
    }

    @Override
    public boolean hasPlayableTile(Tile tile, Board board) {

        int first = board.getFirst().getLeftNum();
        int last = board.getLast().getRightNum();

        return tile.getLeftNum() == last
                || tile.getRightNum() == first
                || tile.getLeftNum() == first
                || tile.getRightNum() == last;
    }


    @Override
    public boolean isValidPlay(Tile tile, Board board, int position) { //Rule --> LEFT | RIGHT

        int FIRST = 1;
        int LAST = 2;
        int firstLeftNumBoard = board.getFirst().getLeftNum();
        int lastRightNumBoard = board.getLast().getRightNum();
        int leftNumTile = tile.getLeftNum();
        int rightNumTile = tile.getRightNum();

        if (position == FIRST)
            if (firstLeftNumBoard == rightNumTile)
                return true;
            else if (firstLeftNumBoard == leftNumTile) {
                tile.reverseTile();
                return true;
            }

        if (position == LAST)
            if (lastRightNumBoard == leftNumTile)
                return true;
            else if (lastRightNumBoard == rightNumTile) {
                tile.reverseTile();
                return true;
            }

        return false;
    }

    @Override
    public boolean isDeadGame(DeckTiles deckTiles, Board board, Player... players) {

        if (deckTiles.isEmpty()) { //SI POZO ESTA VACIO Y NADIE TIENE PARA JUGAR >END<
            for (Player p : players)
                if (hasPlayableTile(p, board))
                    return false; //PROCEDER A CALCULAR QUIEN ES EL QUE TIENE MAS PUNTOS
        } else
            for (Tile t : deckTiles.getDeckTiles())
                if (hasPlayableTile(t, board))
                    return false;

        for (Player p : players)
            if (hasPlayableTile(p, board))
                return false;

        return true;

    }

    @Override
    public void addPoints(Player player, Player... players) { //solo si es ganador

        player.addPoints(POINTS);
        int max = player.getPoints();
        int counter = players.length - 1;

        for (Player p : players)
            if (player != p)
                if (max > player.getPoints())
                    counter--;

        if (counter == 0)
            for (Player p : players)
                if (player != p)
                    player.addPoints(p.getPoints());
    }

    @Override
    public boolean isRoundWinner(Player player) {
        return player.isHandEmpty();
    }

    @Override
    public boolean isWinner(Player player) { //Rule
        return player.getPoints() >= MAX_POINTS;
    }

}
