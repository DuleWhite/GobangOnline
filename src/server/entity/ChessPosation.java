package server.entity;

public class ChessPosation {
    private int chessType;
    private Integer chessX;
    private Integer chessY;

    public ChessPosation(int chessType, Integer chessX, Integer chessY) {
        this.chessType = chessType;
        this.chessX = chessX;
        this.chessY = chessY;
    }

    public Integer getChessX() {
        return chessX;
    }

    public Integer getChessY() {
        return chessY;
    }

    @Override
    public String toString() {
        return chessX + "," + chessY;
    }
}
