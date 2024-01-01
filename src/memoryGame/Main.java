package memoryGame;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner playerInput = new Scanner(System.in);
		String playerName;
		int boardRows = 4;
		int boardColumns = 4;

		System.out.println("=============================================");
		System.out.println("Welkom bij het geheugenspel!" + "\n");
		
		System.out.print("Speler 1: ");
		playerName = playerInput.nextLine();
		Player player1 = new Player(playerName);
		System.out.print("Speler 2: ");
		playerName = playerInput.nextLine();
		Player player2 = new Player(playerName);
		
		Board board = new Board(boardRows, boardColumns);
		Game game = new Game(board, 2);
		game.addPlayer(player1);
		game.addPlayer(player2);
		
		System.out.println("\n" + "De speler de meeste juiste combinaties wint!");
		System.out.println("=============================================");
		
        board.showBoard();
        board.addGame(game);
		playerInput.close();
	}

}
