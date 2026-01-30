package advancedConcepts.projects.tictactoe.model;

public class Move {
    private Player player;
    private Cell cell;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}


// Move for a game like chess
// class ChessMove{
//     private Cell startCell;
//     private Cell endCell;
//     private Player player;
//     private ChessPiece chessPiece;
//}