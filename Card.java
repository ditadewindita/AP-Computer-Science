
public class Card implements Comparable {

	private String rank, suit;
	private int value;
	
	public Card(String r, String s, int v){
		rank=r;
		suit=s;
		value=v;
	}
	
	public String getRank(){ return rank; }
	public String getSuit(){ return suit; }
	public int getValue(){ return value; }


	public boolean equals(Card c){
		if(value==c.value)
			return true;
		return false;
	}
	
	public boolean match(Card c){
		if(rank.equals(c.rank))
			return true;
		return false;
	}
	
	public int compareTo(Object o){
		Card c = (Card)o;
		if(value>c.value)
			return 1;
		if(value<c.value)
			return -1;
		return 0;
	}
	
	public String toString(){
		return rank + " of " + suit;
	}

}
