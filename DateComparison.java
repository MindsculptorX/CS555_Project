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

	public static boolean beforeDate (String _date1, String _date2) {
		Date date1 = parse(_date1, formatDate);
		Date date2 = parse(_date2, formatDate);

		if (date1.before(date2)) {
			return true;
		} else {
			return false;
		}

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

}
