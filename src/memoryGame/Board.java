package memoryGame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {
	private int rows;
	private int columns;
	public int[][] board;
	public int[][] gameBoard;
	public int[][] tempBoard;
	public Game game;
	
	public Board(int boardRows, int boardColumns) {
		this.rows = boardRows;
		this.columns = boardColumns;
		this.board = new int[boardRows][boardColumns];
		this.gameBoard = new int[boardRows][boardColumns];
		
		initialiseBoard();
	}
	
	public void addGame(Game newGame) {
		this.game = newGame;
		this.game.playGame();
	}
	
	public void initialiseBoard() {
		Integer[] cards = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8};
		List<Integer> cardList = Arrays.asList(cards);
		Collections.shuffle(cardList);
		int cardIndex = 0;
		
		for (int countRow = 0; countRow < this.board.length; countRow++) {
            for (int countColumn = 0; countColumn < this.board[countRow].length; countColumn++) {
            	this.board[countRow][countColumn] = cardList.get(cardIndex);
                cardIndex++;
            }
        }
		initialiseGameBoard();
	}
	
	public void initialiseGameBoard() {
		for (int countRow = 0; countRow < this.gameBoard.length; countRow++) {
			for (int countColumn = 0; countColumn < this.gameBoard[countRow].length; countColumn++) {
				this.gameBoard[countRow][countColumn] = 0;
			}
		}
		initialiseTempBoard();
	}
	
	public void initialiseTempBoard() {
		this.tempBoard = new int[this.rows][this.columns];
		
		for (int countRow = 0; countRow < this.tempBoard.length; countRow++) {
			for (int countColumn = 0; countColumn < this.tempBoard[countRow].length; countColumn++) {
				this.tempBoard[countRow][countColumn] = this.gameBoard[countRow][countColumn];
			}
		}
	}
	
	public void updateGameBoard(int firstCardRow, int firstCardColumn, int firstCard, int secondCardRow, int secondCardColumn, int secondCard) {
		this.gameBoard[firstCardRow][firstCardColumn] = firstCard;
		this.gameBoard[secondCardRow][secondCardColumn] = secondCard;
	}
	
	public void showBoard() {
		System.out.println("~~~~~~~~");
		for (int countRow = 0; countRow < this.board.length; countRow++) {
			for (int countColumn = 0; countColumn < this.board[countRow].length; countColumn++) {
				System.out.print(this.board[countRow][countColumn] + " ");
			}
			System.out.println();
		}
		System.out.println("~~~~~~~~");
	}
	
	public void showTempBoard() {
		System.out.println("\n" + "~~~~~~~~");
		for (int countRow = 0; countRow < this.tempBoard.length; countRow++) {
			for (int countColumn = 0; countColumn < this.tempBoard[countRow].length; countColumn++) {
				System.out.print(this.tempBoard[countRow][countColumn] + " ");
			}
			System.out.println();
		}
		System.out.println("~~~~~~~~");
	}
}
