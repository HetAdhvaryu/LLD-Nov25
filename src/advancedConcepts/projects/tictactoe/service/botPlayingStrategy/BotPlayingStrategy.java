package advancedConcepts.projects.tictactoe.service.botPlayingStrategy;

import advancedConcepts.projects.tictactoe.model.Board;
import advancedConcepts.projects.tictactoe.model.Move;
import advancedConcepts.projects.tictactoe.model.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player);
}
