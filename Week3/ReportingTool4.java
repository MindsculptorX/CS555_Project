import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class ReportingTool4 {
	//ZHIQI XIONG
	public static ArrayList<String> listLivingSingle() {
		ArrayList<String> livingSingle = new ArrayList<String>();
		HashMap<Integer, Individual> indiList = ParseGEDCOMFile.indiList;
		for (Integer key : indiList.keySet()) {
			Individual indi = indiList.get(key);
			if (indi.getAge() <= 30) {
				continue;
			}
			if (indi.getFamsId().equals("N/A")) {
				livingSingle.add(indi.getId());
			}
		}
		return livingSingle;
	}
	
	public static ArrayList<String> listOrphans() {
		ArrayList<String> Orphans = new ArrayList<String>();
		HashMap<Integer, Family> famList = ParseGEDCOMFile.famList;
		for (Integer key : famList.keySet()) {
			Family fam = famList.get(key);
			Individual father = ReportingTool.getIndiById(fam.getHusbandId());
			Individual mother = ReportingTool.getIndiById(fam.getWifeId());
			if (father.isAlive() || mother.isAlive()) {
				continue;
			}
			Orphans.addAll(fam.getChildren());
		}
		return Orphans;
	}
	
	
	//↓Xi's Sprint 4 code
	public static ArrayList<String> listMultipleBirths(){
		ArrayList<String> answer = new ArrayList<String>();
		HashMap<String,ArrayList<String>> mp = new HashMap<String,ArrayList<String>>();
		for(Family fam : ParseGEDCOMFile.famList.values()){
			//clear
			mp.clear();
			//Insert children
			for(String childId : fam.getChildren()){
				String bir = ReportingTool.getIndiById(childId).getBirthday();
				if(mp.get(bir) == null){
					mp.put(bir, new ArrayList<>());
				}
				mp.get(bir).add(childId);
			}
			//Find multi 
			for(ArrayList<String> Ids : mp.values()){
				if(Ids.size()>1){
					System.out.println(Ids);
					answer.addAll(Ids);
				}
			}	
		}
		return answer;
	}
	public static ArrayList<String> listLargeAgeDiff(){
		ArrayList<String> answer = new ArrayList<String>();
		for(Family fam : ParseGEDCOMFile.famList.values()){
			int husAge = ReportingTool.getIndiById(fam.getHusbandId()).getAge();
			int wifeAge = ReportingTool.getIndiById(fam.getWifeId()).getAge();
			if(wifeAge > 2*husAge || wifeAge*2 < husAge){
				answer.add(fam.getHusbandId());
				answer.add(fam.getWifeId());
			}
		}
		return answer;
	}
	
	//↓Leo code
	
	public static ArrayList<String> listUpcomingAnniversaries(){
		ArrayList<String> answer = new ArrayList<String>();
		for(Family fam : ParseGEDCOMFile.famList.values()){
			String Today = DateComparison.getTodayDate();
			String nextAnn = Today.substring(0, 4) + fam.getMarried().substring(4);
//			System.out.println(nextAnn);
			int gapD = DateComparison.getDateGap(Today,nextAnn);
			if(gapD>=0 && gapD <= 30){answer.add(fam.getId());}
//			System.out.println(Today);
		}
		return answer;
	}
	
	public static ArrayList<String> listUpcomingBirthdays(){
		ArrayList<String> answer = new ArrayList<String>();
		for(Individual indi : ParseGEDCOMFile.indiList.values()){
			String Today = DateComparison.getTodayDate();
			String nextBir = Today.substring(0, 4) + indi.getBirthday().substring(4);
			int gapD = DateComparison.getDateGap(Today,nextBir);
			if(gapD>=0 && gapD <= 30){answer.add(indi.getId());}
		}
		return answer;
	}
	
	public static ArrayList<String> livingMarried() {
		ArrayList<String> marriedLiving = new ArrayList<String>();
		for (Family _family : ParseGEDCOMFile.famList.values()) {
			if(_family.getDivorced().equalsIgnoreCase("N/A")) {
				String spouse1 = _family.getHusbandId();
				String spouse2 = _family.getWifeId();
				if(ReportingTool.getIndiById(spouse1).isAlive()) {
					marriedLiving.add(spouse1);
				}
				if(ReportingTool.getIndiById(spouse2).isAlive()) {
					marriedLiving.add(spouse2);
				}
			}
		}
		return marriedLiving;
	}
	
	public static boolean firstCousinsNotMarried(Individual _individual) {
		String spouse = "";
		HashSet<String> cousins = new HashSet<String>();
		if(!_individual.getFamcId().equalsIgnoreCase("N/A")) {
			Family childOfFamily = ReportingTool.getFamById(_individual.getFamcId());
			Individual mom = ReportingTool.getIndiById(childOfFamily.getWifeId());
			Individual dad = ReportingTool.getIndiById(childOfFamily.getHusbandId());
			HashSet<String> auntsAndUncles = getSiblings(mom);
			auntsAndUncles.addAll(getSiblings(dad));
			cousins = getAllCousins(auntsAndUncles);
		}
		spouse = getSpouse(_individual);
		
		if(cousins.contains(spouse)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean auntsAndUncles(Individual _individual) {
		String spouse = "";
		HashSet<String> auntsAndUncles = new HashSet<String>();
		if(!_individual.getFamcId().equalsIgnoreCase("N/A")) {
			Family childOfFamily = ReportingTool.getFamById(_individual.getFamcId());
			Individual mom = ReportingTool.getIndiById(childOfFamily.getWifeId());
			Individual dad = ReportingTool.getIndiById(childOfFamily.getHusbandId());
			auntsAndUncles = getSiblings(mom);
			auntsAndUncles.addAll(getSiblings(dad));
		}
		spouse = getSpouse(_individual);
		
		if(auntsAndUncles.contains(spouse)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String getSpouse(Individual _individual) {
		String spouse = "";
		if(!_individual.getFamsId().equalsIgnoreCase("N/A")) {
			if(_individual.getGender().equalsIgnoreCase("F")) {
				spouse = ReportingTool.getFamById(_individual.getFamsId()).getHusbandId();
			} else if(_individual.getGender().equalsIgnoreCase("M")) {
				spouse = ReportingTool.getFamById(_individual.getFamsId()).getWifeId();
			}
		}
		return spouse;
	}

	public static HashSet<String> getSiblings(Individual _individual) {
		HashSet<String> siblings = new HashSet<String>();
		if(!_individual.getFamcId().equalsIgnoreCase("N/A")) {
			Family childOf = ReportingTool.getFamById(_individual.getFamcId());
			siblings.addAll(childOf .getChildren());
			siblings.remove(_individual.getId());
		}
		return siblings;
	}

	public static HashSet<String> getAllCousins(HashSet<String> generationOfSiblings) {
		HashSet<String> cousins = new HashSet<String>();
		for(String sibling : generationOfSiblings) {
			if(!ReportingTool.getIndiById(sibling).getFamsId().equalsIgnoreCase("N/A")) {
				Family marriedFamily = ReportingTool.getFamById(ReportingTool.getIndiById(sibling).getFamsId());
				ArrayList<String> children = marriedFamily.getChildren();
				cousins.addAll(children);
			}
		}
		return cousins;
	}

	public static boolean noBigamy(Family fam){
		HashMap<Integer,Family> famlist = ParseGEDCOMFile.famList;
		Iterator<Map.Entry<Integer, Family>> entries = famlist.entrySet().iterator();
		while(entries.hasNext()){
			Map.Entry<Integer, Family> entry = entries.next();
			Family family = entry.getValue();
			if(!family.getId().equals(fam.getId())){
				if(family.getHusbandId().equals(fam.getHusbandId()) || family.getWifeId().equals(fam.getWifeId())){
					if(family.getDivorced().equals("N/A") && fam.getDivorced().equals("N/A")){
						return false;
					}else{
						if(!family.getDivorced().equals("N/A") && !fam.getDivorced().equals("N/A")){
							if(DateComparison.beforeDate(fam.getMarried(), family.getDivorced()) 
									&& DateComparison.beforeDate(family.getMarried(), fam.getDivorced())){
								return false;
							}
						
						}else 
						if(!family.getDivorced().equals("N/A") && fam.getDivorced().equals("N/A")){
							if(DateComparison.beforeDate(fam.getMarried(), family.getDivorced())){
								return false;
							}
						}else if(!fam.getDivorced().equals("N.A") && family.getDivorced().equals("N/A")){
							if(DateComparison.beforeDate(family.getMarried(),fam.getDivorced())){
								return false;
							}
						}
					}
					}
			}
		}
		return true;
	}
	public static String partialDate(String date){
		String[] split = date.split(" ");
		if(split.length == 3){
		}else if(split.length == 2){
			date = "01 "+date;
		}else if(split.length == 1){
			date = "01 JAN "+date;
		}
		return date;
	}
	public static void printTable(Map<Integer, Individual> indiList, Map<Integer, Family> famList) {
		System.out.println("Individuals");
		System.out.println("  ID           Name           Gender    Birthday    Age   Alive     Death       Child    Spouse ");
		System.out.println("====== ===================== ======== ============ ===== ======= ============ ========= =========");
		for(int i = 0;i< 5000;i++){
			if(indiList.containsKey(i)){
			Individual indi = indiList.get(i);
			System.out.printf("  %-5s   %-18s    %-5s  %-11s  %-4s  %-6s  %-11s    %-6s    %-6s\n",
					indi.getId(), indi.getName(), indi.getGender(), indi.getBirthday(),indi.getAge(), indi.getDeath() == "N/A" ? "True" : "False", indi.getDeath() == "N/A" ? "   N/A     " : indi.getDeath(), indi.getFamcId(), indi.getFamsId());
		}
	}
	System.out.println("====== ===================== ======== ============ ===== ======= ============ ========= =========\n");

	System.out.println("Families");
	System.out.println("  ID     Married      Divorced    Husband ID     Husband Name     Wife ID      Wife Name          Children    ");
	System.out.println("====== ============ ============ ============ ================== ========= ================== ================");
	for(int i = 0;i< 1000;i++){
		if(famList.containsKey(i)){
			Family fam = famList.get(i);
			System.out.printf("  %-4s  %-11s  %-11s     %-8s  %-17s    %-6s   %-16s    %-13s\n",
					fam.getId(), fam.getMarried(), fam.getDivorced() == "N/A" ? "   N/A    " : fam.getDivorced(), fam.getHusbandId(), fam.getHusbandName(), fam.getWifeId(), fam.getWifeName(), fam.getChildren());
		}
	}
	System.out.println("====== ============ ============ ============ ================== ========= ================== ================\n");
	
	    
	for(int i = 0;i< 1000;i++){
		if(famList.containsKey(i)){
			Family fam = famList.get(i);
			if(!noBigamy(fam)){
				System.out.println("FAMILY ERROR -SI017 " + fam.getId()+" has bigamy");
			}
			
		}
	}
	
	for(int i = 0;i< 5000;i++){
		if(indiList.containsKey(i)){
			Individual indi = indiList.get(i);
			
			if(!firstCousinsNotMarried(indi)) {
				System.out.println("INDIVIDUAL ERROR - SI025: " + indi.getId() + " is married to a first cousin " + getSpouse(indi));
			}
			
			if(!auntsAndUncles(indi)) {
				System.out.println("INDIVIDUAL ERROR - SI026: " + indi.getId() + " is married to an aunt or uncle " + getSpouse(indi));
			}

		}
	}
	System.out.println("INDIVIDUAL SI036 - List Individuals Living & Married: " + livingMarried());
	System.out.println("INDIVIDUAL SI037 - List Individuals Living & Single: " + listLivingSingle());
	System.out.println("INDIVIDUAL SI039 - List Individuals Orphans: " + listOrphans());
	System.out.println("INDIVIDUAL SI038 - List multiple births: " + listMultipleBirths());
	System.out.println("INDIVIDUAL SI040 - List large age differences: " + listLargeAgeDiff());
	System.out.println("INDIVIDUAL SI044 - List upcoming birthdays : " + listUpcomingBirthdays());
	System.out.println("INDIVIDUAL SI045 - List upcoming anniversaries : " + listUpcomingAnniversaries());

    }
}
