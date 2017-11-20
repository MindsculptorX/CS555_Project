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
	
	
	
	
}
