import java.util.Scanner;


public class Mancala implements Game{
	
	private static final int BOARD_SIZE = 14;
	
	//COMP
	private static final int store1 = BOARD_SIZE/2;
	private static final int store2 = 0;
	
	private int[] board;
	
	public Mancala(int n){
		board = new int[BOARD_SIZE];
		for(int i=0; i<BOARD_SIZE; i++){
			board[i]=n;
		}
		board[store1]=0;
		board[store2]=0;
		playGame();
		
	}
	
	public boolean move(int k){
		int pos = 0;
		int x = board[k];
		int store;
		if(k<store1)
			store = store1;
		else 
			store = store2;
		if(k!=store1 && k!=store2){
			board[k]=0;
			while(x>0){
			 k=k+1;
			 if(k>=BOARD_SIZE)
				 k=0;
			 if((k==store1 || k==store2) && k!=store);//do nothing
			 else
				 board[k]++;
			 x--;
			}
		}
		if(k==store)
			return true;
		return false;
	}
	
	public String goal() {
		return "Have the greatest amount of marbles in your home!";
	}

	public void setUp() {
		System.out.println("*****Welcome to Mancala!******");
		System.out.println("Your home is on the right at position 7 and the computer's is on the left at position 0.");
		System.out.println(toString());
	}

	public void gameOver(String winner) {
		if(winner.equals("tie"))
			System.out.println("Its a tie!");
		else
			System.out.println(winner + " won the game!");
		
	}

	
	public boolean playerTurn(){
		Scanner kb = new Scanner(System.in);
		System.out.println("What pit would you like to take from?");
		int x = kb.nextInt();
		boolean p = move(x);
		System.out.println(toString());
		return p;
	}
	
	public boolean compTurn(){
		int ran = (int)((Math.random()*6)+8);
		boolean p = move(ran);
		System.out.println("Computer is taking its turn....");
		System.out.println(toString());
		return p;
	}
	
	public boolean isEmpty(){
		int c = 0;
		for(int i=0; i<BOARD_SIZE; i++)
			if(board[i]!=0)
				c++;
		
		return c==2;
	}
	
	public void playGame(){
		setUp();
		boolean empty = isEmpty();
		boolean player = playerTurn();
		boolean comp = false;
		while(!empty){
			if(!player)
				comp = compTurn();
			else if (!comp);
				player = playerTurn();
		}
		
		
		//WIN
		int p = board[store2];
		int c = board[store1];
		if(p>c)
			gameOver("Player");
		if(p<c)
			gameOver("Computer");
		else
			gameOver("tie");
		
	}
	
	public String toString(){
		String ret = " ";
		for(int i=1; i<BOARD_SIZE; i++){
			if(i==store1)
				ret+="\n" + board[store2] + "\t    " + board[store1] + "\n ";
			else
				ret+=board[i] + " ";
		}
		return ret;
		
		
	}
	
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.println("How many marbles would you like in each pit?");
		int x = kb.nextInt();
		Mancala m = new Mancala(x);		
	}

}
