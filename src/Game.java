public class Game {

    private int numPlayers;
    private Player[] players;
    private Board board;
    private Domino domino;

    //deck tiles - domino

    public Game() {
        this.numPlayers = 4; // IO
        this.players = new Player[numPlayers];
        this.board = new Board();
        this.domino = new Domino();

        initPlayers();
        initGame();
    }

    public void initPlayers() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i + 1);
        }
    }

    public void initGame(){

        for (int i = 0; i < players.length ; i++) {
            domino.drawTileFromDeck(players[i],7);
        }

        Tile t = domino.getInitTile(players);
        board.addLast(t);
        board.displayBoard();


        System.out.println(players[0]);
        System.out.println(players[1]);
        System.out.println(players[2]);
        System.out.println(players[3]);

        players[3].getTile(2);

        System.out.println();
        System.out.println(players[0]);
        System.out.println(players[1]);
        System.out.println(players[2]);
        System.out.println(players[3]);

    }



}
