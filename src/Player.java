import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Tile> hand;
    private String name;
    private int points;

    public Player(String name){
        this.hand = new ArrayList<>();
        this.name = name;
    }

    public void addTile(Tile tile){
        hand.add(tile);
    }

    public void removeTile(Tile tile) {
        hand.remove(tile);
    }

    public void removeAllTiles(){
        hand.clear();
    }

    public boolean isHandEmpty(){
        return this.hand.isEmpty();
    }

    public void addPoints(int points){
        this.points += points;
    }


    @Override
    public String toString() {
        return name + "Tiles --> " + hand;
    }
}
