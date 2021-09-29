package chessPD;

public class GameTree 
{
	private GameTreeNode root;
	
	public GameTree(Player player, Player other, Board board)
	{
		root = new GameTreeNode(null, player, other, board, 0);
		root.createChildren();
	}
	
	public void evaluateTree()
	{
		root.evaluate();
	}
		
	public Move getNextMove()
	{
		return root.getNextMove();
	}
	
	public GameTreeNode getRoot()
	{
		return this.root;
	}
}
