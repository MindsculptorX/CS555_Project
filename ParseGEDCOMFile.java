
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseGEDCOMFile {
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
	 class individual{
			private String id;
			private String name;
			private int age;
			private String gender;
			private String birthday;
			private boolean Alive;
			private String death;
			private ArrayList<String> child;
			private ArrayList<String> Spouse;
			public individual(){
				setAlive(true);
			}
			public individual(String id,String name,String gender,String birthday){
				setId(id);
				setName(name);
				setGender(gender);
				setBirthday(transDate(birthday));
				setAlive(true);			
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public int getAge() {
				return age;
			}
			public void setAge(int age) {
				this.age = age;
			}
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
				this.gender = gender;
			}
			public String getBirthday() {
				return birthday;
			}
			public void setBirthday(String birthday) {
				this.birthday = transDate(birthday);
			}
			public boolean isAlive() {
				return Alive;
			}
			public void setAlive(boolean alive) {
				Alive = alive;
			}
			public String getDeath() {
				return death;
			}
			public void setDeath(String death) {
				this.death = transDate(death);
			}

			public ArrayList<String> getChild() {
				return child;
			}
			public void setChild(ArrayList<String> child) {
				this.child = child;
			}
			public ArrayList<String> getSpouse() {
				return Spouse;
			}
			public void setSpouse(ArrayList<String> spouse) {
				Spouse = spouse;
			}
			public String transDate(String date){
				String split[] = date.split(" ");
				String day = split[0];
				String month=null;
				if(split[1].equals("JAN")){
					month="01";
				}else if(split[1].equals("FEB")){
					month="02";
				}else if(split[1].equals("MAR")){
					month="03";
				}else if(split[1].equals("APR")){
					month="04";
				}else if(split[1].equals("MAY")){
					month="05";
				}else if(split[1].equals("JUN")){
					month="06";
				}else if(split[1].equals("JUL")){
					month="07";
				}else if(split[1].equals("AUG")){
					month="08";
				}else if(split[1].equals("SEP")){
					month="09";
				}else if(split[1].equals("OCT")){
					month="10";
				}else if(split[1].equals("NOV")){
					month="11";
				}else if(split[1].equals("DEC")){
					month="12";
				}
				String year = split[2];
				String d = year+"-"+month+"-"+day;	
				return d;
			}
				
		}
		 class family{
			 private String id;
			 private String married;
			 private String divorced;
			 private String husId;
			 private String wifId;
			 private String husName;
			 private String wifName;
			 private ArrayList<String> children;
			public family(){
				
			}
			public family(String id,String married,String husId,String wifId){
				setId(id);
				setMarried(married);
				setHusId(husId);
				setWifId(wifId);
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getMarried() {
				return married;
			}
			public void setMarried(String married) {
				this.married = transDate(married);
			}
			public String getDivorced() {
				return divorced;
			}
			public void setDivorced(String divorced) {
				this.divorced = transDate(divorced);
			}
			public String getHusId() {
				return husId;
			}
			public void setHusId(String husId) {
				this.husId = husId;
			}
			public String getWifId() {
				return wifId;
			}
			public void setWifId(String wifId) {
				this.wifId = wifId;
			}
			public String getHusName() {
				return husName;
			}
			public void setHusName(String husName) {
				this.husName = husName;
			}
			public String getWifName() {
				return wifName;
			}
			public void setWifName(String wifName) {
				this.wifName = wifName;
			}
			
			public ArrayList<String> getChildren() {
				return children;
			}
			public void setChildren(ArrayList<String> children) {
				this.children = children;
			}
			public String transDate(String date){
				String split[] = date.split(" ");
				String day = split[0];
				String month=null;
				if(split[1].equals("JAN")){
					month="01";
				}else if(split[1].equals("FEB")){
					month="02";
				}else if(split[1].equals("MAR")){
					month="03";
				}else if(split[1].equals("APR")){
					month="04";
				}else if(split[1].equals("MAY")){
					month="05";
				}else if(split[1].equals("JUN")){
					month="06";
				}else if(split[1].equals("JUL")){
					month="07";
				}else if(split[1].equals("AUG")){
					month="08";
				}else if(split[1].equals("SEP")){
					month="09";
				}else if(split[1].equals("OCT")){
					month="10";
				}else if(split[1].equals("NOV")){
					month="11";
				}else if(split[1].equals("DEC")){
					month="12";
				}
				String year = split[2];
				String d = year+"-"+month+"-"+day;	
				return d;
			} 
		 }
