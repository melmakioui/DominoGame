package Rules;

import Game.*;

public interface DominoRules {


    void initTiles(DeckTiles deckTiles);

    void grabTileFromDeck(DeckTiles deckTiles, Player... players);

    void stealTile(Player player, DeckTiles deckTiles);

    Tile starterTile(Player... player);

    int starterPlayer(Tile tile, Board board, Player... players);

    boolean hasPlayableTile(Player player, Board board);

    boolean isValidPlay(Tile tile, Board board, int pos);

    void addPointsDeadGame (Player player, Player[] players);

    void addPoints(Player player, Player[] players);

    boolean isPlayableTile(Tile tile, Board board);

    int getWinnerOfDeadGame(Player... players);

    boolean isRoundFinished(Player player);

    boolean isPlayerReachPoints(Player player);

    boolean isDeadGame(DeckTiles deckTiles, Board board, Player... players);


}
