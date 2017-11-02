

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportingTool {
	 //we put every function here
    //every user story
    public static String divorceBeforeDeath(Family fam){
		//return N/A for no error; N/A... for ... error
		String ans = "N/A";
		Individual husband = getIndiById(fam.getHusbandId());
		Individual wife = getIndiById(fam.getWifeId());
		if(fam.getDivorced().equals("N/A")){
			return ans;//It means make sense
		}
		if(!husband.isAlive()){
			if(DateComparison.beforeDate(fam.getDivorced(),husband.getDeath())){
				ans +="Husband";
			}
		}
		if(!wife.isAlive() && DateComparison.beforeDate(fam.getDivorced(),wife.getDeath())){
			ans += "Wife";
		}
		return ans;
	}

    //↓here is Xi's sprint2 code
	public static Individual getIndiById(String ID) {
		ID = ID.substring(1);
		return ParseGEDCOMFile.indiList.get(Integer.parseInt(ID));
	}

	public static boolean MaleLastNames(Family fam) {// If indi is a MALE and don't share the name with the fami, return false.
		for (int i = 0; i < fam.getChildren().size(); i++) {
			Individual child = getIndiById(fam.getChildren().get(i));
			if (child.getGender().equals("M")) {
				String fatherLastName = fam.getHusbandName().split(" ")[1];
				String sonLastName = child.getName().split(" ")[1];
				if(!fatherLastName.equals(sonLastName)){
					return false;
				}
			}
		}
		return true;
	}

	public static boolean ParentsNotTooOld(Family fam) {
		int fatherBirth = Integer.parseInt(getIndiById(fam.getHusbandId()).getBirthday().substring(0, 4));
		int motherBirth = Integer.parseInt(getIndiById(fam.getWifeId()).getBirthday().substring(0, 4));
		for (int i = 0; i < fam.getChildren().size(); i++) {
			int chlidBirth = Integer.parseInt(getIndiById(fam.getChildren().get(i)).getBirthday().substring(0, 4));
			if ((chlidBirth > motherBirth + 60) || (chlidBirth > fatherBirth + 80)) {
				return false;
			}
		}
		return true;
	}

    
	public static boolean BirthBeforeMarriageOfParents(Individual indi){
		if(!indi.getFamcId().equals("N/A")){
			int familyId = Integer.parseInt(indi.getFamcId().substring(1));
			Family fam = ParseGEDCOMFile.famList.get(familyId);
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
		}else{
			return true;
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
	
	public static boolean MarriageBeforeFourteen(Individual _individual) {
		if (_individual.getFamsId().equals("N/A")) {
			return false;
		} else {
			int spouseFamily = Integer.parseInt(_individual.getFamsId().substring(1));
			long AgeOfMarriage = DateComparison.differentValueDate(ParseGEDCOMFile.famList.get(spouseFamily).getMarried(), _individual.getBirthday()) / 365;
			return AgeOfMarriage < 14 ? true : false;
		}
	}
	
	public static boolean SiblingsSpacing(Individual _individual) {
		//true for Birth dates of siblings less than 8 months apart and more than 2 days apart 
		if (_individual.getFamcId().equals("N/A")) {
			return false;
		} else {
			int childOfFamily = Integer.parseInt(_individual.getFamcId().substring(1));
			ArrayList<String> siblings = ParseGEDCOMFile.famList.get(childOfFamily).getChildren();
			for (String sibling : siblings) {
				Individual sib = ParseGEDCOMFile.indiList.get(Integer.parseInt(sibling.substring(1)));
				if (sib == null || sib == _individual) {
					return false;
				} 
				long differentDateOfSibs = DateComparison.differentValueDate(sib.getBirthday(), _individual.getBirthday());
				return (differentDateOfSibs < 240 && differentDateOfSibs > 2) ? true : false;
			}
		}
		return false;
	}

	public static boolean MarriageToDescendants(Individual _individual) {
		if(_individual.getFamsId().equalsIgnoreCase("N/A") || _individual.getFamcId().equalsIgnoreCase("N/A")) {
			return false;
		} else {
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
	}

	public static boolean MarriageToSiblings(Individual _individual) {
		if(_individual.getFamcId().equalsIgnoreCase("N/A") || _individual.getFamsId().equalsIgnoreCase("N/A")) {
			return false;
		} else {
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
	}

	public static boolean birthBeforeMarriage(Individual _individual) {
		if(_individual.getFamsId().equalsIgnoreCase("N/A")) {
			return false;
		}
		int spouseFamily = Integer.parseInt(_individual.getFamsId().substring(1));
		String dateMarried = ParseGEDCOMFile.famList.get(spouseFamily).getMarried();
		if(DateComparison.beforeDate(_individual.getBirthday(), dateMarried)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean birthBeforeDeath(Individual _individual) {
		if(_individual.getDeath().equalsIgnoreCase("N/A")) {
			return false;
		} else {
			if(DateComparison.beforeDate(_individual.getBirthday(), _individual.getDeath())) {
				return true;
			} else {
				return false;
			}
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
			if(father.getDeath().equals("N/A") &&  mother.getDeath().equals("N/A")){
				return true;
			}
			else if(!father.getDeath().equals("N/A") && DateComparison.beforeDate(child.getBirthday(), father.getDeath())){
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
    		HashMap<Integer,Individual> indiList = ParseGEDCOMFile.indiList;
    		for(int i=0;i<family.getChildren().size();i++){
    			int childrenId = Integer.parseInt(family.getChildren().get(i).substring(1));
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
			if (!divorceBeforeDeath(fam).equals("N/A")) {
				if (divorceBeforeDeath(fam).equals("Husband")) {
					System.out.println("ERROR: INDIVIDUAL: " + fam.getHusbandId() + " " + fam.getHusbandName() + " divorce before death.");
				} else if (divorceBeforeDeath(fam).equals("Wife")) {
					System.out.println("ERROR: INDIVIDUAL: " + fam.getWifeId() + " " + fam.getWifeName() + " divorce before death.");
				} else {
					System.out.println("ERROR: INDIVIDUAL: " + fam.getHusbandId() + " " + fam.getHusbandName() + " and " + fam.getWifeId() + " " + fam.getWifeName() + " divorce before death.");
				}
			}
			if (!MaleLastNames(fam)) {
				System.out.println("ERROR: INDIVIDUAL: " + fam.getHusbandId() + " " + fam.getHusbandName() + " has the different name.");
			}
		}
	}
	
	for(int i = 0;i< 5000;i++){
		if(indiList.containsKey(i)){
			Individual indi = indiList.get(i);
			if (!BirthBeforeDeathOfParents(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "birth after death of parents.");
			}
			if (!lessThenOneFiveZero(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "is more than 150 years old.");
			}
			if (MarriageBeforeFourteen(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "got married before 14 years old.");
			}
			if (SiblingsSpacing(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "has the problem of sibling spacing.");
			}
			if (MarriageToDescendants(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "got married to descendants.");
			}
			if (MarriageToSiblings(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "got married to siblings.");
			}
			if (birthBeforeMarriage(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "birth before marriage.");
			}
			if (!birthBeforeDeath(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "birth after death.");
			}
			if (!BirthBeforeDeathOfParents(indi)) {
				System.out.println("ERROR: INDIVIDUAL: " + indi.getId() + " " + indi.getName() + "birth after death of parents.");
			}
		}
	}
}

}
