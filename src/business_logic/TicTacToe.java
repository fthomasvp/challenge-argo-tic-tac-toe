package business_logic;

import java.util.Arrays;
import java.util.List;

import enums.Rules;
import models.Movement;

public class TicTacToe {
  final static int BOARD_SIZE = 3;
  final static int CHARACTER_WIN_COUNT = 3;

  private String[][] board;

  public TicTacToe() {
    this.board = this.initBoard();
  }

  public String[][] initBoard() {
    String[][] emptyBoard = new String[BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < emptyBoard.length; i++) {
      for (int j = 0; j < emptyBoard[i].length; j++) {
        emptyBoard[i][j] = "|_|";
      }
    }

    return emptyBoard;
  }

  public void displayBoard() {
    System.out.println();

    for (int i = 0; i < this.board.length; i++) {
      System.out.print("\t\t");
      for (int j = 0; j < this.board[i].length; j++) {
        System.out.print(this.board[i][j]);
      }
      System.out.println();
    }

    System.out.println();
  }

  public void displayScoreboard(int playerOneWinCount, int playerTwoWinCount) {
    System.out.println("\t<<< Placar >>>");
    System.out.println("Jogador 1 vs Jogador 2");
    System.out.println(playerOneWinCount + " x " + playerTwoWinCount);
  }

  /**
   * 
   * @param movement
   * @return true if the player fulfilled the board horizontally. And false if he
   *         don't.
   */
  public boolean isHorizontalMovementWin(Movement movement) {
    String[] boardLine = this.board[movement.getLine()];
    int playerCharacterCount = 0;

    for (int i = 0; i < boardLine.length; i++) {
      if (boardLine[i].equals(movement.player.getCharacter())) {
        playerCharacterCount++;
      }
    }

    return playerCharacterCount == CHARACTER_WIN_COUNT;
  }

  /**
   * 
   * @param movement
   * @return true if the player fulfilled the board vertically. And false if he
   *         don't.
   */
  public boolean isVerticalMovementWin(Movement movement) {
    int playerCharacterCount = 0;

    for (int i = 0; i < this.board.length; i++) {
      if (this.board[i][movement.getColumn()].equals(movement.player.getCharacter())) {
        playerCharacterCount++;
      }
    }

    return playerCharacterCount == CHARACTER_WIN_COUNT;
  }

  /**
   * 
   * @param movement
   * @return true if the player fulfilled the board on principal diagonal. And
   *         false if he don't.
   */
  public boolean isPrincipalDiagonalMovementWin(Movement movement) {
    int playerCharacterCount = 0;

    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board[i].length; j++) {
        if (i == j) {
          if (this.board[i][j].equals(movement.player.getCharacter())) {
            playerCharacterCount++;
          }
        }
      }
    }

    return playerCharacterCount == CHARACTER_WIN_COUNT;
  }

  /**
   * 
   * @param movement
   * @return true if the player fulfilled the board on secondary diagonal. And
   *         false if he don't.
   */
  public boolean isSecondaryDiagonalMovementWin(Movement movement) {
    int playerCharacterCount = 0;

    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board[i].length; j++) {
        if (i + j == this.board.length - 1) {
          if (this.board[i][j].equals(movement.player.getCharacter())) {
            playerCharacterCount++;
          }
        }
      }
    }

    return playerCharacterCount == CHARACTER_WIN_COUNT;
  }

  /**
   * 
   * @return true if the board has no empty places (|_|). And true if does not.
   */
  public boolean isBoardFulFilled() {
    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board[i].length; j++) {
        if (this.board[i][j].equals("|_|")) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * 
   * @param movement
   * @return A Rules Enum: WIN if it's a horizontally, vertically or diagonally
   *         movement. INVALID_MOVEMENT if it's in a fulfilled place.
   *         ACCEPTED_MOVEMENT if it's a movement on an empty place (|_|). And
   *         STILL_PLAYING if the game is not over.
   */
  public Rules makeMovement(Movement movement) {
    if (this.board[movement.getLine()][movement.getColumn()].equals("|_|")) {
      this.board[movement.getLine()][movement.getColumn()] = movement.player.getCharacter();

      List<Boolean> isMovementWin = Arrays.asList(this.isHorizontalMovementWin(movement),
          this.isVerticalMovementWin(movement), this.isPrincipalDiagonalMovementWin(movement),
          this.isSecondaryDiagonalMovementWin(movement));

      if (isMovementWin.contains(true)) {
        return Rules.WIN;
      }

      return Rules.ACCEPTED_MOVIMENT;
    }

    if (!this.board[movement.getLine()][movement.getColumn()].equals("|_|")) {
      return Rules.INVALID_MOVEMENT;
    }

    return Rules.STILL_PLAYING;
  }
}
