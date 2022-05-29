import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Tile> hand;
    private final String name;
    private int points;

    public Player(int numPlayer) {
        this.hand = new ArrayList<>();
        this.name = ("Player " + numPlayer + 1);
        this.points = 0;
    }


    public Tile getTile(int idx) {
        return hand.get(idx);
    }

    public void addTile(Tile tile) {
        hand.add(tile);
    }

    public void removeTile(Tile tile) {
        hand.remove(tile);
    }

    public void clearHand() {
        hand.clear();
    }

    public boolean isHandEmpty() {
        return this.hand.isEmpty();
    }

    public void addPoints(int points) {
        this.points += points;
    }

    //Nos devuelve la ficha mas alta
    public Tile getMaxTile() {

        int max = 0;
        Tile tile = null;

        for (Tile value : hand) {
            if (max < value.getSumTile()) {
                max = value.getSumTile();
                tile = value;
            }
        }
        return tile;
    }

    public List<Tile> getHand() {
        return this.hand;
    }


    @Override
    public String toString() {
        return name + "Tiles --> " + hand;
    }
}
