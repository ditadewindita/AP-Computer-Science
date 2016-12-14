
public class Recursion {
	
	public static void printPat(int n){
		if(n==0)
			System.out.print("**");
		else {
			if(n%2!=0)
				System.out.print(n);
			printPat(n-1);
			if(n%2==0)
				System.out.print(n);
		}
	}
	
	public static boolean isPalindrome(String str){
		int x = str.length();
		if(x==0 || x==1)
			return true;
		if(str.substring(0,1).equals(str.substring(x-1)))
			return isPalindrome(str.substring(1,x-1));
		return false;
	}
	
	public static void printPat2(int n){
		if(n>0){
			printPat2(n-1);
			for(int i=0; i<n; i++)
				System.out.print(n);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		printPat(6);
		System.out.println();
		System.out.println(isPalindrome("racecar"));
		printPat2(4);
	}

}
