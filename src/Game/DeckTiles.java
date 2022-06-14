package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckTiles {

    private List<Tile> deckTiles;

    public DeckTiles(){
        deckTiles = new ArrayList<>();
    }

    public void addTile(Tile tile){
        deckTiles.add(tile);
    }

    public void clearDeck(){
        deckTiles.clear();
    }

    public Tile getDominoTile(){
        Random random = new Random();

        int randomPos = random.nextInt(deckTiles.size());
        Tile randomTile = deckTiles.get(randomPos);
        deckTiles.remove(randomTile);

        return randomTile;
    }

    public boolean isEmpty(){
        return deckTiles.isEmpty();
    }

    public void shuffleDeck(){
        Collections.shuffle(deckTiles);
    }

    public List<Tile> getDeckTiles() {
        return Collections.unmodifiableList(this.deckTiles);
    }
}
