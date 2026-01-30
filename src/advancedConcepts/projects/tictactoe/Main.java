package advancedConcepts.projects.tictactoe;

import advancedConcepts.projects.tictactoe.controller.GameController;
import advancedConcepts.projects.tictactoe.model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //basic initialisations
        int id = 1;
        List<Player> players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to TicTacToe Game");
        //taking user inputs
        System.out.println("Please enter the dimension for the board");
        int dimension = scanner.nextInt();
        GameController gameController = new GameController(dimension);
        System.out.println("Do you want a bot in the game ? (Y/N)");
        String botAns = scanner.next();
        // adding a bot into players list
        if(botAns.equalsIgnoreCase("Y")) {
            Bot bot = new Bot(id++, "BOT", '$', PlayerType.BOT, BotDifficultyLevel.MEDIUM);
            players.add(bot);
        }
        // adding players into the list
        while (id < dimension){ // id goes upto dimension-1, when id becomes equal to dimension, loop breaks
            System.out.println("Please enter the name of the player");
            String playerName = scanner.next();
            System.out.println("Please enter the symbol for the player");
            char playerSymbol = scanner.next().charAt(0);
            Player newPlayer = new Player(id++, playerName, playerSymbol, PlayerType.HUMAN);
            players.add(newPlayer);
        }
        Collections.shuffle(players); // randomise the player list
        Game game = gameController.createNewGame(dimension, players);
        int playerIndex = -1;
        //playing of the game
        while (gameController.getGameStatus(game).equals(GameStatus.YET_TO_START) ||
                gameController.getGameStatus(game).equals(GameStatus.RUNNING)
        ){
            gameController.updateGameStatus(game, GameStatus.RUNNING);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            gameController.displayBoard(game);
            Move executedMove = gameController.executeMove(game, players.get(playerIndex));
            // TODO 1
            // TODO 2
            Player winner = gameController.checkWinner(game, executedMove);
            if(winner !=  null){
                System.out.println("WINNER IS : " + winner.getName());
                gameController.displayBoard(game);
                gameController.updateGameStatus(game, GameStatus.WIN);
                break;
            }
        }
    }
}
/*

 */