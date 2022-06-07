package Game;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;
    private List<Player> teamPlayers;
    private int points;

    public Team(int numTeam) {
        this.teamName = "Team " + numTeam;
        this.teamPlayers = new ArrayList<>();
        this.points = 0;
    }

    public void addPlayer(Player player){
        teamPlayers.add(player);
    }

    public void addPoints(int points){
        this.points += points;
    }

    public int getPoints (){
        return points;
    }

    public List<Player> getTeamPlayers(){
        return teamPlayers;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String toString() {
        return teamName  + "has --> " + points;
    }
}
