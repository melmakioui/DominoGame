package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private List<Tile> hand;
    private final String name;
    private Team team;

    public Player(int numPlayer, Team team) {
        this.hand = new ArrayList<>();
        this.name = ("Player " + numPlayer);
        this.team = team;
    }

    public Tile getTile(int idx) {
        return hand.get(idx);
    }

    public void addTile(Tile tile) {
        hand.add(tile);
        hand.sort(Tile::compareTo);
        hand.sort(Collections.reverseOrder());
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
        this.team.addPoints(points);
    }

    public void removePoints(){
        this.team.removePoints();
    }

    public int getPoints(){
        return this.team.getPoints();
    }

    public int getTotalSumTiles(){

        int total = 0;

        for (Tile t: hand)
            total += t.getSumTile();

        return total;
    }

    public List<Tile> getHand() {
        return Collections.unmodifiableList(this.hand);
    }

    public String getName() {
        return name;
    }

    public String getTeamName(){
        return team.getTeamName();
    }

    public boolean containsTile(Tile tile){
       return hand.contains(tile);
    }

    public int getNumTiles() {
        return hand.size() -1;
    }

    @Override
    public String toString() {

        String pl = "";
        int counter = 1;

        for (Tile tile: hand) {
            pl += "\n" + counter + "." + " " + tile;
            counter++;
        }

        return    "\n" + name +  " with " + team.getTeamName() + " " + pl + "\n" +
                team + " points";
    }
}
