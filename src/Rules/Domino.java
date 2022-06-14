package Rules;

import Game.Board;
import Game.DeckTiles;
import Game.Player;
import Game.Tile;
import InputOutput.Output;

public class Domino implements DominoRules {

    protected final int MAX_POINTS = 100;
    protected final int POINTS = 12;
    protected final int QUANTITY_TO_DEAL = 7;

    @Override
    public void initTiles(DeckTiles deckTiles) {
        for (int i = 6; i >= 0; i--)
            for (int j = 0; j <= i; j++)
                deckTiles.addTile(new Tile(i, j));
    }

    @Override
    public void drawTileFromDeck(DeckTiles deckTiles, Player... players) {

        deckTiles.shuffleDeck();

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
    public Tile starterTile(Player... player) {

        Tile max = null;
        int min = -1;

        for (Player p : player)
            for (Tile t : p.getHand())
                if (t.getLeftNum() == t.getRightNum())
                    if (min < t.getSumTile()) {
                        max = t;
                        min = t.getSumTile();
                    }

        return max;
    }

    @Override
    public int starterPlayer(Tile tile, Board board, Player... players) {

        for (int i = 0; i < players.length; i++)
            if (players[i].containsTile(tile)) {
                board.addLast(tile);
                players[i].removeTile(tile);
                Output.displayBoard(board);
                return i;
            }
        return 0;
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
    public boolean isValidPlay(Tile tile, Board board, int position) { //Rule --> LEFT | RIGHT

        int firstLeftNumBoard = board.getFirst().getLeftNum();
        int lastRightNumBoard = board.getLast().getRightNum();
        int leftNumTile = tile.getLeftNum();
        int rightNumTile = tile.getRightNum();

        switch (position) {
            case 1:
                if (firstLeftNumBoard == rightNumTile)
                    return true;
                else if (firstLeftNumBoard == leftNumTile) {
                    tile.reverseTile();
                    return true;
                }
                break;
            case 2:
                if (lastRightNumBoard == leftNumTile)
                    return true;
                else if (lastRightNumBoard == rightNumTile) {
                    tile.reverseTile();
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean isDeadGame(DeckTiles deckTiles, Board board, Player... players) {

        if (deckTiles.isEmpty()) {
            for (Player p : players)
                if (hasPlayableTile(p, board))
                    return false;
        } else

            for (Tile t : deckTiles.getDeckTiles())
                if (isPlayableTile(t, board))
                    return false;

        return true;
    }

    @Override
    public boolean isPlayableTile(Tile tile, Board board) {

        int first = board.getFirst().getLeftNum();
        int last = board.getLast().getRightNum();

        return tile.getLeftNum() == last
                || tile.getRightNum() == first
                || tile.getLeftNum() == first
                || tile.getRightNum() == last;
    }

    @Override
    public int getWinnerOfDeadGame(Player... players) {

        int min = 0;
        int idx = 0;

        for (int i = 0; i < players.length; i++)
            if (min < players[i].getTotalSumTiles()) {
                min = players[i].getTotalSumTiles();
                idx = i;
            }
        return idx;
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
