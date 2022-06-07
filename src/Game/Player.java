package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return hand.get(idx);
    }

    public Tile putTile(int idx) {
        Tile tile = hand.get(idx);
        hand.remove(idx);
        return tile;
    }

    public void addTile(Tile tile) {
        hand.add(tile);
        hand.sort(Tile::compareTo);
    }

    public void removeTile(Tile tile) {
        hand.remove(tile);
    }

    public boolean isHandEmpty() {
        return this.hand.isEmpty();
    }

    public void clearHand() {
        hand.clear();
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int removePoints() {
        int temp = points;
        points = 0;
        return temp;
    }

    public int getPoints(){
        return this.points;
    }

    public int getMaxTile() {
        hand.sort(Collections.reverseOrder());
        return hand.get(0).getSumTile();
    }

    //Eliminar metode
    public List<Tile> getHand() {
        return this.hand;
    }

    @Override
    public String toString() {
        return "> " + name + " " + hand ;
    }
}
