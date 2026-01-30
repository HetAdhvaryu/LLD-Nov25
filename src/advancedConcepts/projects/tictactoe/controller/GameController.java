package advancedConcepts.projects.tictactoe.controller;

import advancedConcepts.projects.tictactoe.model.*;
import advancedConcepts.projects.tictactoe.service.botPlayingStrategy.BotPlayingStrategy;
import advancedConcepts.projects.tictactoe.service.botPlayingStrategy.RandomBotPlayingStrategy;
import advancedConcepts.projects.tictactoe.service.winnerCheckStrategy.OrderOneWinnerCheckStrategy;
import advancedConcepts.projects.tictactoe.service.winnerCheckStrategy.WinnerCheckStrategy;

import java.util.List;
import java.util.Scanner;

public class GameController {

    private WinnerCheckStrategy winnerCheckStrategy;

    public GameController(int dimension) {
        this.winnerCheckStrategy = new OrderOneWinnerCheckStrategy(dimension);
    }

    public Game createNewGame(int dimension, List<Player> players) {
        return Game.builder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
    }

    public void displayBoard(Game game) {
        List<List<Cell>> matrix = game.getBoard().getMatrix();
        for(List<Cell> row : matrix) {
            for(Cell cell : row) {
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    System.out.print("| | ");
                } else {
                    System.out.print("|" + cell.getPlayer().getSymbol() + "| ");
                }
            }
            System.out.println();
        }
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public Game updateGameStatus(Game game, GameStatus status) {
        game.setGameStatus(status);
        return game;
    }

    public Move executeMove(Game game, Player player) {
        if(player.getPlayerType().equals(PlayerType.HUMAN)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the row for the cell : " + player.getName());
            int row = sc.nextInt();
            System.out.println("Please enter the column for the cell : " + player.getName());
            int column = sc.nextInt();
            //TODO: validate the row and column
            Cell playedMoveCell = game.getBoard().getMatrix().get(row).get(column);
            playedMoveCell.setCellState(CellState.FILLED);
            playedMoveCell.setPlayer(player);
            return new Move(player, playedMoveCell);
        } else {
            System.out.println("Bot is making a move");
            //TODO: implement factory and proper structure for bot playing strategy design pattern
            BotPlayingStrategy strategy = new RandomBotPlayingStrategy();
            return strategy.makeMove(game.getBoard(), player);
        }
    }

    public Player checkWinner(Game game, Move move) {
        //TODO: implement factory and proper structure for winner check strategy design pattern
        return winnerCheckStrategy.checkWinner(game.getBoard(), move);
    }

    public Board undoMove(Game game, Move move) {
        return null;
    }

    public void replayGame(Game game) {

    }




}
