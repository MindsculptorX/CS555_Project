import static org.junit.Assert.*;

import org.junit.Test;

public class Sprint3Tests {
//here is zhiqi xiong's tests for Sprint 3

//This is the test for SI030
	@Test
	public void testUniqueFamiliesBySpousesId() {
		ParseGEDCOMFile.setMap();
		
		Family fam1 = new Family("F50001", "12 DEC 1988", "N60001", "N60002");
		ParseGEDCOMFile.famList.put(50001, fam1);	
		//same marriage date
		Family fam2 = new Family("F50002", "12 DEC 1988", "N60001", "N60002");
		ParseGEDCOMFile.famList.put(50002, fam2);
		assertEquals(false, ReportingTool3.uniqueFamiliesBySpousesId(fam2));
		ParseGEDCOMFile.famList.remove(50002, fam2);
		
		Family fam3 = new Family("F50003", "12 DEC 1988", "N60001", "N60003");
		ParseGEDCOMFile.famList.put(50003, fam3);
		assertEquals(false, ReportingTool3.uniqueFamiliesBySpousesId(fam3));
		ParseGEDCOMFile.famList.remove(50003, fam3);
		
		Family fam4 = new Family("F50004", "12 DEC 1988", "N60004", "N60002");
		ParseGEDCOMFile.famList.put(50004, fam4);
		assertEquals(false, ReportingTool3.uniqueFamiliesBySpousesId(fam4));
		ParseGEDCOMFile.famList.remove(50004, fam4);
		
		Family fam5 = new Family("F50005", "12 DEC 1988", "N60004", "N60003");
		ParseGEDCOMFile.famList.put(50005, fam5);
		assertEquals(true, ReportingTool3.uniqueFamiliesBySpousesId(fam5));
		ParseGEDCOMFile.famList.remove(50005, fam5);
		//different marriage date
		Family fam6 = new Family("F50006", "12 DEC 1999", "N60001", "N60002");
		ParseGEDCOMFile.famList.put(50006, fam6);
		assertEquals(true, ReportingTool3.uniqueFamiliesBySpousesId(fam6));
		ParseGEDCOMFile.famList.remove(50006, fam6);
		
		Family fam7 = new Family("F50007", "12 DEC 1999", "N60001", "N60003");
		ParseGEDCOMFile.famList.put(50007, fam7);
		assertEquals(true, ReportingTool3.uniqueFamiliesBySpousesId(fam7));
		ParseGEDCOMFile.famList.remove(50007, fam7);
		
		Family fam8 = new Family("F50008", "12 DEC 1999", "N60004", "N60002");
		ParseGEDCOMFile.famList.put(50008, fam8);
		assertEquals(true, ReportingTool3.uniqueFamiliesBySpousesId(fam8));
		ParseGEDCOMFile.famList.remove(50008, fam8);
		
		Family fam9 = new Family("F50009", "12 DEC 1999", "N60004", "N60003");
		ParseGEDCOMFile.famList.put(50009, fam9);
		assertEquals(true, ReportingTool3.uniqueFamiliesBySpousesId(fam9));		
	}
	@Test
	public void testUniqueFamiliesBySpouses() {
		ParseGEDCOMFile.setMap();
		
		Family fam1 = new Family();
		fam1.setId("F50001");
		fam1.setMarried("12 DEC 1988");
		fam1.setHusbandName("Mike A");
		fam1.setWifeName("Lily A");
		ParseGEDCOMFile.famList.put(50001, fam1);	
		
		//same marriage date
		Family fam2 = new Family();
		fam2.setId("F50002");
		fam2.setMarried("12 DEC 1988");
		fam2.setHusbandName("Mike A");
		fam2.setWifeName("Lily A");
		ParseGEDCOMFile.famList.put(50002, fam2);
		assertEquals(false, ReportingTool.uniqueFamiliesBySpouses(fam2));
		ParseGEDCOMFile.famList.remove(50002, fam2);
		
		Family fam3 = new Family();
		fam3.setId("F50003");
		fam3.setMarried("12 DEC 1988");
		fam3.setHusbandName("Mike B");
		fam3.setWifeName("Lily A");
		ParseGEDCOMFile.famList.put(50003, fam3);
		assertEquals(false, ReportingTool.uniqueFamiliesBySpouses(fam3));
		ParseGEDCOMFile.famList.remove(50003, fam3);
		
		Family fam4 = new Family();
		fam4.setId("F50004");
		fam4.setMarried("12 DEC 1988");
		fam4.setHusbandName("Mike A");
		fam4.setWifeName("Lily B");
		ParseGEDCOMFile.famList.put(50004, fam4);
		assertEquals(false, ReportingTool.uniqueFamiliesBySpouses(fam4));
		ParseGEDCOMFile.famList.remove(50004, fam4);
		
		Family fam5 = new Family();
		fam5.setId("F50005");
		fam5.setMarried("12 DEC 1988");
		fam5.setHusbandName("Mike B");
		fam5.setWifeName("Lily B");
		ParseGEDCOMFile.famList.put(50005, fam5);
		assertEquals(true, ReportingTool.uniqueFamiliesBySpouses(fam5));
		ParseGEDCOMFile.famList.remove(50005, fam5);
		
		//different marriage date
		Family fam6 = new Family();
		fam6.setId("F50006");
		fam6.setMarried("12 DEC 1999");
		fam6.setHusbandName("Mike A");
		fam6.setWifeName("Lily A");
		ParseGEDCOMFile.famList.put(50006, fam6);
		assertEquals(true, ReportingTool.uniqueFamiliesBySpouses(fam6));
		ParseGEDCOMFile.famList.remove(50006, fam6);
		
		Family fam7 = new Family();
		fam7.setId("F50007");
		fam7.setMarried("12 DEC 1999");
		fam7.setHusbandName("Mike B");
		fam7.setWifeName("Lily A");
		ParseGEDCOMFile.famList.put(50007, fam7);
		assertEquals(true, ReportingTool.uniqueFamiliesBySpouses(fam7));
		ParseGEDCOMFile.famList.remove(50007, fam7);
		
		Family fam8 = new Family();
		fam8.setId("F50008");
		fam8.setMarried("12 DEC 1999");
		fam8.setHusbandName("Mike A");
		fam8.setWifeName("Lily B");
		ParseGEDCOMFile.famList.put(50008, fam8);
		assertEquals(true, ReportingTool.uniqueFamiliesBySpouses(fam8));
		ParseGEDCOMFile.famList.remove(50008, fam8);
		
		Family fam9 = new Family();
		fam9.setId("F50009");
		fam9.setMarried("12 DEC 1999");
		fam9.setHusbandName("Mike B");
		fam9.setWifeName("Lily B");
		ParseGEDCOMFile.famList.put(50009, fam9);
		assertEquals(true, ReportingTool.uniqueFamiliesBySpouses(fam9));	
	}
}
