package Game;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Tile> board;

    public Board(){
        this.board = new ArrayList<>();
    }

    public void addFirst(Tile tile){
        board.add(0,tile);
    }

    public void addLast(Tile tile) {
        board.add(tile);
    }

    public Tile getFirst(){
        return board.get(0);
    }

    public Tile getLast(){
        return board.get( board.size() -1 );
    }

    public void clearBoard(){
        board.clear();
    }

    public void displayBoard(){
        for (Tile tile: board) {
            System.out.println(tile);
        }
    }

    @Override
    public String toString() {

        int counter = 0;
        String b = "";

        for (Tile tile: board ) {
            b += tile;
        }
        return "\n" + "BOARD: \n" +
                b;
    }
}
