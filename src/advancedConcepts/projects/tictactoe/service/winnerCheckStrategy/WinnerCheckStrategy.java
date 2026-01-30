package advancedConcepts.projects.tictactoe.service.winnerCheckStrategy;

import advancedConcepts.projects.tictactoe.model.Board;
import advancedConcepts.projects.tictactoe.model.Move;
import advancedConcepts.projects.tictactoe.model.Player;

public interface WinnerCheckStrategy {
    Player checkWinner(Board board, Move lastPlayedMove);
}
