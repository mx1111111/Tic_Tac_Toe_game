package dao;

import model.Score;
import java.sql.*;

public class ScoreDAO {

    public Score loadScores(String gameMode) {
        String sql = "SELECT * FROM scores WHERE game_mode = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, gameMode);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Score(rs.getString("game_mode"), rs.getInt("player_x_score"), rs.getInt("player_o_score"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading scores: " + e.getMessage());
        }
        return null;
    }

    public void saveScores(Score score) {
        String updateSql = "UPDATE scores SET player_x_score = ?, player_o_score = ? WHERE game_mode = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            pstmt.setInt(1, score.getPlayerXScore());
            pstmt.setInt(2, score.getPlayerOScore());
            pstmt.setString(3, score.getGameMode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }
}
