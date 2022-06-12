package Rules;

import Game.*;

public interface DominoRules {


    void initTiles(DeckTiles deckTiles);

    void drawTileFromDeck(DeckTiles deckTiles, Player... players);

    void stealTile(Player player, DeckTiles deckTiles);

    int startPlayer(Player... player);

    boolean hasPlayableTile(Player player, Board board);

    boolean isValidPlay(Tile tile, Board board, int pos);

    void addPoints(Player player, Player... players);

    boolean isRoundWinner(Player player);

    boolean isWinner(Player player);

    boolean isDeadGame(DeckTiles deckTiles, Board board, Player... players);


}
