import java.util.Scanner;


public class ArmstrongNumbers {
	
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a number");
		int a = kb.nextInt();
		int num = a;
		String numA = "" + a;
		int digits = numA.length();
		int b = 0;
		int c = 0;
		int armstrong = 0;
		for(int i=0; i<digits; i++){
			b=a%10;
			c=a/10;
			a=c;
			armstrong+=Math.pow(b,digits);
		}
		if(num==armstrong)
			System.out.println("ARMSTRONG");
		else
			System.out.println("NOT AN ARMSTRONG");	
	}
	
	

}
