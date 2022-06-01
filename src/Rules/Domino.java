package Rules;

import Game.Board;
import Game.DeckTiles;
import Game.Player;
import Game.Tile;

public class Domino {

    private final int MAX_POINTS = 80;
    private final int QUANTITY_TO_DEAL = 7;
    private final int MIN_PLAYERS = 2;
    private final int MAX_PLAYERS = 4;

    public Domino() {


    }

    public void initTiles(DeckTiles deckTiles) { //rule
        for (int i = 6; i >= 0; i--)
            for (int j = 0; j <= i; j++)
                deckTiles.addTile(new Tile(i, j));
    }

    public void drawTileFromDeck(DeckTiles deckTiles, Player... players) { //rule

        for (int i = 0; i < players.length; i++)
            for (int j = 0; j < QUANTITY_TO_DEAL; j++)
                players[i].addTile(deckTiles.getDominoTile());
    }

    public void stealTile(Player player, DeckTiles deckTiles) {
        if (deckTiles.isEmpty())
            player.addTile(deckTiles.getDominoTile());
    }

    public Tile getInitTile(Player... player) {

        int max = 0;
        int idx = 0;
        Tile maxTile;

        for (int i = 0; i < player.length; i++)
            if (max < player[i].getMaxTile().getSumTile()) {
                max = player[i].getMaxTile().getSumTile();
                idx = i;
            }

        player[idx].getMaxTile();
        maxTile = player[idx].getMaxTile();
        player[idx].removeTile(maxTile);
        /**Indicar que jugador a sacado la ficha mas alta*/
        return maxTile;
    }


    public boolean canPlay(Player player, Board board) {

        for (Tile tile : player.getHand()) {
            if (tile.getRightNum() == board.getFirst().getLeftNum() || tile.getLeftNum() == board.getFirst().getLeftNum()
                    || tile.getRightNum() == board.getLast().getRightNum() || tile.getLeftNum() == board.getLast().getLeftNum())
                return true;
        }
        return false;
    }


    public boolean isValidPlay(Tile tile, Board board) { //Rule

        return tile.getLeftNum() == board.getFirst().getLeftNum() ||
                tile.getRightNum() == board.getFirst().getRightNum() ||
                tile.getLeftNum() == board.getLast().getLeftNum() ||
                tile.getRightNum() == board.getLast().getRightNum();
    }

    public void addPoints(Player player) { //solo si es ganador

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


    public boolean isRoundWinner(Player player) {
        return player.isHandEmpty();
    }

    public boolean isWinner(Player player) { //Rule
        return player.getPoints() == MAX_POINTS;
    }


}
