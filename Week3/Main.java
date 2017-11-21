
public class Main {
	//here is the main function
	  public static void main(String[] args) {
		  //read file and print initial two maps
		  ParseGEDCOMFile.setMap();
		  ReportingTool4.printTable(ParseGEDCOMFile.indiList, ParseGEDCOMFile.famList);
		  
	  }
	  
	  
}
