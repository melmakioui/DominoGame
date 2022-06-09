package Rules;
import Game.*;
public interface Rules {


    void initTiles(DeckTiles deckTiles);

    void drawTileFromDeck(DeckTiles deckTiles, Player... players);

    void stealTile(Player player, DeckTiles deckTiles);

    int startPlayer(Player...player);

    boolean canPlay(Player player, Board board);

    boolean isValidPlay (Tile tile, Board board, int pos);

    void addPoints(Player player, Player...players);

    boolean isRoundWinner(Player player);

    boolean isWinner(Player player);



}
