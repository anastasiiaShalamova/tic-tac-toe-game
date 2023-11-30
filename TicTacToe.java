package TIC_TAC_TOE;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TicTacToe {
	
    private static final int GRID_SIZE = 3;
    private char[][] grid;
    private int scoreX;
    private int scoreO;
    private Queue<Character> turnQueue;

    
    public TicTacToe() {
    	
        grid = new char[GRID_SIZE][GRID_SIZE];
        scoreX = 0;
        scoreO = 0;
        turnQueue = new LinkedList<>();
        turnQueue.add('X');
        turnQueue.add('O');
    }

    public void playGame() {
    	
        while (true) {
            initializeGrid();
            playRound();
            displayScore();
            
            if (!playAgain()) {
                System.out.println("Thanks for playing. Final Score:\n Player X: " + scoreX + ", Player O: " + scoreO);
                break;
            }
        }
    }

    private void initializeGrid() {
    	
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    private void playRound() {
    	
        while (true) {
            char currentPlayer = turnQueue.poll();
            displayGrid();
            makeMove(currentPlayer);
            
            if (checkWinner(currentPlayer)) {
                System.out.println("\nPlayer " + currentPlayer + " wins!");
                updateScore(currentPlayer);
                break;
            }
            if (isGridFull()) {
                System.out.println("It's a draw!");
                break;
            }
            turnQueue.offer(currentPlayer); // adds current player back to the queue for the next turn
        }
    }

    private void displayGrid() {
    	
        System.out.println("-------------");
        
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print("| ");
            
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private void makeMove(char currentPlayer) {
    	
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            System.out.print("\nPlayer " + currentPlayer + ", enter your move: \n(row and column, separated by space) ");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } 
        while (!isValidMove(row, col));

        grid[row - 1][col - 1] = currentPlayer;
    }

    private boolean isValidMove(int row, int col) {
    	
        if (row < 1 || row > GRID_SIZE || col < 1 || col > GRID_SIZE || grid[row - 1][col - 1] != ' ') {
            System.out.println("I\nnvalid move. Try again.");
            return false;
        }
        return true;
    }

    // hecks rows, columns, and diagonals for a win
    private boolean checkWinner(char currentPlayer) {
    	
        for (int i = 0; i < GRID_SIZE; i++) {
        	
            if (grid[i][0] == currentPlayer && grid[i][1] == currentPlayer && grid[i][2] == currentPlayer) {
                return true; // row win
            }
            if (grid[0][i] == currentPlayer && grid[1][i] == currentPlayer && grid[2][i] == currentPlayer) {
                return true; // column win
            }
        }

        if (grid[0][0] == currentPlayer && grid[1][1] == currentPlayer && grid[2][2] == currentPlayer) {
            return true; // diagonal win (top left to bottom right)
        }
        if (grid[0][2] == currentPlayer && grid[1][1] == currentPlayer && grid[2][0] == currentPlayer) {
            return true; // diagonal win (top right to bottom left)
        }

        return false;
    }

    private boolean isGridFull() {
    	
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateScore(char winner) {
        if (winner == 'X') {
            scoreX++;
        } else {
            scoreO++;
        }
    }

    private void displayScore() {
        System.out.println("Current Score:  Player X: " + scoreX + ", Player O: " + scoreO);
    }

    private boolean playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you want to play again? (yes/no): ");
        String choice = scanner.next().toLowerCase();
        return choice.equals("yes");
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}
