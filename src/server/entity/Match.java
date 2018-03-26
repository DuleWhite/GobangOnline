package server.entity;

import server.network.SendMessage;

public class Match {
    private int matchId;
    private Player player1 = null;
    private Player player2 = null;

    public Match(Player player) {
        this.player1 = player;
        this.matchId = this.hashCode();
    }

    public Player getPlayer() {
        return player1;
    }
    public Player getPlayer2(){
        return player2;
    }

    public Player getOppo(String playerId){
        if(player1.getPlayerId() == Integer.parseInt(playerId)){
            return player2;
        }
        else return player1;
    }

    public boolean addPlayer(Player player) {
        if (player2 == null) {
            player2 = player;
            SendMessage.newChallenger(player1,player2);
            return true;
        }
        return false;
    }

    public void removePlayer(int playerId){
        if(player1.getPlayerId() == playerId){
            player1 = null;
            player1 = player2;
            player2 = null;
        }
        else player2 = null;

    }

    public void swapPlayer(){
        Player temp = player1;
        player1 = player2;
        player2 = temp;
    }

    public int getMatchId() {
        return matchId;
    }

    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append(getMatchId());
        temp.append(":");
        if (player1 != null) {
            temp.append(player1.getNickname());
        } else temp.append("                 ");
        temp.append("---");
        if (player2 != null) {
            temp.append(player2.getNickname());
        } else temp.append("                 ");
        return temp.toString();
    }

    public String toString2(){
        return getMatchId() +
                "-" +
                player1.getNickname() +
                ":" +
                player1.getPlayerId() +
                "-" +
                player2.getNickname() +
                ":" +
                player2.getPlayerId();
    }
}
