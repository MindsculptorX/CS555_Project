

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportingTool {
	 //we put every function here
    //every user story
    public static String divorceBeforeDeath(Family fam,Individual Wife,Individual Husband){
		//return N/A for no error
		//return N/AHusband for husband died before divorce
		//return N/AWife    for wife    died before divorce
		//return N/AHusbandWife for both died before divorce
		String ans = "N/A";
		if(fam.getDivorced().equals("N/A")){
			return ans;//It means make sense
		}
		if(!Husband.isAlive()){
			if(DateComparison.beforeDate(fam.getDivorced(),Husband.getDeath())){
				ans +="Husband";
			}
		}
		if(!Wife.isAlive() && DateComparison.beforeDate(fam.getDivorced(),Wife.getDeath())){
			ans += "Wife";
		}
		return ans;
	}

	public static boolean BirthBeforeMarriageOfParents(Individual indi,Family fam){
		if(DateComparison.beforeDate(fam.getMarried(),indi.getBirthday())){
			long birthAfterMarried = DateComparison.differentValueDate(fam.getMarried(), indi.getBirthday());
			if(birthAfterMarried >=270){
				return true;
			}else{
			return false;
			}
		}else{
			return false;
		}
	}

	public static boolean numberOfSiblings (Family _family) {
		if(_family.getChildren().size() >= 15) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean lessThenOneFiveZero (Individual _individual) {
		return _individual.getAge() < 150 ? true : false;
	}

	public static boolean MarriageToDescendants(Individual _individual) {
		int spouseFamily = Integer.parseInt(_individual.getFamsId().substring(1));
		ArrayList<String> children = ParseGEDCOMFile.famList.get(spouseFamily).getChildren();
		String spouse = "";

		if(_individual.getGender().equalsIgnoreCase("M")) {
			spouse = ParseGEDCOMFile.famList.get(spouseFamily).getWifeId();
		} else if (_individual.getGender().equalsIgnoreCase("F")) {
			spouse = ParseGEDCOMFile.famList.get(spouseFamily).getHusbandId();
		}

		if (children.contains(spouse)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean MarriageToSiblings(Individual _individual) {
		int spouseFamily = Integer.parseInt(_individual.getFamsId().substring(1));
		int childOfFamily = Integer.parseInt(_individual.getFamcId().substring(1));
		ArrayList<String> siblings = ParseGEDCOMFile.famList.get(childOfFamily).getChildren();
		String spouse = "";

		if(_individual.getGender().equalsIgnoreCase("M")) {
			spouse = ParseGEDCOMFile.famList.get(spouseFamily).getWifeId();
		} else if (_individual.getGender().equalsIgnoreCase("F")) {
		  spouse = ParseGEDCOMFile.famList.get(spouseFamily).getHusbandId();
		}

		if (siblings.contains(spouse)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean birthBeforeMarriage(Individual _individual) {
		int spouseFamily = Integer.parseInt(_individual.getFamsId().substring(1));
		String dateMarried = ParseGEDCOMFile.famList.get(spouseFamily).getMarried();
		if(DateComparison.beforeDate(_individual.getBirthday(), dateMarried)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean birthBeforeDeath(Individual _individual) {
		if(DateComparison.beforeDate(_individual.getBirthday(), _individual.getDeath())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean BirthBeforeDeathOfParents(Individual child){
		HashMap<Integer,Individual> indiList = ParseGEDCOMFile.indiList;
		HashMap<Integer,Family> famList = ParseGEDCOMFile.famList;
		if(!child.getFamcId().equals("N/A")){
			int familyId = Integer.parseInt(child.getFamcId().substring(1));
			Family fam = famList.get(familyId);
			Individual father = indiList.get(Integer.parseInt(fam.getHusbandId().substring(1)));
			Individual mother = indiList.get(Integer.parseInt(fam.getWifeId().substring(1)));
			if(!father.getDeath().equals("N/A") && DateComparison.beforeDate(child.getBirthday(), father.getDeath())){
				return true;
			}else if(!mother.getDeath().equals("N/A") && DateComparison.beforeDate(child.getBirthday(), mother.getDeath())){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
    public static boolean MultipleBirthsLessThan5(Family family){   	
    	if(family.getChildren().size()<=5){
    		return true;
    	}else{
    		Individual[] children = new Individual[family.getChildren().size()];
    		for(int i=0;i<family.getChildren().size();i++){
    			int childrenId = Integer.parseInt(family.getChildren().get(i).substring(1));
    			HashMap<Integer,Individual> indiList = ParseGEDCOMFile.indiList;
    			Individual indi = indiList.get(childrenId);
    			children[i] = indi;
    		}
    		for(int i=0;i<children.length;i++){
        		int sameBirthDay = 0;
    			for(int j=0;j<children.length;j++){
    				if(children[i].getBirthday().equals(children[j].getBirthday())){
    					sameBirthDay+=1;
    					if(sameBirthDay>5){
    						return false;
    					}
    				}
    			}
    	}
    	return true;
    }
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
			//BUG before::
			//We use haspMap<Integer,Indi> So don`t
			Individual husband = indiList.get(Integer.parseInt(fam.getHusbandId().substring(1)));
			Individual wife = indiList.get(Integer.parseInt(fam.getWifeId().substring(1)));
			if (!fam.marriageBeforeDivorce()) {
				System.out.println("ERROR: FAMILY: " + fam.getId() + " Divorce " + fam.getDivorced() + " before married " + fam.getMarried());
			}
//			if(!divorceBeforeDeath(fam,wife,husband).equals("N/A")){
				System.out.println("Error in "+ ReportingTool.divorceBeforeDeath(fam,wife,husband));
//			}
//NEED TO DO
		}
	}
}

}
