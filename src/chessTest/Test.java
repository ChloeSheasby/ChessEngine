package chessTest;

import java.util.Scanner;

import chessPD.Game;
import chessPD.Game.GameStatus;
import chessPD.Player.PlayerType;
import chessPD.GameTree;
import chessPD.Move;
import chessPD.Player;

public class Test {

	public static void main(String[] args) throws Exception {
//		Board board = new Board();
//		board.printBoard();
//		System.out.println();
//		
		Scanner scan = new Scanner(System.in);
		Player p1 = new Player(true, PlayerType.HUMAN, "Chloe");
		Player p2 = new Player(false, PlayerType.COMPUTER, "R2-D2");
		//Player p2 = new Player(false, PlayerType.HUMAN, "Joseph");
		
		Game game = new Game(p1, p2);
		
		GameTree decisionTree = null;

		System.out.println("Welcome to chess, " + game.getP1().name + " and " + game.getP2().name + "!");
		
		/*hi!
		- joseph daniel sheasby december 2020*/
		
//		// starting out with some moves so testing is easier
//		game.playerMove(p1, 0, 2, 1, 0);
//		game.playerMove(p2, 6, 4, 3, 3);
//		game.playerMove(p1, 2, 3, 0, 2);
//		game.playerMove(p2, 7, 3, 2, 6);
//		game.playerMove(p1, 1, 3, 0, 0);
//		game.playerMove(p2, 4, 3, 3, 2);
//		game.playerMove(p1, 0, 2, 0, 0);
//		game.playerMove(p2, 6, 4, 4, 4);
//		game.playerMove(p1, 0, 2, 6, 7);
//		game.playerMove(p2, 3, 1, 6, 4);
//		game.playerMove(p1, 2, 4, 7, 6);
//		game.playerMove(p2, 7, 5, 1, 2);
//		game.playerMove(p1, 4, 5, 6, 4);
//		game.playerMove(p2, 6, 4, 5, 5);
//		game.playerMove(p1, 2, 2, 0, 3);
//		game.playerMove(p2, 7, 5, 4, 4);
//		game.playerMove(p1, 0, 1, 3, 4);
//		game.playerMove(p2, 4, 3, 5, 5);
//		game.playerMove(p1, 1, 2, 4, 4);
//		game.playerMove(p2, 5, 5, 4, 3);
//		
//		System.out.print("Killed Pieces: ");
//		if(game.getPiecesKilled() != null)
//		{
//			for(int i = 0; i < game.getPiecesKilled().size(); i++)
//			{
//				System.out.print(game.getPiecesKilled().get(i).toString() + " ");
//			}
//			System.out.println();
//		}
//		
//		King king = (King) game.getBoard().getPieceForPosition(7, 3);
//		king.isCheck(game.getBoard());
//		System.out.print(king.toString() + " " + king.isCheck(game.getBoard()));
		
		game.playerMove(p1, 1, 3, 1, 1);
		
		while(game.getStatus() == GameStatus.ACTIVE)
		{
			System.out.println();
			System.out.println(game.getCurrentPlayer().name + "'s Turn:");
			System.out.println();
			
			game.printBoard();
			
			if(game.getCurrentPlayer().getType() == PlayerType.HUMAN)
			{
				System.out.println("\nPlease enter a position in the format of 'E 3' (letter, space, then number).");
				
				System.out.print("Enter your starting position: ");
				int startY = scan.next().charAt(0) - 65;	// changes the letter char to an int
				int startX = scan.next().charAt(0) - 49;	// changes the number char to an int and must subtract one more to get indices from 0-7
				
				System.out.print("Enter your ending position: ");
				int endY = scan.next().charAt(0) - 65;	// changes the letter char to an int
				int endX = scan.next().charAt(0) - 49;	// changes the number char to an int and must subtract one more to get indices from 0-7
				
				if(!game.playerMove(game.getCurrentPlayer(), startX, endX, startY, endY))
					System.out.println("\nInvalid move. Please try again.");
			}
			else if(game.getCurrentPlayer().getType() == PlayerType.COMPUTER)
			{
				decisionTree = new GameTree(game.getCurrentPlayer(), game.getOtherPlayer(), game.getBoard());
				//decisionTree.evaluateTree();
				Move move = decisionTree.getNextMove();
				game.playerMove(game.getCurrentPlayer(), move.getStart().getX(), move.getEnd().getX(), move.getStart().getY(), move.getEnd().getY());
			}
		
			System.out.print("Killed Pieces: ");
			if(game.getPiecesKilled() != null)
			{
				for(int i = 0; i < game.getPiecesKilled().size(); i++)
				{
					System.out.print(game.getPiecesKilled().get(i).toString() + " ");
				}
				System.out.println();
			}
		}
		
		game.evaluateGameStatus();
		
		scan.close();
	}

}
