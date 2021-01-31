import java.util.Scanner;

import business_logic.TicTacToe;
import enums.Player;
import enums.Rules;
import models.Movement;

public class App {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Seja bem vindo ao Jogo da Velha ===\n");

    TicTacToe ticTacToe = new TicTacToe();

    int playerOneWinCount = 0;
    int playerTwoWinCount = 0;

    String isStopPlaying = "N";

    int playerTurn = 0;

    do {
      Player player = playerTurn % 2 == 0 ? Player.PLAYER_ONE : Player.PLAYER_TWO;

      int line;
      int column;

      // Display the actual player
      if (Player.PLAYER_ONE == player) {
        System.out.println("\tXX-| Jogador 1 |-XX");
      } else {
        System.out.println("\tOO-| Jogador 2 |-OO");
      }

      do {
        System.out.println("(---) Informe a linha. (Valores permitidos: 0, 1 ou 2)");
        String input = scanner.nextLine();

        // Avoid exception when user doesn't input some valid value
        if (input.trim().length() == 0) {
          line = -1;
        } else {
          line = Integer.parseInt(input);
        }
      } while (line < 0 || line > 2);

      do {
        System.out.println("\n(|||) Informe a coluna. (Valores permitidos: 0, 1 ou 2)");
        String input = scanner.nextLine();

        if (input.trim().length() == 0) {
          column = -1;
        } else {
          column = Integer.parseInt(input);
        }
      } while (column < 0 || column > 2);

      Movement movement = new Movement(line, column, player);

      Rules fromMovementResult = ticTacToe.makeMovement(movement);

      ticTacToe.displayBoard();

      // Non-Win situation
      if (ticTacToe.isBoardFulFilled()) {
        playerTurn = 0;

        System.out.println("Deu Velha!\n");

        ticTacToe.displayScoreboard(playerOneWinCount, playerTwoWinCount);

        // Display option to Stop the game
        do {
          System.out.println("\n(!) Deseja parar o jogo? Caso não, uma nova partida irá começar. (S/N)");
          isStopPlaying = scanner.nextLine();
        } while (!isStopPlaying.contains("S") && !isStopPlaying.contains("N"));

        // Clear the board
        ticTacToe = new TicTacToe();
        continue;
      }

      if (fromMovementResult == Rules.WIN) {
        if (player == Player.PLAYER_ONE) {
          playerOneWinCount++;

          System.out.println("Jogador 1 venceu!\n");
        } else {
          playerTwoWinCount++;

          System.out.println("Jogador 2 venceu!\n");
        }

        ticTacToe.displayScoreboard(playerOneWinCount, playerTwoWinCount);

        do {
          System.out.println("\n(!) Deseja parar o jogo? Caso não, uma nova partida irá começar. (S/N)");
          isStopPlaying = scanner.nextLine();
        } while (!isStopPlaying.contains("S") && !isStopPlaying.contains("N"));

        playerTurn = 0;

        ticTacToe = new TicTacToe();
        continue;
      }

      if (fromMovementResult == Rules.INVALID_MOVEMENT) {
        System.out.println(
            "(!!!) Posição escolhida está preenchida. Por favor, informe outros valores para linha e coluna.\n");
        continue;
      }

      // Change the turn to next player
      playerTurn++;

    } while (isStopPlaying.equals("N"));

    scanner.close();
  }
}
