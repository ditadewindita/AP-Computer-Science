import java.util.Scanner;

public class Calendar {

	private int year;
	private String birthday;
	private String thanksgiving;
	private String newYear;
	private String otherHoliday;
	
	public Calendar(int y){
		year=y;
		birthday = getBirthday();
		thanksgiving = getThanksgiving();
		newYear = getNewYear();
		otherHoliday = getVeteransDay();
	}
	
	public String getThanksgiving(){ 
		int n = getWeekDayNum(11,1);
		int offset = 0;
		if(n<=4){
			offset=Math.abs(n-4)+1;
		}
		if(n>4){
			offset=(6-n)+6;
		}
		return "Thanksgiving will be on Thursday, 11/" + (offset+21); 
	}
	
	public String getNewYear(){ 
		String n = getWeekDay(1,1);
		return "The New Year will be on " + n + ", 1/1"; 
	}
	
	public String getBirthday(){ 
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter your birthday month");
		int m = kb.nextInt();
		System.out.println("Enter the day");
		int d = kb.nextInt();
		return "Your birthday will be on " + getWeekDay(m,d) + ", " + m + "/" + d; 
	}
	
	public String getVeteransDay(){
		String n = getWeekDay(11,11);
		return "Veterans day is on " + n + ", 11/11";
	}

	public String getWeekDay(int m, int d){
		int last = year%100;
		int first = year/100;
		int a = 2*(3-(first%4));
		int b = last + (last/4);
		int c = lookUpMonth(m);
		int fin = (a+b+c+d)%7;
		String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		return day[fin];
	}
	
	public int getWeekDayNum(int m, int d){
		int last = year%100;
		int first = year/100;
		int a = 2*(3-(first%4));
		int b = last + (last/4);
		int c = lookUpMonth(m);
		int fin = (a+b+c+d)%7;
		return fin;	
	}
	
	public void printCalendarInfo(){
		System.out.println("In " + year + ":");
		System.out.println(birthday);
		System.out.println(newYear);
		System.out.println(thanksgiving);
		System.out.println(otherHoliday);	
	}
	
	public int lookUpMonth(int month){
		int val;
		switch(month){
			case 1: 
			case 10: val = 0; break;
			case 2: 
			case 3:
			case 11: val = 3; break;
			case 4:
			case 7: val = 6; break;
			case 5: val = 1; break;
			case 6: val = 4; break;
			case 8: val = 2; break;
			default: val = 5;
		}
		return val;
	}
}
