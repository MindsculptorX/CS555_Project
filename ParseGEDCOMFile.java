
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParseGEDCOMFile {
	public static String [] level0Tag =  {"INDI", "FAM", "HEAD", "TRLR", "NOTE"};
	public static String [] level1Tag = {"NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"};
	public static String [] level2Tag = {"DATE"};
	public static String valid = "";
	
	public static void main (String [] args) {
		HashMap<Integer,Individual> indiList = new HashMap<Integer,Individual>();
		HashMap<Integer,Family> famList = new HashMap<Integer,Family>();
		try {
			File gedcom = new File ("src/input"); //must specify path			
			BufferedReader reader = new BufferedReader(new FileReader(gedcom));		
			String readLine = "";
			Object obj = new Object();
			int key=0;
			while ((readLine = reader.readLine()) != null) {
				String splitParts [] = readLine.split(" ", 3);
				int level;
				String tag = "";
				String argument = "";	
				level = Integer.parseInt(splitParts[0]);
				if(level == 0){
					if(splitParts.length > 2){
					if(splitParts[2].equals("INDI")){
						Individual indi = new Individual();
						String id = splitParts[1].replaceAll("@", "");
						key = Integer.parseInt(id.replaceAll("I", ""));
						indi.setId(id);
						indiList.put(key, indi);
						
				}else if(splitParts[2].equals("FAM")){
						Family fam = new Family();
						String id = splitParts[1].replaceAll("@", "");
						key = Integer.parseInt(id.replaceAll("F", ""));
						fam.setId(id);
						famList.put(key, fam);

			}else{
			}	
			}			
			}else if(level == 1){
				tag = splitParts[1];
				if(isValid(level,tag)){
				if(tag.equals("NAME")){
					Individual indi = indiList.get(key);
					argument = splitParts[2];
					indi.setName(argument);
					indiList.put(key, indi);
				}else if(tag.equals("SEX")){
					Individual indi = indiList.get(key);
					argument = splitParts[2];
					indi.setGender(argument);
				}else if(tag.equals("BIRT")){
					Individual indi = indiList.get(key);
					if((readLine = reader.readLine()) != null){
						splitParts = readLine.split(" ",3);
						level = Integer.parseInt(splitParts[0]);
						tag = splitParts[1];
						if(level == 2 && tag.equals("DATE")){
							argument = splitParts[2];
							int year = Integer.parseInt(argument.split(" ")[2]);
							indi.setAge(2017 - year);
							indi.setBirthday(argument);
							indiList.put(key, indi);
						}else{
						}
					}
				}else if(tag.equals("DEAT")){
					Individual indi = indiList.get(key);
					if((readLine = reader.readLine()) != null){
						splitParts = readLine.split(" ",3);
						level = Integer.parseInt(splitParts[0]);
						tag = splitParts[1];
						if(level == 2 && tag.equals("DATE")){
							argument = splitParts[2];
							int year = Integer.parseInt(argument.split(" ")[2]);
							indi.setAge(year - 2017 + indi.getAge());
							indi.setDeath(argument);
							indiList.put(key, indi);
						}else{
						}
					}
				}else if(tag.equals("FAMC")){
					Individual indi = indiList.get(key);
					argument = splitParts[2];
					indi.setFamcId(argument.replaceAll("@", ""));
					indiList.put(key, indi);
				}else if(tag.equals("FAMS")){
					Individual indi = indiList.get(key);
					argument = splitParts[2];
					indi.setFamsId(argument.replaceAll("@", ""));
					indiList.put(key, indi);
				}else if(tag.equals("MARR")){
					Family fam = famList.get(key);
					if((readLine = reader.readLine()) != null){
						splitParts = readLine.split(" ",3);
						level = Integer.parseInt(splitParts[0]);
						tag = splitParts[1];
						if(level == 2 && tag.equals("DATE")){
							argument = splitParts[2];
							fam.setMarried(argument);
							famList.put(key, fam);
						}
					}
				}else if(tag.equals("HUSB")){
					Family fam = famList.get(key);
					argument = splitParts[2].replace("@", "");
					fam.setHusId(argument);
					int id = Integer.parseInt(argument.replaceAll("I", ""));
					Individual indi = indiList.get(id);
					String husName = indi.getName();
					fam.setHusName(husName);
					famList.put(key, fam);
				}else if(tag.equals("WIFE")){
					Family fam = famList.get(key);
					argument = splitParts[2].replace("@", "");
					int id = Integer.parseInt(argument.replaceAll("I", ""));
					fam.setWifId(argument);
					Individual indi = indiList.get(id);
					String wifName = indi.getName();
					fam.setWifName(wifName);
					famList.put(key, fam);
				}else if(tag.equals("CHIL")){
					Family fam = famList.get(key);
					argument = splitParts[2].replace("@", "");
					ArrayList children =fam.getChildren();
					children.add(argument);
					fam.setChildren(children);
					famList.put(key, fam);
				}else if(tag.equals("DIV")){
					Family fam = famList.get(key);
					if((readLine = reader.readLine()) != null){
						splitParts = readLine.split(" ",3);
						level = Integer.parseInt(splitParts[0]);
						tag = splitParts[1];
						if(level == 2 && tag.equals("DATE")){
							argument = splitParts[2];
							fam.setDivorced(argument);
							famList.put(key, fam);
						}else{
							break;
						}
					}
				}
				}
			}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Individuals");
		System.out.println("  ID          Name         Gender    Birthday    Age   Alive     Death       Child     Spouse ");
		System.out.println("====== ================== ======== ============ ===== ======= ============ ========= =========");
		for(int i = 0;i< 5000;i++){
			if(indiList.containsKey(i)){
				Individual indi = indiList.get(i);
				System.out.printf("  %-4s   %-16s    %-5s  %-11s  %-4s  %-6s  %-11s    %-6s    %-6s\n", 
						indi.getId(), indi.getName(), indi.getGender(), indi.getBirthday(),indi.getAge(), indi.getDeath() == "N/A" ? "True" : "False", indi.getDeath() == "N/A" ? "   N/A     " : indi.getDeath(), indi.getFamcId(), indi.getFamsId());
			}
		}
		System.out.println("====== ================== ======== ============ ===== ======= ============ ========= =========\n");
		
		System.out.println("Families");
		System.out.println("  ID     Married      Divorced    Husband ID     Husband Name     Wife ID      Wife Name          Children    ");
		System.out.println("====== ============ ============ ============ ================== ========= ================== ================");
		for(int i = 0;i< 1000;i++){
			if(famList.containsKey(i)){
				Family fam = famList.get(i);
				System.out.printf("  %-4s  %-11s  %-11s     %-8s  %-17s    %-6s   %-16s    %-13s\n", 
						fam.getId(), fam.getMarried(), fam.getDivorced() == "N/A" ? "   N/A    " : fam.getDivorced(), fam.getHusId(), fam.getHusName(), fam.getWifId(), fam.getWifName(), fam.getChildren());
			}
		}
		System.out.println("====== ============ ============ ============ ================== ========= ================== ================");
	}
	
	public static boolean isValid(Integer _level, String _tag) {
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
	
}
	 class Individual{
			private String id;
			private String name;
			private int age;
			private String gender;
			private String birthday;
			private boolean Alive;
			private String death = "N/A";
			private String famcId = "N/A";
			private String famsId = "N/A";
			public Individual(){
				setAlive(true);
			}
			public Individual(String id,String name,String gender,String birthday){
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
			public String getFamcId() {
				return famcId;
			}
			public void setFamcId(String famcId) {
				this.famcId = famcId;
			}
			public String getFamsId() {
				return famsId;
			}
			public void setFamsId(String famsId) {
				this.famsId = famsId;
			}
			public String transDate(String date){
				String split[] = date.split(" ");
				String day = split[0];
				int temp = Integer.parseInt(day);
				if(temp<10){
					day="0"+day;
				}
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
			@Override
			public String toString() {
				return "Individual [id=" + id + ", name=" + name + ", age="
						+ age + ", gender=" + gender + ", birthday=" + birthday
						+ ", Alive=" + Alive + ", death=" + death + ", famcId="
						+ famcId + ", famsId=" + famsId + "]";
			}
			
				
		}
		 class Family{
			 private String id;
			 private String married;
			 private String divorced = "N/A";
			 private String husId;
			 private String wifId;
			 private String husName;
			 private String wifName;
			 private ArrayList<String> children;
			public Family(){
				setChildren(new ArrayList<String>());
			}
			public Family(String id,String married,String husId,String wifId){
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
				int temp = Integer.parseInt(day);
				if(temp<10){
					day="0"+day;
				}
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
			@Override
			public String toString() {
				return "Family [id=" + id + ", married=" + married
						+ ", divorced=" + divorced + ", husId=" + husId
						+ ", wifId=" + wifId + ", husName=" + husName
						+ ", wifName=" + wifName + ", children=" + children
						+ "]";
			}
			
		 }
