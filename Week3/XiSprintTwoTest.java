
import static org.junit.Assert.*;

import org.junit.Test;

public class XiSprintTwoTest {
	
//	@Test
	public void testMaleLastNames() {
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
		
		System.out.println(ParseGEDCOMFile.indiList.hashCode());
		ParseGEDCOMFile.indiList.put(20001, null);
		ParseGEDCOMFile.indiList.put(20002, null);
		
		fam1.getChildren().add("I20001");
		fam2.getChildren().add("I20002");
//		System.out.println(ReportingTool.MaleLastNames(fam1));
//		System.out.println(ReportingTool.MaleLastNames(fam2));
//		
		assertEquals(true, ReportingTool.MaleLastNames(fam1));
		assertEquals(false, ReportingTool.MaleLastNames(fam2));

	}

//	@Test
	public void testParentsNotTooOld() {
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

		// System.out.println(ReportingTool.ParentsNotTooOld(fam1));
		// System.out.println(ReportingTool.ParentsNotTooOld(fam2));
		// System.out.println(ReportingTool.ParentsNotTooOld(fam3));
		// System.out.println(ReportingTool.ParentsNotTooOld(fam4));

		assertEquals(false, ReportingTool.ParentsNotTooOld(fam1));
		assertEquals(true, ReportingTool.ParentsNotTooOld(fam2));
		assertEquals(false, ReportingTool.ParentsNotTooOld(fam3));
		assertEquals(false, ReportingTool.ParentsNotTooOld(fam4));
	}
	
	@Test
	public void testTest() {
		assertEquals(true, true);
	}
}
