package advancedConcepts.projects.tictactoe.service.botPlayingStrategy;

import advancedConcepts.projects.tictactoe.exception.DrawGameException;
import advancedConcepts.projects.tictactoe.model.*;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move makeMove(Board board, Player player) {
        List<List<Cell>> matrix = board.getMatrix();
        for (List<Cell> cells : matrix) {
            for (Cell cell : cells) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    cell.setCellState(CellState.FILLED);
                    cell.setPlayer(player);
                    return new Move(player, cell);
                }
            }
        }
        throw new DrawGameException("No more empty cells available");
    }
}
