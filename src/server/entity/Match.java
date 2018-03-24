package server.entity;

public class Match {
    private int matchId;
    private Player player1 = null;
    private Player player2 = null;

    public Match() {
        this.matchId = this.hashCode();
    }

    public void addPlayer(Player player) {
        if (player1 == null) {
            player1 = player;
        } else if (player2 == null) {
            player2 = player;
        }
    }

    public int getMatchId() {
        return matchId;
    }

    public boolean isFull() {
        return player1 == null || player2 == null;
    }

    @Override
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
}
