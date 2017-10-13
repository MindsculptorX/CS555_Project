import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ParseGEDCOMFile {

	public static void main (String [] args) {
		HashMap<Integer,Individual> indiList = new HashMap<Integer,Individual>();
		HashMap<Integer,Family> famList = new HashMap<Integer,Family>();
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
							argument = splitParts[2];
							if (DateComparison.beforeToday(argument)){
								int year = Integer.parseInt(argument.split(" ")[2]);
								indi.setAge(2017 - year);
								indi.setBirthday(argument);
								indiList.put(key, indi);
							}
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
							if (DateComparison.beforeToday(argument)){
								if (DateComparison.beforeDate(indi.getBirthday(), argument)){
									int year = Integer.parseInt(argument.split(" ")[2]);
									indi.setAge(year - 2017 + indi.getAge());
									indi.setDeath(argument);
									indiList.put(key, indi);
								}
							}
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
							if (DateComparison.beforeToday(argument)){
								fam.setMarried(argument);
								famList.put(key, fam);
							}
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
							if (DateComparison.beforeToday(argument)){
								if(marriageBeforeDivorce(fam, argument)){
								fam.setDivorced(argument);
								famList.put(key, fam);
								}
							}
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
		//Get the age for each Indi:
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
		System.out.println("====== ============ ============ ============ ================== ========= ================== ================\n");
		for(int i = 0;i< 1000;i++){
			if(famList.containsKey(i)){
				Family fam = famList.get(i);
				if (!marriageBeforeDivorce(fam, fam.getMarried())) {
					System.out.println("ERROR: FAMILY: " + fam.getId() + " Divorce " + fam.getDivorced() + " before married " + fam.getMarried());
				}

			}
		}
	}

	public static boolean marriageBeforeDivorce(Family fam,String date){
		date = fam.transDate(date);
		String[] temp = date.split("-");
		int year = Integer.parseInt(temp[0]);
		int month = Integer.parseInt(temp[1]);
		int day = Integer.parseInt(temp[2]);
		if(year<fam.getmYear()){
			return false;
		}else if(year>fam.getmYear()){
			return true;
		}else{
			if(month<fam.getmMonth()){
				return false;
			}else if(month>fam.getdMonth()){
				return true;
			}else{
				if(day<=fam.getmDay()){
					return false;
				}else{
					return true;
				}
			}
		}
	}

	public static boolean divorceBeforeDeath(Family fam,Individual indi){
		//If not divorce return false;
		if(fam.getDivorced().equals("N/A")){
			return false;
		}
		//if divorce but alive return true
		if(indi.isAlive()){
			return true;
		}
		//dead and divorced
		int deadYear = Integer.parseInt(indi.getDeath().substring(0,4));
		int deadMonth = Integer.parseInt(indi.getDeath().substring(5,7));
		int deadDay = Integer.parseInt(indi.getDeath().substring(8,10));

		int divorceYear = Integer.parseInt(fam.getDivorced().substring(0,4));
		int divorceMonth= Integer.parseInt(fam.getDivorced().substring(5,7));
		int divorceDay = Integer.parseInt(fam.getDivorced().substring(8,10));

		if(divorceYear<deadYear){return true;}
		if(divorceYear>deadYear){return false;}
		//Same year↑
		if(divorceMonth<deadMonth){return true;}
		if(divorceMonth>deadMonth){return false;}
		//Same month↑
		if(divorceDay<deadDay){return true;}
		else{return false;}//Same day included

	}
}
