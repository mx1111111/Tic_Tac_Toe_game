package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel menuPanel;
    private JButton pvpButton;
    private JButton pvcButton;

    private JPanel gamePanel;
    private JButton[][] buttons;
    private JLabel scoreLabel;
    private JButton newGameButton;
    private JButton resetScoresButton;
    private JButton menuButton;

    public GameView() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createMenuPanel();
        createGamePanel();

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(gamePanel, "game");
        add(mainPanel);
    }

    private void createMenuPanel() {
        menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        JLabel title = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        gbc.gridy = 0;
        menuPanel.add(title, gbc);

        pvpButton = new JButton("Player vs Player");
        pvpButton.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 1;
        menuPanel.add(pvpButton, gbc);

        pvcButton = new JButton("Player vs Computer");
        pvcButton.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 2;
        menuPanel.add(pvcButton, gbc);
    }

    private void createGamePanel() {
        gamePanel = new JPanel(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                boardPanel.add(buttons[i][j]);
            }
        }

        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("X: 0  |  O: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scorePanel.add(scoreLabel);

        JPanel controlPanel = new JPanel();
        newGameButton = new JButton("New Game");
        resetScoresButton = new JButton("Reset Scores");
        menuButton = new JButton("Menu");
        controlPanel.add(newGameButton);
        controlPanel.add(resetScoresButton);
        controlPanel.add(menuButton);

        gamePanel.add(boardPanel, BorderLayout.CENTER);
        gamePanel.add(scorePanel, BorderLayout.NORTH);
        gamePanel.add(controlPanel, BorderLayout.SOUTH);
    }

    public void setController(GameController controller) {
        pvpButton.addActionListener(controller);
        pvcButton.addActionListener(controller);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].addActionListener(controller);
        newGameButton.addActionListener(controller);
        resetScoresButton.addActionListener(controller);
        menuButton.addActionListener(controller);
    }

    public void showMenu() {
        cardLayout.show(mainPanel, "menu");
    }

    public void showGame() {
        cardLayout.show(mainPanel, "game");
    }

    public void updateBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = buttons[i][j];
                char c = board[i][j];
                btn.setText(String.valueOf(c));
                if (c == 'X') {
                    btn.setForeground(new Color(0, 102, 204));
                } else if (c == 'O') {
                    btn.setForeground(new Color(204, 0, 0));
                }
            }
        }
    }

    public void updateScore(int xScore, int oScore, String mode) {
        if (mode.equals("PVC")) {
            scoreLabel.setText("Player: " + xScore + "  |  Computer: " + oScore);
        } else {
            scoreLabel.setText("X: " + xScore + "  |  O: " + oScore);
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton getPvpButton() { return pvpButton; }
    public JButton getPvcButton() { return pvcButton; }
    public JButton[][] getButtons() { return buttons; }
    public JButton getNewGameButton() { return newGameButton; }
    public JButton getResetScoresButton() { return resetScoresButton; }
    public JButton getMenuButton() { return menuButton; }
}
