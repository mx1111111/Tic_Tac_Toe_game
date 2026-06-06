package model;

public class Score {
    private String gameMode;
    private int playerXScore;
    private int playerOScore;

    public Score() {
        this.playerXScore = 0;
        this.playerOScore = 0;
    }

    public Score(String gameMode, int playerXScore, int playerOScore) {
        this.gameMode = gameMode;
        this.playerXScore = playerXScore;
        this.playerOScore = playerOScore;
    }

    public String getGameMode() { return gameMode; }
    public void setGameMode(String gameMode) { this.gameMode = gameMode; }
    public int getPlayerXScore() { return playerXScore; }
    public void setPlayerXScore(int playerXScore) { this.playerXScore = playerXScore; }
    public int getPlayerOScore() { return playerOScore; }
    public void setPlayerOScore(int playerOScore) { this.playerOScore = playerOScore; }
}
