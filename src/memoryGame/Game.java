package memoryGame;

import java.util.Scanner;

public class Game {
	private boolean finished;
	private Board board;
	private Player[] players;
	
	public Game(Board gameBoard, int playerCount) {
		this.finished = false;
		this.board = gameBoard;
		this.players = new Player[playerCount];
	}
	
	public void addPlayer(Player player) {
		for (int i = 0; i < this.players.length; i++) {
			if (this.players[i] == null) {
				this.players[i] = player;
				
				return;
			}
		}
	}
	
	public void increaseScore(int playerId) {
		this.players[playerId].score++;
	}
	
	public void showWinner() {
		Player winner = this.players[0];
		int lowestScore = winner.getScore();
		
		for (int i = 0; i < this.players.length; i++) {
			if (this.players[i] != null) {
				if (this.players[i].getScore() > winner.getScore()) {
					winner = this.players[i];
				} else {
					lowestScore = this.players[i].getScore();
				}
			}
		}
		
		if (winner.getScore() != lowestScore) {
			System.out.println(winner.getName() + " wint met " + winner.getScore() + " tegen " + lowestScore + "!");
		} else {
			System.out.println("Gelijkspel met " + winner.getScore() + " tegen " + lowestScore + "!");
		}
	}
	
	public void returnGameState() {
		if (checkGameState(this.board.board, this.board.gameBoard)) {
			this.finished = true;
		}
	}
	
	private boolean checkGameState(int[][] board, int[][] gameBoard) {
		for (int countRow = 0; countRow < board.length; countRow++) {
			for (int countColumn = 0; countColumn < board.length; countColumn++) {
				if (gameBoard[countRow][countColumn] != board[countRow][countColumn]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void turnFirstCard(int cardRow, int cardColumn) {
		int firstCard = this.board.board[cardRow][cardColumn];
	
		this.board.tempBoard[cardRow][cardColumn] = firstCard;
		this.board.showTempBoard();
	}
	
	public void turnSecondCard(int cardRow, int cardColumn) {
		int secondCard = this.board.board[cardRow][cardColumn];
		
		this.board.tempBoard[cardRow][cardColumn] = secondCard;
		this.board.showTempBoard();
	}
	
	public void compareCards(int playerId, int firstCardRow, int firstCardColumn, int secondCardRow, int secondCardColumn) {
		int firstCard = this.board.board[firstCardRow][firstCardColumn];
		int secondCard = this.board.board[secondCardRow][secondCardColumn];
		
		if (firstCard == secondCard) {
			this.board.updateGameBoard(firstCardRow, firstCardColumn, firstCard, secondCardRow, secondCardColumn, secondCard);
			this.board.game.increaseScore(playerId);
			System.out.println("\n" + "Juiste combinatie! +1 punt voor " + this.players[playerId].getName());
		} else {
			System.out.println("\n" + "Onjuiste combinatie...");
		}
		this.board.initialiseTempBoard();
		System.out.println("---------------------------------------------");
	}
	
	public void playGame() {
		Scanner playerInput = new Scanner(System.in);
		int currentPlayerIndex = 0;
		
		while (this.finished == false) {
			Player currentPlayer = this.players[currentPlayerIndex];
			
			System.out.println(currentPlayer.getName() + " is aan het zet...");
            System.out.print("\n" + "Kies de eerste kaart" + "\n" + "Rij: ");
    		int firstCardRow = playerInput.nextInt();
    		System.out.print("Kolom: ");
    		int firstCardColumn = playerInput.nextInt();
            turnFirstCard(firstCardRow, firstCardColumn);
            
    		System.out.print("\n" + "Kies de tweede kaart" + "\n" + "Rij: ");
    		int secondCardRow = playerInput.nextInt();
    		System.out.print("Kolom: ");
    		int secondCardColumn = playerInput.nextInt();
    		turnSecondCard(secondCardRow, secondCardColumn);
    		
    		compareCards(currentPlayerIndex, firstCardRow, firstCardColumn, secondCardRow, secondCardColumn);
    		returnGameState();
    		
    		currentPlayerIndex = (currentPlayerIndex + 1) % 2;
		}
		showWinner();
		playerInput.close();
	}
}
