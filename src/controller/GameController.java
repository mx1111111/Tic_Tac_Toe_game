package controller;

import dao.ScoreDAO;
import model.GameModel;
import model.Score;
import view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameController implements ActionListener {
    private GameModel model;
    private GameView view;
    private Score pvpScore;
    private Score pvcScore;
    private ScoreDAO scoreDAO;
    private Random random;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.random = new Random();
        this.scoreDAO = new ScoreDAO();

        Score loadedPvp = scoreDAO.loadScores("PVP");
        pvpScore = (loadedPvp != null) ? loadedPvp : new Score("PVP", 0, 0);

        Score loadedPvc = scoreDAO.loadScores("PVC");
        pvcScore = (loadedPvc != null) ? loadedPvc : new Score("PVC", 0, 0);

        view.setController(this);
        view.showMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == view.getPvpButton()) {
            model.setGameMode("PVP");
            view.updateScore(pvpScore.getPlayerXScore(), pvpScore.getPlayerOScore(), "PVP");
            newGame();
            view.showGame();
            return;
        }

        if (source == view.getPvcButton()) {
            model.setGameMode("PVC");
            view.updateScore(pvcScore.getPlayerXScore(), pvcScore.getPlayerOScore(), "PVC");
            newGame();
            view.showGame();
            return;
        }

        if (source == view.getNewGameButton()) {
            newGame();
            return;
        }

        if (source == view.getResetScoresButton()) {
            resetScores();
            return;
        }

        if (source == view.getMenuButton()) {
            goToMenu();
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (source == view.getButtons()[i][j]) {
                    handleCellClick(i, j);
                    return;
                }
            }
        }
    }

    private Score currentScore() {
        return model.getGameMode().equals("PVP") ? pvpScore : pvcScore;
    }

    private void handleCellClick(int row, int col) {
        if (model.isGameOver()) return;

        if (model.makeMove(row, col)) {
            view.updateBoard(getBoardState());

            char winner = model.checkWin();
            if (winner != ' ') {
                model.setGameOver(true);
                handleWin(winner);
                return;
            }

            if (model.checkDraw()) {
                model.setGameOver(true);
                view.showMessage("It's a draw!");
                return;
            }

            model.switchPlayer();

            if (model.getGameMode().equals("PVC") && model.getCurrentPlayer() == 'O' && !model.isGameOver()) {
                computerTurn();
            }
        }
    }

    private void computerTurn() {
        int emptyCount = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (model.getCell(i, j) == ' ') emptyCount++;

        if (emptyCount == 0) return;

        int target = random.nextInt(emptyCount);
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (model.getCell(i, j) == ' ') {
                    if (count == target) {
                        handleCellClick(i, j);
                        return;
                    }
                    count++;
                }
            }
        }
    }

    private void handleWin(char winner) {
        Score score = currentScore();
        if (winner == 'X') {
            score.setPlayerXScore(score.getPlayerXScore() + 1);
            view.showMessage("Player X wins!");
        } else {
            if (model.getGameMode().equals("PVC")) {
                view.showMessage("Computer wins!");
            } else {
                view.showMessage("Player O wins!");
            }
            score.setPlayerOScore(score.getPlayerOScore() + 1);
        }

        scoreDAO.saveScores(score);
        view.updateScore(score.getPlayerXScore(), score.getPlayerOScore(), model.getGameMode());
    }

    private void newGame() {
        model.reset();
        view.updateBoard(getBoardState());
    }

    private void goToMenu() {
        model.reset();
        view.showMenu();
    }

    private void resetScores() {
        Score score = currentScore();
        score.setPlayerXScore(0);
        score.setPlayerOScore(0);
        scoreDAO.saveScores(score);
        view.updateScore(0, 0, model.getGameMode());
        newGame();
    }

    private char[][] getBoardState() {
        char[][] state = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                state[i][j] = model.getCell(i, j);
        return state;
    }
}
