import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class isValid {
	public static String [] level0Tag =  {"INDI", "FAM", "HEAD", "TRLR", "NOTE"};
	public static String [] level1Tag = {"NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"};
	public static String [] level2Tag = {"DATE"};

	public static boolean isTagValid(Integer _level, String _tag) {
		int level = _level;
		String tempTag = _tag;

		if (level == 0) {
			List<String> list = Arrays.asList(level0Tag);
			if (list.contains(tempTag)) {
				return true;
			} else {
				return false;
			}
		}
		if (level == 1) {
			List<String> list = Arrays.asList(level1Tag);
			if (list.contains(tempTag)) {
				return true;
			} else {
				return false;
			}
		}
		if (level == 2) {
			List<String> list = Arrays.asList(level2Tag);
			if (list.contains(tempTag)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static boolean dateBeforeToday(String parsedDate) {
		int year, month, date;
		Calendar today = new GregorianCalendar();

		String temp[] = parsedDate.split(" ");
		int passedYear = Integer.parseInt(temp[2]);
		int passedMonth = 0;
		int passedDate = Integer.parseInt(temp[0]);
		String monthString = temp[1];

		switch (monthString) {
		case "JAN":		passedMonth = 1;
				break;
		case "FEB":		passedMonth = 2;
				break;
		case "MAR":		passedMonth = 3;
				break;
		case "APR":		passedMonth = 4;
				break;
		case "MAY":		passedMonth = 5;
				break;
		case "JUN":		passedMonth = 6;
				break;
		case "JUL":		passedMonth = 7;
				break;
		case "AUG":		passedMonth = 8;
				break;
		case "SEP":		passedMonth = 9;
				break;
		case "OCT":		passedMonth = 10;
				break;
		case "NOV":		passedMonth = 11;
				break;
		case "DEC":		passedMonth = 12;
				break;
		}


		year = today.get(GregorianCalendar.YEAR);
		month = today.get(GregorianCalendar.MONTH) + 1;
		date = today.get(GregorianCalendar.DAY_OF_MONTH);

		if (passedYear < year) {
			return true;
		} else {
			if (passedMonth < month) {
				return true;
			} else {
				if (passedDate < date) {
					return true;
				} else {
					return false;
				}
			}
		}

	}

}
