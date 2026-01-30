package advancedConcepts.projects.tictactoe.service.winnerCheckStrategy;

import advancedConcepts.projects.tictactoe.exception.DrawGameException;
import advancedConcepts.projects.tictactoe.model.Board;
import advancedConcepts.projects.tictactoe.model.Move;
import advancedConcepts.projects.tictactoe.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO: reduce the number of lines of code - ~130 lines in method -> half, approx 70
public class OrderOneWinnerCheckStrategy implements WinnerCheckStrategy{
    private int dimension;
    private List<HashMap<Character, Integer>> rowHashMapList; // index i of list would represent row i hashmap in the matrix
    private List<HashMap<Character, Integer>> colHashMapList; // index i of list would represent col i hashmap in the matrix
    private HashMap<Character, Integer> leftDiagonalHashMap;
    private HashMap<Character, Integer> rightDiagonalHashMap;
    private HashMap<Character, Integer> cornerHashMap;
    private int validHashMaps;

    public OrderOneWinnerCheckStrategy(int dimension) {
        rowHashMapList = new ArrayList<>();
        colHashMapList = new ArrayList<>();
        leftDiagonalHashMap = new HashMap<>();
        rightDiagonalHashMap = new HashMap<>();
        cornerHashMap = new HashMap<>();
        this.dimension = dimension;
        validHashMaps = 2 * dimension + 3;
        for(int i = 0; i < dimension; i++) {
            rowHashMapList.add(new HashMap<>());
            colHashMapList.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastPlayedMove) {
        Player player = lastPlayedMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastPlayedMove.getCell().getRow();
        int col = lastPlayedMove.getCell().getCol();
        if(checkAndUpdateColHashMap(col, symbol)
                || checkAndUpdateRowHashMap(row, symbol)
                || (checkCorner(row, col) && checkAndUpdateCornerHashMap(symbol)) // short circuit
                || (checkLeftDiagonal(row, col) && checkAndUpdateLeftDiagonalHashMap(symbol)) // short circuit
                || (checkRightDiagonal(row, col) && checkAndUpdateRightDiagonalHashMap(symbol))// short circuit
        ){
            return player;
        }
        return null;
    }

    private boolean checkAndUpdateRowHashMap(int row, char symbol){
        HashMap<Character, Integer> rowHashMap = rowHashMapList.get(row);
        if(rowHashMap.containsKey(symbol)){
            rowHashMap.put(symbol, rowHashMap.get(symbol) + 1);
            if(rowHashMap.get(symbol) == dimension){
                return true;
            }
        } else {
            rowHashMap.put(symbol, 1);
        }
        if(rowHashMap.size() > 1){
            validHashMaps--;
            if(validHashMaps == 0){
                throw new DrawGameException("Game is drawn");
            }
        }
        return false;
    }

    private boolean checkAndUpdateColHashMap(int col, char symbol){
        HashMap<Character, Integer> colHashMap = colHashMapList.get(col);
        if(colHashMap.containsKey(symbol)){
            colHashMap.put(symbol, colHashMap.get(symbol) + 1);
            if(colHashMap.get(symbol) == dimension){
                return true;
            }
        } else {
            colHashMap.put(symbol, 1);
        }
        if(colHashMap.size() > 1){
            validHashMaps--;
            if(validHashMaps == 0){
                throw new DrawGameException("Game is drawn");
            }
        }
        return false;
    }

    private boolean checkAndUpdateLeftDiagonalHashMap(char symbol){
        if(leftDiagonalHashMap.containsKey(symbol)){
            leftDiagonalHashMap.put(symbol, leftDiagonalHashMap.get(symbol) + 1);
            if(leftDiagonalHashMap.get(symbol) == dimension){
                return true;
            }
        } else {
            leftDiagonalHashMap.put(symbol, 1);
        }
        //TODO: add the check for draw
        return false;
    }

    private boolean checkAndUpdateRightDiagonalHashMap(char symbol){
        if(rightDiagonalHashMap.containsKey(symbol)){
            rightDiagonalHashMap.put(symbol, rightDiagonalHashMap.get(symbol) + 1);
            if(rightDiagonalHashMap.get(symbol) == dimension){
                return true;
            }
        } else {
            rightDiagonalHashMap.put(symbol, 1);
        }
        //TODO: add the check for draw
        return false;
    }

    private boolean checkAndUpdateCornerHashMap(char symbol){
        if(cornerHashMap.containsKey(symbol)){
            cornerHashMap.put(symbol, cornerHashMap.get(symbol) + 1);
            if(cornerHashMap.get(symbol) == 4){
                return true;
            }
        } else {
            cornerHashMap.put(symbol, 1);
        }
        //TODO: add the check for draw
        return false;
    }

    private boolean checkLeftDiagonal(int row, int col){
        return row == col;
    }

    private boolean checkRightDiagonal(int row, int col){
        return (row+col) == (dimension-1);
    }

    private boolean checkCorner(int row, int col){
        return (
                (row == 0 && col == 0)
                || (row == dimension-1 && col == dimension-1)
                || (row == 0 && col == dimension-1)
                || (row == dimension-1 && col == 0)
        );
    }
}
/*

        0,0 _ 0,2
        _ _ _   --> hashmap r1, c1, leftDiag, rightDiag
        2,0 _ 2,2

 */