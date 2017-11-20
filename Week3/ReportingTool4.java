import java.util.ArrayList;
import java.util.HashMap;

public class ReportingTool4 {
	public static ArrayList<String> listMultipleBirths(){
		ArrayList<String> answer = new ArrayList<String>();
		HashMap<String,ArrayList<String>> mp = new HashMap<String,ArrayList<String>>();
		for(Family fam : ParseGEDCOMFile.famList.values()){
			//clear
			mp.clear();
			//Insert children
			for(String childId : fam.getChildren()){
				mp.get(ReportingTool.getIndiById(childId).getBirthday()).add(childId);
			}
			//Find multi 
			for(ArrayList<String> Ids : mp.values()){
				if(Ids.size()>1){
					answer.addAll(Ids);
				}
			}
			
			
		}
		return answer;
	}
}
