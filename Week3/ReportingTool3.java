import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportingTool3 {

    public static boolean uniqueFamiliesBySpouses(Family fam) {
    	//false means not unique
    	HashMap<Integer,Family> famList = ParseGEDCOMFile.famList;
    	for (Integer key : famList.keySet()) {
    		Family curFam = famList.get(key);
    		if (!curFam.getId().equals(fam.getId())) {
    			if (curFam.getMarried().equals(fam.getMarried()) && (curFam.getHusbandName().equals(fam.getHusbandName()) || curFam.getWifeName().equals(fam.getWifeName()))) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static boolean CorrespondingEntries(Individual indi){
    	if(!indi.getFamcId().equals("N/A")){
    	int familyId = Integer.parseInt(indi.getFamcId().substring(1));
		Family fam = ParseGEDCOMFile.famList.get(familyId);
		ArrayList<String> children = fam.getChildren();
		if(!children.contains(indi.getId())){
			return false;
		}
    	}
    	if(!indi.getFamsId().equals("N/A")){
    		int familyId = Integer.parseInt(indi.getFamsId().substring(1));
			Family fam = ParseGEDCOMFile.famList.get(familyId);
    		if(indi.getGender().equals("M")){
    			if(!(fam.getHusbandId().equals(indi.getId()) && fam.getHusbandName().equals(indi.getName()))){
    				return false;
    			}
    		}else if(indi.getGender().equals("F")){
    			if(!(fam.getWifeId().equals(indi.getId()) && fam.getWifeName().equals(indi.getName()))){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static ArrayList<String>[] childrenByAge (Family _family) {
    		ArrayList<String> childrenList = _family.getChildren();
    		List<SI034> newChildList = new ArrayList<SI034>(childrenList.size());
    		
    		if (childrenList.size() >= 2) {
    			for (String member: childrenList) {
        			Individual child = ReportingTool.getIndiById(member);
        			int childAge = child.getAge();
        			newChildList.add(new SI034(member, childAge));
        		}
    			
    			newChildList.sort(Comparator.comparing(SI034::getAge).reversed());
    			
    			List<String> sortedChildren = newChildList.stream()
    					.map(SI034::getId)
    					.collect(Collectors.toList());
    			
        		System.out.println("FAMILY-ID: " + _family.getId() + ": SI034 " + sortedChildren + " siblings are listed in descending order.");
    		}
		return null;
    }
    
  //↓here is Xi's sprint3 code
  	public static boolean UniqueFirstNameInFamily(Family fam){//No more than one child with the same name and birth date should appear in a family
  		HashSet<String> name_birth_Set = new HashSet<String>();
  		for (int i = 0; i < fam.getChildren().size(); i++) {
  			Individual child = ReportingTool.getIndiById(fam.getChildren().get(i));
  			String name_birth = child.getName() + '&' + child.getBirthday();
  			if(name_birth_Set.contains(name_birth)){return false;}
  			else{name_birth_Set.add(name_birth);}
  		}
  		return true;
  	}
  	
  	public static ArrayList<String> ListDeceased(){
  		ArrayList<String> DeadList = new ArrayList<String>();
  		for(Individual indi : ParseGEDCOMFile.indiList.values()){
  			if(!indi.getDeath().equals("N/A")){
  				DeadList.add(indi.getId());
  			}
  		}
  		return DeadList;
  	}
    
  //Leo Sprint3
  	public static boolean MarriageBeforeDeath(Family fam){
  		String MarriageDate = fam.getMarried();
  		if(MarriageDate == null) {return true;}
  		if(!ReportingTool.getIndiById(fam.getHusbandId()).isAlive()){
  			String husDeadDate = ReportingTool.getIndiById(fam.getHusbandId()).getDeath();
  			if(DateComparison.beforeDate(husDeadDate,MarriageDate)){return false;}
  		}
  		if(!ReportingTool.getIndiById(fam.getWifeId()).isAlive()){
  			String WifeDeadDate = ReportingTool.getIndiById(fam.getWifeId()).getDeath();
  			if(DateComparison.beforeDate(WifeDeadDate,MarriageDate)){return false;}
  		}
  		return true;
  		
  	}
  	
  	public static boolean CorrectGenderForRole(Family fam){
  		if(!ReportingTool.getIndiById(fam.getHusbandId()).getGender().equals("M")){return false;}
  		if(!ReportingTool.getIndiById(fam.getWifeId()).getGender().equals("F")){return false;}
  		return true;
  	}
  	
  	public static HashSet<String> UniqueId(){//return the repeat ID list
  		HashSet<String> wrongIdList = new HashSet<String>();
  		HashSet<String> IDSet = new HashSet<String>();
  		HashSet<String> indiIDSet = new HashSet<String>();
  		for(Family fam : ParseGEDCOMFile.famList.values()){
  			String ID = fam.getId();
  			if(IDSet.contains(ID)){wrongIdList.add(ID);}//Means already have this
  			else{IDSet.add(ID);}
  		}
  		for(Individual indi : ParseGEDCOMFile.indiList.values()){
  			String ID = indi.getId();
  			if(IDSet.contains(ID)){wrongIdList.add(ID);}//Means already have this
  			else{IDSet.add(ID);}
  		}
  		
  		return wrongIdList;
  	}
  	
  	public static ArrayList<String> listRecentBirths(){ //return ID list
  		ArrayList<String> recentId = new ArrayList<String>();
  		for(Individual indi : ParseGEDCOMFile.indiList.values()){
  			int gapD = DateComparison.getDateGap(indi.getBirthday(),DateComparison.getTodayDate());
  			if(gapD <= 30){recentId.add(indi.getId());}
  		}
  		return recentId;
  	}
  	
  	public static ArrayList<String> listRecentDeaths(){ //return ID list
  		ArrayList<String> recentId = new ArrayList<String>();
  		for(Individual indi : ParseGEDCOMFile.indiList.values()){
  			int gapD = DateComparison.getDateGap(indi.getDeath(),DateComparison.getTodayDate());
  			if(gapD <= 30){recentId.add(indi.getId());}
  			
  		}
  		return recentId;
  	}
  	
  	public static ArrayList<String> listRecentSurvivors(Individual indi){ //return ID list
  		ArrayList<String> recentSurvivorsId = new ArrayList<String>();
  		
  		if(ListDeceased().contains(indi.getId())) {
  			if (!indi.getFamsId().equals("N/A")) {
  				Family spouseFamily = ReportingTool.getFamById(indi.getFamsId());
  				if (indi.getGender().equals("M")) {
  					String wife = spouseFamily.getWifeId();
  					recentSurvivorsId.add(wife);
  				}else if (indi.getGender().equals("F")) {
  					String husband = spouseFamily.getHusbandId();
  					recentSurvivorsId.add(husband);
  				}
  				ArrayList<String> children = spouseFamily.getChildren();
  				recentSurvivorsId.addAll(children);
  			}
  		}
  		return recentSurvivorsId;
  	}

	public static void printTable(Map<Integer, Individual> indiList, Map<Integer, Family> famList) {
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
					fam.getId(), fam.getMarried(), fam.getDivorced() == "N/A" ? "   N/A    " : fam.getDivorced(), fam.getHusbandId(), fam.getHusbandName(), fam.getWifeId(), fam.getWifeName(), fam.getChildren());
		}
	}
	System.out.println("====== ============ ============ ============ ================== ========= ================== ================\n");
	
	    
	for(int i = 0;i< 1000;i++){
		if(famList.containsKey(i)){
			Family fam = famList.get(i);
//			if (!uniqueFamiliesBySpouses(fam)) {
//				System.out.println("Error: FAMILY: SI030 " + fam.getId() + " has error in unique families by spouses.");
//			}
			
			childrenByAge(fam);
			
			if (!MarriageBeforeDeath(fam)) {
				System.out.println("Error: FAMILY: SI011 " + fam.getId() + " has an error regarding marriage date and individual's death date.");
			}
			
			if (!CorrectGenderForRole(fam)) {
				System.out.println("Error: FAMILY: SI027 " + fam.getId() + " has an error regarding the gender roles of the spouses.");
			}
			
			if (!UniqueFirstNameInFamily(fam)) {
				System.out.println("Error: FAMILY: SI031 " + fam.getId() + " has an error regarding same name and date of birth.");
			}
			
			if(ParseGEDCOMFile.repeatId.contains(fam.getId())){
				System.out.println("Error: FAMILY: SI028 " + fam.getId() + " does not have a unique ID, it was repeated.");
			}
		}
	}
	
	for(int i = 0;i< 5000;i++){
		if(indiList.containsKey(i)){
			Individual indi = indiList.get(i);
			if(!CorrespondingEntries(indi)){
				System.out.println("ERROR: INDIVIDUAL: SI032 "+indi.getId()+" the information in the individual and family records is not consistent.");
			}
			
			if(!listRecentSurvivors(indi).isEmpty()) {
				System.out.println("Survivors of " + indi.getId() + " " + listRecentSurvivors(indi));
			}
			if(ParseGEDCOMFile.repeatId.contains(indi.getId())){
				System.out.println("Error: INDIVIDUAL: SI028 " +indi.getId() + " does not have a unique ID, it was repeated.");
			}

		}
	}
	System.out.println("Births" + listRecentBirths());
	System.out.println("INDIVIDUAL SI036 - List Recent Deaths: " + ListDeceased());
	
    }
	
}


