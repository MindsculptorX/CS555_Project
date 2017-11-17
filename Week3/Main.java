
public class Main {
	//here is the main function
	  public static void main(String[] args) {
		  //read file and print initial two maps
		  ParseGEDCOMFile.setMap();
		  ReportingTool3.printTable(ParseGEDCOMFile.indiList, ParseGEDCOMFile.famList);
		  
	  }
	  
	  
}
