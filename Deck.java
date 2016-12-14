
public class Deck {
	
	private Card[] deck;
	private Card[] copy;
	private int size;
	
	public Deck(){
		size=52;
		deck = new Card[size];
		String[] suit = {"diamonds", "hearts", "spades", "clubs"};
		String[] rank = {"ace", "two", "three", "four", "five", "six", 
				"seven", "eight", "nine", "ten", "jack", "queen", "king"};
		int r = 0;
		for(int d=0; d<52; d++){
			deck[d]=new Card(rank[r], suit[d/13], r+1);
			r++;
			if(r==13)
				r=0;
		}	
		size=deck.length;
		shuffleFullDeck();
	}
	
	public Deck(String[] rank, String[] suit, int[] val){
		size=rank.length*suit.length;
		deck=new Card[size];
		int r = 0;
		for(int d=0; d<size; d++){
			deck[d]=new Card(rank[r], suit[d/rank.length], val[r]);
			r++;
			if(r==rank.length)
				r=0;
		}	
		size=deck.length;
		shuffleFullDeck();
	}
	
	public void shuffle(){
		for(int i=0; i<100; i++){
			int one = (int)(Math.random()*size);
			int two = (int)(Math.random()*size);
			Card temp = deck[one];
			deck[one]=deck[two];
			deck[two]=temp;
		}
	}
	
	public void shuffleFullDeck(){
		for(int i=0; i<size; i++)
			if(deck[i]==null)
				deck[i]=copy[i];
		shuffle();
	}
	
	public int getSize(){ return size; }
	
	public boolean isEmpty(){
		if(size==0)
			return true;
		return false;
	}
	
	public void showDiscardedCards(){
		String ret = "";
		for(int i=size-1; i<deck.length; i++)
			ret+=deck[i];
		System.out.println(ret);
		
	}
	
	public Card[] deal(int n){
		if(size-n<0) throw new IllegalArgumentException();
		Card[] ret = new Card[n];
		for(int i=0; i<n; i++){
			ret[i]=deck[size-1];
			size--;
		}
		return ret;
		
	}
	
	public Card deal(){
		Card[] ret = deal(1);
		return ret[0];
	}
	
	
	public String toString(){
		String temp = "";
		for(int i=0; i<size; i++){
			temp+=deck[i];
			if(i!=size-1)
				temp+=", ";	
		}
		return temp;
	}
	
}
