import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateComparison {
	static Calendar calendar = Calendar.getInstance();
	static SimpleDateFormat formatDate[] = new SimpleDateFormat[] {new SimpleDateFormat ("dd MMM yyyy"), new SimpleDateFormat ("yyyy-MM-dd")};
	static Date today = calendar.getTime();

	public static boolean beforeToday (String _date1) {
		Date date = parse(_date1, formatDate);

		if (date.before(today)) {
			return true;
		} else {
			return false;
		}

	}
	
	public static boolean illegitimateDate (String _date) {
		SimpleDateFormat format = new SimpleDateFormat ("dd MMM yyyy");
		format.setLenient(false);
		Date date;
		
		try {
			date = format.parse(_date);
			return true;
		} catch (ParseException e) {
			//System.out.println("Invalid Date");
			return false;
		}
	}

	public static boolean beforeDate (String _date1, String _date2) {
		Date date1 = parse(_date1, formatDate);
		Date date2 = parse(_date2, formatDate);
		
		
		if (date1.before(date2)) {
			return true;
		} else {
			return false;
		}

	}
	public static long differentValueDate(String _date1,String _date2){
		Date date1 = parse(_date1, formatDate);
		Date date2 = parse(_date2, formatDate);
		long day = (date1.getTime()-date2.getTime())/(24*60*60*1000)>0 ? (date1.getTime()-date2.getTime())/(24*60*60*1000): 
			   (date2.getTime()-date1.getTime())/(24*60*60*1000);
		return day;
		
	}
	public static Date parse (String _date, DateFormat...formatters) {
		Date date = null;
		for (DateFormat formatter: formatters) {
			try {
				date =formatter.parse(_date);
			} catch (ParseException e) {
				
			}
		}

		return date;
	}
	
	public static int getYearFromDataStr(String date){
		return Integer.parseInt(date.split("-")[0]);
	}
	public static int getMonthFromDataStr(String date){
		return Integer.parseInt(date.split("-")[1]);
	}
	public static int getDayFromDataStr(String date){
		return Integer.parseInt(date.split("-")[2]);
	}
	
	public static String getTodayDate(){
		return "2017-11-04";
	}
	public static int getDateGap(String date1,String date2){
		//1990-01-01  -->1990-01-10  //return 9
		int ans = 0;
		ans += (getYearFromDataStr(date2) - getYearFromDataStr(date1)) * 365;
		ans += (getMonthFromDataStr(date2) - getMonthFromDataStr(date1)) * 30;
		ans += (getDayFromDataStr(date2) - getDayFromDataStr(date1));
		return ans;
	}

}
