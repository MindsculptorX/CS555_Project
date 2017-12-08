import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ParseGEDCOMFile {
	public static HashMap<Integer,Individual> indiList;
	public static HashMap<Integer,Family> famList;
	public static ArrayList<String> uniqueId;
	public static ArrayList<String> repeatId;

	public static void setMap() {
		indiList = new HashMap<Integer,Individual>();
		famList = new HashMap<Integer,Family>();
		uniqueId = new ArrayList<String>();
		repeatId = new ArrayList<String>();
		try {
			File gedcom = new File ("gedcomFile.ged"); //must specify path
			BufferedReader reader = new BufferedReader(new FileReader(gedcom));
			String readLine = "";
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
						if(!uniqueId.contains(id)){
							uniqueId.add(id);
						}else {
							repeatId.add(id);
						}

				}else if(splitParts[2].equals("FAM")){
						Family fam = new Family();
						String id = splitParts[1].replaceAll("@", "");
						key = Integer.parseInt(id.replaceAll("F", ""));
						fam.setId(id);
						famList.put(key, fam);
						if(!uniqueId.contains(id)){
							uniqueId.add(id);
						}else {
							repeatId.add(id);
						}
			}else{
			}
			}
			}else if(level == 1){
				tag = splitParts[1];
				if(isValid.isTagValid(level,tag)){
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
							argument = ReportingTool4.partialDate(splitParts[2]);
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
							argument = ReportingTool4.partialDate(splitParts[2]);
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
							argument = ReportingTool4.partialDate(splitParts[2]);
							fam.setMarried(argument);
							famList.put(key, fam);
						}
					}
				}else if(tag.equals("HUSB")){
					Family fam = famList.get(key);
					argument = splitParts[2].replace("@", "");
					fam.setHusbandId(argument);
					int id = Integer.parseInt(argument.replaceAll("I", ""));
					Individual indi = indiList.get(id);
					String husName = indi.getName();
					fam.setHusbandName(husName);
					famList.put(key, fam);
				}else if(tag.equals("WIFE")){
					Family fam = famList.get(key);
					argument = splitParts[2].replace("@", "");
					int id = Integer.parseInt(argument.replaceAll("I", ""));
					fam.setWifeId(argument);
					Individual indi = indiList.get(id);
					String wifName = indi.getName();
					fam.setWifeName(wifName);
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
							argument = ReportingTool4.partialDate(splitParts[2]);
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
//		//Get the age for each Indi:
		for(int i = 0;i< 5000;i++){
			if(indiList.containsKey(i)){
				Individual indi = indiList.get(i);
				int birthYear = Integer.parseInt(indi.getBirthday().substring(0, 4));
				if(indi.isAlive()){
					indi.setAge(2017-birthYear);
				}else{
					int deadYear = Integer.parseInt(indi.getDeath().substring(0, 4));
					indi.setAge(deadYear-birthYear);
				}
			}
		}
	}
}
