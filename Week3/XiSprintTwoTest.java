
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class XiSprintTwoTest {
	
	@Test
	public void testMaleLastNames() {
		ParseGEDCOMFile.setMap();
		
		Family fam1 = new Family();
		Family fam2 = new Family();
		
		fam1.setHusbandName("Daddy /Bean/");
		fam2.setHusbandName("Daddy /Bean/");
		
		Individual child1 = new Individual();
		Individual child2 = new Individual();
		child1.setName("Irvin /Bean/");
		child2.setName("Irvin /Other/");
		child1.setGender("M");
		child2.setGender("M");

		ParseGEDCOMFile.indiList.put(20001, child1);
		ParseGEDCOMFile.indiList.put(20002, child2);
		
		fam1.setChildren(new ArrayList<String>(Arrays.asList("I20001")));
		fam2.setChildren(new ArrayList<String>(Arrays.asList("I20002")));

//		fam1.getChildren().add("I20001");//This is wrong
//		fam2.getChildren().add("I20002");

		assertEquals(true, ReportingTool.MaleLastNames(fam1));
		assertEquals(false, ReportingTool.MaleLastNames(fam2));

	}

	@Test
	public void testParentsNotTooOld() {
		ParseGEDCOMFile.setMap();
		Individual father1 = new Individual();
		Individual father2 = new Individual();
		Individual mother1 = new Individual();
		Individual mother2 = new Individual();
		Individual child = new Individual();

		father1.setBirthday("01 Jan 1960");
		father2.setBirthday("01 Jan 1900");
		mother1.setBirthday("01 Jan 1960");
		mother2.setBirthday("01 Jan 1900");
		child.setBirthday("01 Jan 1990");

		ParseGEDCOMFile.indiList.put(20003, father1);
		ParseGEDCOMFile.indiList.put(20004, father2);
		ParseGEDCOMFile.indiList.put(20005, mother1);
		ParseGEDCOMFile.indiList.put(20006, mother2);
		ParseGEDCOMFile.indiList.put(20007, child);

		Family fam1 = new Family();// F M //F is old one f is young one
		Family fam2 = new Family();// f m
		Family fam3 = new Family();// F m
		Family fam4 = new Family();// f M

		fam1.getChildren().add("I20007");
		fam2.getChildren().add("I20007");
		fam3.getChildren().add("I20007");
		fam4.getChildren().add("I20007");

		fam1.setHusbandId("I20004");
		fam2.setHusbandId("I20003");
		fam3.setHusbandId("I20004");
		fam4.setHusbandId("I20003");

		fam1.setWifeId("I20006");
		fam2.setWifeId("I20005");
		fam3.setWifeId("I20005");
		fam4.setWifeId("I20006");

		assertEquals(false, ReportingTool.ParentsNotTooOld(fam1));
		assertEquals(true, ReportingTool.ParentsNotTooOld(fam2));
		assertEquals(false, ReportingTool.ParentsNotTooOld(fam3));
		assertEquals(false, ReportingTool.ParentsNotTooOld(fam4));
	}
	
	
	@Test
	public void testLeo_lessThen150() {
		Individual indi1 = new Individual();
		Individual indi2 = new Individual();
		Individual indi3 = new Individual();
		Individual indi4 = new Individual();

		indi1.setAge(100);
		indi2.setAge(200);
		indi3.setAge(150);
		indi4.setAge(0);
		
		assertEquals(true, ReportingTool.LEO_lessThen150(indi1));
		assertEquals(false, ReportingTool.LEO_lessThen150(indi2));
		assertEquals(false, ReportingTool.LEO_lessThen150(indi3));
		assertEquals(true, ReportingTool.LEO_lessThen150(indi4));
	}
	
	
	
	@Test
	public void testLeo_birthBeforeDeath() {
		ParseGEDCOMFile.setMap();
		Individual indi1 = new Individual();
		Individual indi2 = new Individual();
		Individual indi3 = new Individual();
		Individual indi4 = new Individual();
		
		indi1.setBirthday("01 JAN 1960");
		indi2.setBirthday("01 JAN 1970");
		indi3.setBirthday("01 JAN 1990");
		indi4.setBirthday("01 JAN 1990");
		
		indi2.setDeath("01 JAN 1970");
		indi3.setDeath("01 JAN 2000");
		indi4.setDeath("01 JAN 1900");
		
		ParseGEDCOMFile.indiList.put(30001, indi1);
		ParseGEDCOMFile.indiList.put(30002, indi2);
		ParseGEDCOMFile.indiList.put(30003, indi3);
		ParseGEDCOMFile.indiList.put(30004, indi4);
		
		assertEquals(true, ReportingTool.Leo_birthBeforeDeath(indi1));
		assertEquals(true, ReportingTool.Leo_birthBeforeDeath(indi2));
		assertEquals(true, ReportingTool.Leo_birthBeforeDeath(indi3));
		assertEquals(false, ReportingTool.Leo_birthBeforeDeath(indi4));
	}
	
}
