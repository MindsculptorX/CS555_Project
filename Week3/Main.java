import java.util.HashMap;

public class Main {
	//here is the main function
	  public static void main(String[] args) {
		  //read file and initial two maps
		  ParseGEDCOMFile.setMap();
		  HashMap<Integer, Individual> indiList = ParseGEDCOMFile.indiList;
		  HashMap<Integer, Family> famList = ParseGEDCOMFile.famList;
		  ReportingTool.printTable(indiList, famList);
	  }
}
