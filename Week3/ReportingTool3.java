import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
    
    public static ArrayList<String>[] childrenByAge (Family _family) {
    		ArrayList<String> childrenList = _family.getChildren();
    		List<SI034> newChildList = new ArrayList<SI034>(childrenList.size());
    		
    		if (childrenList.size() >= 2) {
    			for (String member: childrenList) {
        			Individual child = ParseGEDCOMFile.indiList.get(Integer.parseInt(member.substring(1)));
        			int childAge = child.getAge();
        			newChildList.add(new SI034(member, childAge));
        		}
    			
    			newChildList.sort(Comparator.comparing(SI034::getAge).reversed());
    			
    			List<String> sortedChildren = newChildList.stream()
    					.map(SI034::getId)
    					.collect(Collectors.toList());
    			
        		System.out.println("FAMILY-ID: " + _family.getId() + ": SI034 " + sortedChildren + " siblings are listed in descending order.");
        		System.out.println();
    		}
		return null;
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
			if (!uniqueFamiliesBySpouses(fam)) {
				System.out.println("Error: FAMILY: SI030 " + fam.getId() + " has error in unique families by spouses.");
			}
			
			childrenByAge(fam);
		}
	}
	
	for(int i = 0;i< 5000;i++){
		if(indiList.containsKey(i)){
			Individual indi = indiList.get(i);

		}
	}
    }
	
}


