/**
	- 1 Jan 1900 -> MONDAY
	- 30 days: 4, 6, 9, 11
	- 31 days: 1, 3, 5, 7, 8, 10, 12
	- 28 or 29 days: 2
	
	LEAP YEAR
		- occurs on any year even divisible by 4,
		- but not on a century unless it is divisible by 400
		
	Goal
		number of Sundays fell on the first of the month
		from (1 Jan 1901) to (31 Dec 2000)
*/

public class Problem19{
	public static void main(String[] args){
		/**
			1. find what day (1 Jan 1901) is
			2. for each year -> check leap year
			3. add # of days for each month and
				see if the first day of each month is sunday or not
		*/
		// 0: monday, 1: tuesday, ..., 6: sunday
		// 1900 is a century and not divisible by 400
		// Hence, not a leap year -> has 365 days total
		int year = 1901;
		int month = 1;
		int today = 365 % 7;
		boolean leapYear = false;
		
		int sundays = 0;
		
		//from (1 Jan 1901) to (31 Dec 2000)
		while(!(year == 2001 && month == 1)){
			leapYear = isLeapYear(year);
			int days;
			if(month == 2){
				days = 28;
				if(leapYear){
					days = 29;
				}
			}else if(month == 4 || month == 6 || month == 9 || month == 11){
				days = 30;
			}else{
				days = 31;
			}
			month++;
			if(month == 13){
				month = 1;
				year++;
			}
			today = (today + days) % 7;
			if(today == 6){
				System.out.println("Sunday: " + year + ", " + month);
				sundays++;
			}
		}
		System.out.println(sundays);
	}
	
	private static boolean isLeapYear(int year){
		boolean leapYear;
		if(year % 4 == 0){
			if((year % 100) == 0 && (year % 400) != 0){
				leapYear = false;
			}else{
				leapYear = true;
			}
		}else{
			leapYear = false;
		}
		return leapYear;
	}
}