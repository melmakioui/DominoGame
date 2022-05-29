import java.util.Scanner;

public class test {


    public static void main(String[] args) {

        DeckTiles deckTiles = new DeckTiles();
        Board board = new Board();
        Domino d = new Domino(deckTiles);
        Player p = new Player(1);

        d.drawTileFromDeck(p, 7);
        Tile x = d.getInitTile(p);
        board.addLast(x);
        board.displayBoard();

        p.displayCurrentTiles();

        System.out.println(d.isValidPlay(p.getTile(4),board));


    }
}
