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
					fam.getId(), fam.getMarried(), fam.getDivorced() == "N/A" ? "   N/A    " : fam.getDivorced(), fam.getHusId(), fam.getHusName(), fam.getWifId(), fam.getWifName(), fam.getChildren());
		}
	}
	System.out.println("====== ============ ============ ============ ================== ========= ================== ================\n");
	for(int i = 0;i< 1000;i++){
		if(famList.containsKey(i)){
			Family fam = famList.get(i);
			//BUG before::
			//We use haspMap<Integer,Indi> So don`t
			Individual husband = indiList.get(Integer.parseInt(fam.getHusId().substring(1)));
			Individual wife = indiList.get(Integer.parseInt(fam.getWifId().substring(1)));
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
