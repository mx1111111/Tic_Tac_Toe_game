package model;

public class GameModel {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;
    private String gameMode;

    public GameModel() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        gameMode = "PVP";
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) return false;
        if (board[row][col] != ' ' || gameOver) return false;
        board[row][col] = currentPlayer;
        return true;
    }

    public char checkWin() {
        for (int i = 0; i < 3; i++)
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];

        for (int j = 0; j < 3; j++)
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
                return board[0][j];

        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];

        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];

        return ' ';
    }

    public boolean checkDraw() {
        if (checkWin() != ' ') return false;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void reset() {
        resetBoard();
        currentPlayer = 'X';
        gameOver = false;
    }

    public char getCurrentPlayer() { return currentPlayer; }
    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    public String getGameMode() { return gameMode; }
    public void setGameMode(String gameMode) { this.gameMode = gameMode; }
    public char getCell(int row, int col) { return board[row][col]; }
}
