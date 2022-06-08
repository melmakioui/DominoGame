package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private List<Tile> hand;
    private final String name;
    private int points;
    private Team team;

    public Player(int numPlayer) {
        this.hand = new ArrayList<>();
        this.name = ("PLAYER " + numPlayer);
        this.points = 0;
    }

    public Player(int numPlayer, Team team) {
        this.hand = new ArrayList<>();
        this.name = ("Player " + numPlayer);
        this.points = 0;
        this.team = new Team();
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

    public List<Tile> getHand() {
        return this.hand;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {

        String pl = "";
        int counter = 1;

        for (Tile tile: hand) {
            pl += "\n" + counter + "." + " " + tile;
            counter++;
        }

        return   "\n" + "YOUR TURN" + name + "" + pl + "\n" +
                "Current points -> " + points;
    }
}
