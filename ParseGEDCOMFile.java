
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Project2 {
	public static String [] level0Tag =  {"INDI", "FAM", "HEAD", "TRLR", "NOTE"};
	public static String [] level1Tag = {"NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"};
	public static String [] level2Tag = {"DATE"};
	public static String valid = "";
	
	public static void main (String [] args) {

		try {
			File gedcom = new File ("/Users/RANE/Documents/StevensIT/Fall2017/CS555/toParseGEDCOM.ged");
			
			BufferedReader reader = new BufferedReader(new FileReader(gedcom));
			
			String readLine = "";
			
			while ((readLine = reader.readLine()) != null) {
				String splitParts [] = readLine.split(" ", 3);
				int level;
				String tag = "";
				String argument = "";
				String tempTag = splitParts[1];
				
				level = Integer.parseInt(splitParts[0]);
				
				if (splitParts.length == 2) {
					tag = splitParts[1];
					isValid(level, tag);
				} else if (splitParts.length > 2) {
					tag = splitParts[1];
					argument = splitParts[2];
					if (level == 0) {
						if (level == 0) {
						if (splitParts[1].length() > 4) {
							tag = splitParts[2];
							isValid(level, tag);
							argument = splitParts[1];
						} else {
							if(splitParts[1].length() == 4) {
								String [] tempTag = splitParts[1].split("");
								if (tempTag[0].equalsIgnoreCase("@")) {
									tag = splitParts[2];
									isValid(level, tag);
									argument = splitParts[1];
								}
							}
						}
					}
					if (level == 1) {
						isValid(level, tag);
					}
					if (level == 2) {
						isValid(level, tag);
					}
				}
				
				System.out.println("-->" + readLine);
				System.out.println("<--" + level + "|" + tag + "|" + valid + "|" + argument);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void isValid(Integer _level, String _tag) {
		int level = _level; 
		String tempTag = _tag;
		
		if (level == 0) {
			List<String> list = Arrays.asList(level0Tag);
			if (list.contains(tempTag)) {
				valid = "Y";
			} else {
				valid = "N";
			}
		}
		if (level == 1) {
			List<String> list = Arrays.asList(level1Tag);
			if (list.contains(tempTag)) {
				valid = "Y";
			} else {
				valid = "N";
			}
		}
		if (level == 2) {
			List<String> list = Arrays.asList(level2Tag);
			if (list.contains(tempTag)) {
				valid = "Y";
			} else {
				valid = "N";
			}
		}
	}
	
}

