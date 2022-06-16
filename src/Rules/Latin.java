package Rules;

import Game.Player;
import InputOutput.Output;

public class Latin extends Domino {

    protected final int MAX_POINTS = 200;
    private final int CORRIDO = 8;

    @Override
    public int getWinnerOfDeadGame(Player[] players) {

        int teamOneTotalSumTiles = players[0].getTotalSumTilesTeam();
        int teamTwoTotalSumTiles = players[1].getTotalSumTilesTeam();

        if(players[0].getPoints() == 0 || players[1].getPoints() == 0) {
            if (teamOneTotalSumTiles > teamTwoTotalSumTiles)
                return 0;
            else return 1;
        }

        return 1;
    }

    @Override
    public void addPointsDeadGame(Player playerWinner, Player[] players) {
        int points = 0;

        for (Player p: players)
            if (!playerWinner.getTeamName().equals(p.getTeamName()))
                points += p.getTotalSumTiles();

        playerWinner.addPoints(points);
    }

    @Override
    public void addPoints(Player playerWinner, Player[] players) {
        playerWinner.addPoints(POINTS);
    }

    @Override
    public boolean isPlayerReachPoints(Player player) {
        return player.getPoints() >= MAX_POINTS;
    }

    public void addPointsPasoCorrido(Player[] players, int turn) {

        turn = turn - 1;

        if (turn == -1) turn = players.length - 1;

        players[turn].addPoints(CORRIDO);
        Output.displayAddedPointsPasoCorrido(players[turn]);
    }
}
