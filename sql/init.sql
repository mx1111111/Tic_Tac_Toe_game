CREATE DATABASE IF NOT EXISTS tictactoe_db;

USE tictactoe_db;

CREATE TABLE IF NOT EXISTS scores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    game_mode VARCHAR(10) NOT NULL DEFAULT 'PVP',
    player_x_score INT,
    player_o_score INT
);

INSERT INTO scores (game_mode, player_x_score, player_o_score) VALUES ('PVP', 0, 0), ('PVC', 0, 0);
