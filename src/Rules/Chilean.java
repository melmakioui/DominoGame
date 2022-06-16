package Rules;

import Game.Player;

public class Chilean extends Domino{

    protected final int MAX_POINTS = 121;
    protected final int POINTS = 8;

    @Override
    public int getWinnerOfDeadGame(Player[] players) {
        int max = -1;
        int idx = 0;

        for (int i = 0; i < players.length; i++)
            if (max < players[i].getTotalSumTiles()) {
                max = players[i].getTotalSumTiles();
                idx = i;
            }

        addPointsDeadGame(players[idx], players);
        return idx;
    }

    @Override
    public void addPointsDeadGame(Player playerDeadGame, Player[] players) {
        for (Player p: players)
            if (p != playerDeadGame)
                playerDeadGame.addPoints(p.getTotalSumTiles());
    }

    @Override
    public void addPoints(Player playerWinner, Player[] players) {
        playerWinner.addPoints(POINTS);
    }

    @Override
    public boolean isPlayerReachPoints(Player player) {
        return player.getPoints() >= MAX_POINTS;
    }


}
