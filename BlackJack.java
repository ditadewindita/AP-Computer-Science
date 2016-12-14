import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class BlackJack implements Game {
	
	private ArrayList<Card> player_cards;
	private ArrayList<Card> dealer_cards;
	private Deck deckUsed;
	
	public BlackJack(Deck d){
		player_cards = new ArrayList<Card>();
		dealer_cards = new ArrayList<Card>();
		deckUsed=d;
		deckUsed.shuffle();
		playGame();
	}
	
	public void showCards(ArrayList<Card> cards){
		String ret = "";
		for(int i=0; i<cards.size(); i++){
			ret+=cards.get(i);
			if(i!=cards.size()-1)
				ret+=", ";
		}
		System.out.println(ret);
	}

	public void sortCards(ArrayList<Card> cards){
		int b = 0;
		Card temp;
		for(int i=1; i<cards.size()-1; i++){
			b=i;
			while(b>0 && cards.get(b).compareTo(cards.get(b-1))<0){
				temp=cards.get(b);
				cards.set(b, cards.get(b-1));
				cards.set(b-1, temp);
				b--;
			}
		}		
	}
	
	public int totalCards(ArrayList<Card> cards){
		int sum = 0;
		int ace = 0;
		for(int i=0; i<cards.size(); i++){
			if(cards.get(i).getValue()>10)
				sum+=10;
			else if(cards.get(i).getValue()==1)
				ace++;
			else
				sum+=cards.get(i).getValue();
		}
		if(sum<21)
			sum+=(ace*11);
		return sum;
			
	}
	
	public void playerTurn(){
		System.out.print("Player cards: ");
		showCards(player_cards);
		Scanner kb = new Scanner(System.in);
		System.out.println("Do you want another card?");
		String line = kb.nextLine();
		while(line.equals("yes") && totalCards(player_cards)<=21){
			player_cards.add(deckUsed.deal());
			System.out.print("Player cards: ");
			showCards(player_cards);
			System.out.println("Do you want another card?");
			line=kb.nextLine();
		}
	}
	
	public void dealerTurn(){
		while(totalCards(dealer_cards)<=17)
			dealer_cards.add(deckUsed.deal());
	}
	
	public String goal(){ return "Total up your cards to 21 before the dealer does"; }
	
	public void setUp(){
		Card[] d = deckUsed.deal(2);
		Card[] p = deckUsed.deal(2);
		for(int i=0; i<2; i++){
			player_cards.add(p[i]);
			dealer_cards.add(d[i]);
		}
	}
	
	public void gameOver(String winner){
		if(winner.equals("tie"))
			System.out.println("Its a tie!");
		else
			System.out.println(winner + " won the game!");
	}
	
	public void playGame(){
		setUp();
		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(dealer_cards.get(0));
		System.out.print("Dealer card: ");
		showCards(dealer);
		playerTurn();
		if(totalCards(player_cards)<21)
			dealerTurn();
		System.out.print("Dealer cards: ");
		showCards(dealer_cards);
		System.out.println("Player total: " + totalCards(player_cards));
		System.out.println("Dealer total: " + totalCards(dealer_cards));
		int p = totalCards(player_cards);
		int d = totalCards(dealer_cards);
		if(p>21){
			if(d>21)
				gameOver("No one");
			else
				gameOver("Dealer");
		}
		else {
			if(p==d)
				gameOver("tie");
			if(p<d || d>21)
				gameOver("Dealer");
			else
				gameOver("Player");
		}
		Scanner kb = new Scanner(System.in);
		if(deckUsed.getSize()>0)
			System.out.println("Do you want to play again?");
			if(kb.next().equals("yes")){
				player_cards.clear();
				dealer_cards.clear();
				playGame();
			}
	}
}
