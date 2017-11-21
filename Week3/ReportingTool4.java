import java.util.ArrayList;
import java.util.HashMap;

public class ReportingTool4 {
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
			System.out.println(nextAnn);
			int gapD = DateComparison.getDateGap(Today,nextAnn);
			if(gapD>=0 && gapD <= 30){answer.add(fam.getId());}
			System.out.println(Today);
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
			
		}
	}
	
	for(int i = 0;i< 5000;i++){
		if(indiList.containsKey(i)){
			Individual indi = indiList.get(i);
			

		}
	}
	System.out.println("INDIVIDUAL SI036 - List Individuals Living & Married: " + livingMarried());
    }
}
