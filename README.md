# Tic-Tac-Toe (Morpion)

A simple Tic-Tac-Toe desktop game built with **Java Swing** and **MVC architecture**.

## Features

- Two game modes: Player vs Player / Player vs Computer
- 3×3 board with colored X (blue) and O (red)
- Win / draw detection
- Persistent score tracking via MySQL database
- Separate scores for PVP and PVC modes
- New Game, Reset Scores, and Menu buttons

## Project Structure

```
src/
├── main/Main.java              # Entry point
├── model/
│   ├── GameModel.java          # Game logic & state
│   └── Score.java              # Score POJO
├── view/GameView.java          # Swing UI (CardLayout)
├── controller/GameController.java  # MVC controller
└── dao/
    ├── DatabaseConnection.java # JDBC connection
    └── ScoreDAO.java           # DB read/write
sql/init.sql                    # Database setup
```

## Requirements

- Java 17+
- MySQL (XAMPP recommended)
- MySQL Connector/J (included in `lib/`)

## Setup

1. Start MySQL and run `sql/init.sql` to create the database:
   ```sql
   CREATE DATABASE tictactoe_db;
   USE tictactoe_db;
   CREATE TABLE scores (
       id INT PRIMARY KEY AUTO_INCREMENT,
       game_mode VARCHAR(10) NOT NULL DEFAULT 'PVP',
       player_x_score INT,
       player_o_score INT
   );
   INSERT INTO scores (game_mode, player_x_score, player_o_score)
   VALUES ('PVP', 0, 0), ('PVC', 0, 0);
   ```

2. Compile:
   ```
   javac -cp "lib\*" -d out src\main\Main.java src\model\*.java src\view\*.java src\controller\*.java src\dao\*.java
   ```

3. Run:
   ```
   java -cp "out;lib\*" main.Main
   ```
