public class Domino {


    private final int dealingTiles = 7;
    private final int stealTile = 1;

    private DeckTiles deckTiles;

    public Domino(DeckTiles deckTiles){
        this.deckTiles = new DeckTiles();
        this.deckTiles.shuffleDeck();
        initTiles();

    }

    public void initTiles(){ //rule
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j <= i ; j++) {
                deckTiles.addTile(new Tile(i,j));
            }
        }
    }

    public void drawTileFromDeck(Player player, int quantity){ //rule
        for (int i = 0; i < quantity; i++) {
            player.addTile(deckTiles.getDominoTile());
        }
    }

    public Tile getInitTile(Player...player) {

        int max = 0;
        int idx = 0;
        Tile maxTile;

        for (int i = 0; i < player.length; i++) {
            if (max < player[i].getMaxTile().getSumTile()) {
                max = player[i].getMaxTile().getSumTile();
                idx = i;
            }
        }

        boolean firstTile = true; //para indicar que se imprima horizontal
        player[idx].getMaxTile().setInitialTile(firstTile);

        maxTile = player[idx].getMaxTile();
        player[idx].removeTile(maxTile);

        return maxTile;
    }

    //Probar metodo OK
    public boolean canPlay (Player player, Board board) {

        for (Tile tile: player.getHand()) {
            if (tile.getRightNum() == board.getFirst().getLeftNum() || tile.getLeftNum() == board.getFirst().getLeftNum()
            || tile.getRightNum() == board.getLast().getRightNum()|| tile.getLeftNum() == board.getLast().getLeftNum())
                return true;
        }
        return false;
    }


    public boolean isValidPlay (Tile tile, Board board) { //Rule

        return tile.getLeftNum() == board.getFirst().getLeftNum() ||
        tile.getRightNum() == board.getFirst().getRightNum() ||
        tile.getLeftNum() == board.getLast().getLeftNum() ||
        tile.getRightNum() == board.getLast().getRightNum();
    }

    public boolean isWinner(Player player){ //Rule
        return player.isHandEmpty();
    }


}
