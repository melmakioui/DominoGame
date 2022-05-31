import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Player {

    private List<Tile> hand;
    private final String name;
    private int points;

    public Player(int numPlayer) {
        this.hand = new ArrayList<>();
        this.name = ("Player " + numPlayer);
        this.points = 0;
    }


    public Tile getTile(int idx) {
        Tile tile = hand.get(idx);
        hand.remove(idx);
        return tile;
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

    public void displayCurrentTiles(){
        AtomicInteger num = new AtomicInteger();
        System.out.println("Your Current Tiles " + name );
        hand.forEach(tile -> System.out.println( num.incrementAndGet() + " - " + tile));
    }

    @Override
    public String toString() {
        return "> " + name + " " + hand ;
    }
}
