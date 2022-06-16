package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {

    private String teamName;
    private int numTeam;
    private List<Player> teamPlayers;
    private int points;

    public Team(int numTeam) {
        this.numTeam = numTeam;
        this.teamName = "Team " + numTeam;
        this.teamPlayers = new ArrayList<>();
        this.points = 0;
    }

    public void addPlayer(Player... players) {
        teamPlayers.addAll(Arrays.asList(players));
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void removePoints() {
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTotalSumTilesTeam() {

        int total = 0;

        for (Player p : teamPlayers)
            total += p.getTotalSumTiles();

        return total;
    }

    @Override
    public String toString() {
        return teamName + " has --> " + points;
    }
}
