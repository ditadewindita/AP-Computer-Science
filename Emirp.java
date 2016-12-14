import java.util.Scanner;


public class Emirp {
	
	public static int reverseNum(int n){
		int a = n/10;
		int b = n%10;
		return (b*10)+a;
	}
	
	public static boolean isPrime(int n){
		int c = 0;
		if(n==0)
			return true;
		for(int i=2; i<n-1; i++){
			if(n%i==0)
				c++;
		}
		return c==0;
	}
	
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a range");
		int a = kb.nextInt();
		int b = kb.nextInt();
		int c = 0;
		for(int i=a; i<b; i++){
			if((isPrime(i)==true && isPrime(reverseNum(i))==true) && reverseNum(i)!=i){
				c++;
				System.out.print(i + " ");
			}		
		}
		if(c==0)
			System.out.println("NONE");
	}

}

