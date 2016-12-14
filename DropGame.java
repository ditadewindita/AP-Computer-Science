import java.util.Scanner;


public class DropGame {
	
	private String[][] board;
	private static final int ROW=4;
	private static final int COL=6;
	
	public DropGame(){
		board = new String[ROW][COL];
		System.out.println("***Play the Drop Game!!***");
		System.out.println("Your pieces are black while the computer's are white.");
		System.out.println();
		System.out.println(getBoard());
		playGame();
	}
	
	public String getBoard(){
		String ret = "";
		for(String[] r: board){
			for(String c: r){
				if(c!=null)
					ret+=c + "\t";
				else
					ret+="**" + "\t";
			}
			ret+="\n";
		}
		return ret;		
	}
	
	public int dropLocation(int col){
		for(int i=ROW-1; i>=0; i--)
			if(board[i][col]==null)
				return i;
		return -1;
	}
	
	public boolean dropMatchesNeighbors(int col, int row, String color){
		if(col-1>-1 && row+1<ROW && col+1<COL){
			if(board[row][col-1]!=null && board[row+1][col]!=null && board[row][col+1]!=null)
				return board[row][col-1].equals(color) && board[row+1][col].equals(color) && board[row][col+1].equals(color);
			else
				return false;
		}
		return false;
	}
	
	public boolean dropNull(int col, int row){
		return (col-1>-1 && row+1<ROW && col+1<COL) && (board[row][col-1]==null && board[row+1][col]==null && board[row][col+1]==null);	
	}
	
	public boolean dropPiece(int col, int row, String color){
		board[row][col]=color;
		return dropMatchesNeighbors(col,row,color);
	}

	public boolean catsGame(){
		for(int r=0; r<ROW; r++)
			for(int c=0; c<COL; c++)
				if(dropMatchesNeighbors(c,r,"black") || dropMatchesNeighbors(c,r,"white") || (board[r][c]==null && dropNull(c,r)))
					return false;
		return true;
	}

	public void playGame(){
		boolean p = false;
		boolean c = false;
		boolean gameOver = false;
		
		while(!catsGame() && !gameOver){
			//player
			Scanner kb = new Scanner(System.in);
			System.out.println("What column would you like to drop a piece in?");
			int col = kb.nextInt();
			if(dropLocation(col)==-1){
				System.out.println("Column is full. Choose another one!");
				col = kb.nextInt();
			}
			p = dropPiece(col, dropLocation(col), "black");
			System.out.println(p);
 			
			//comp
			int ranCol = (int)(Math.random()*COL);
			if(dropLocation(ranCol)==-1)
				ranCol = (int)(Math.random()*COL);
			c = dropPiece(ranCol, dropLocation(ranCol), "white");
			System.out.println(getBoard());
			
			System.out.println(catsGame());
			gameOver = p || c;
		}
		
		if(p)
			System.out.println("You won!!");
		if(c)
			System.out.println("Computer won :(");
		//tie
		if(catsGame())
			System.out.println("It's a tie!");
	}
	
	public static void main(String[] args) {
		DropGame a = new DropGame();
	}
}
