import java.util.List;
import java.util.stream.Collectors;

public class Domino {


    private final int dealingTiles = 7;
    private final int stealTile = 1;

    private DeckTiles deckTiles;

    public Domino(DeckTiles deckTiles){
        this.deckTiles = new DeckTiles();
        this.deckTiles.shuffleDeck();
    }

    public void initTiles(){
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < i ; j++) {
                deckTiles.addTile(new Tile(i,j));
            }
        }
    }

    public void drawTileFromDeck(Player player, int quantity){
        for (int i = 0; i < quantity; i++) {
            player.addTile(deckTiles.getDominoTile());
        }
    }

    public Tile getLargerTile(Player...player) {

        int max = 0;
        int idx = 0;
        Tile maxTile;

        for (int i = 0; i < player.length; i++) {
            if (max < player[i].getMaxTile().getSumTile()) {
                max = player[i].getMaxTile().getSumTile();
                idx = i;
            }
        }

        maxTile = player[idx].getMaxTile();
        player[idx].removeTile(maxTile);

        return maxTile;
    }


    public boolean isWinner(Player player){
        return player.isHandEmpty();
    }


}
