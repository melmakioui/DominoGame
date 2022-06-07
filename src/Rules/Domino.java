package Rules;

import Game.Board;
import Game.DeckTiles;
import Game.Player;
import Game.Tile;

public class Domino implements Rules {

    protected final int MAX_POINTS = 100;
    protected final int POINTS = 12;
    protected final int QUANTITY_TO_DEAL = 7;
    protected final int MIN_PLAYERS = 2;
    protected final int MAX_PLAYERS = 4;

    /**EL PRIMERO EN LLEGAR A UN PUNTO DETERMINADO DE PUNTOS gana*
     * CHILENO EL QUE LLEGUE A UN PUNTO DETERMINADO pierde LA PARTIDA
     * LATINO
     */
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
        if (deckTiles.isEmpty())
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
    public boolean canPlay(Player player,Board board) {

        int first = board.getFirst().getLeftNum();
        int last = board.getLast().getRightNum();

        for (Tile tile : player.getHand())
            if (tile.getLeftNum() == last
               || tile.getRightNum() == first)
                return true;

        return false;
    }

    @Override
    public boolean isValidPlay(Tile tile, Board board) { //Rule

        if (tile.getLeftNum() == board.getFirst().getLeftNum()
           || tile.getRightNum() == board.getLast().getRightNum()) {
            tile.reverseTile();
            return true;
        }

        return tile.getRightNum() == board.getFirst().getLeftNum()
                || tile.getLeftNum() == board.getLast().getRightNum();
    }

    @Override
    public void addPoints(Player player, Player... players) { //solo si es ganador

        player.addPoints(POINTS);
        /**SI JUGADOR = GANA
         * SUMA PUNTOS DE ADVERSARIOS Y/O PAREJA
         * SOLO SI SUS PUNTOS ES MAYOR QUE LOS ADVERSARIOS
         * */

        /** Mirar SI LOS PUNTOS DE X JUGADOR ES MAS GRANDE QUE
         * OTROS JUGADORES,
         * IF TRUE --> ROBAR LOS PUNTOS DE LOS DEMAS,
         * IF FALSE --> AGREGAR PUNTOS CORRESPONDIENTES Y CONTINUA PARTIDA
         */
    }

    @Override
    public boolean isRoundWinner(Player player) {
        return player.isHandEmpty();
    }

    @Override
    public boolean isWinner(Player player) { //Rule
        return player.getPoints() == MAX_POINTS;
    }


    public int getMAX_PLAYERS() {
        return MAX_PLAYERS;
    }

    public int getMIN_PLAYERS() {
        return MIN_PLAYERS;
    }
}
