import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

public class XiSprintThreeTest {
	
	
//	@Test
	public void testPrint(){ 
		String date = "01 SEP 2017";
		String fdate = Individual.transDate(date);
		System.out.println(date);
		System.out.println(fdate);
	}
	
	@Test
	public void testUniqueFirstNameInFamily() {
		ParseGEDCOMFile.setMap();
		
		Family fam = new Family();
		
		Individual child1 = new Individual();
		Individual child2 = new Individual();
		child1.setName("Irvin /Bean/");
		child2.setName("Irvin /Bean/");
		
		child1.setBirthday("11 SEP 2017");
		child2.setBirthday("11 SEP 2017");
		
		ParseGEDCOMFile.indiList.put(20001, child1);
		ParseGEDCOMFile.indiList.put(20002, child2);
		
		fam.setChildren(new ArrayList<String>(Arrays.asList("I20001")));
		assertEquals(true, ReportingTool3.UniqueFirstNameInFamily(fam));
		
		fam.setChildren(new ArrayList<String>(Arrays.asList("I20001","I20002")));
		assertEquals(false, ReportingTool3.UniqueFirstNameInFamily(fam));
	}
	
	//Leo's test
	@Test
	public void testUniqueId(){
		ParseGEDCOMFile.setMap();
		ParseGEDCOMFile.indiList.clear();
		ParseGEDCOMFile.famList.clear();
		
		Individual indi1 = new Individual();
		Individual indi2 = new Individual();
		Family fam1 = new Family();
		Family fam2 = new Family();
		
		indi1.setId("I20000");
		indi2.setId("I20001");
		fam1.setId("F20001");
		fam2.setId("I20001");
		
		HashSet<String> test = new HashSet<String>();
		ParseGEDCOMFile.indiList.put(20001, indi1);
		assertEquals(test,ReportingTool3.UniqueId());
		ParseGEDCOMFile.indiList.put(20002, indi2);
		assertEquals(test,ReportingTool3.UniqueId());
		ParseGEDCOMFile.famList.put(20001, fam1);
		assertEquals(test,ReportingTool3.UniqueId());
		ParseGEDCOMFile.famList.put(20002, fam2);
		test.add("I20001");
		assertEquals(test,ReportingTool3.UniqueId());
		
	}
	
	
	@Test
	public void testCorrectGenderForMale(){
		ParseGEDCOMFile.setMap();
		ParseGEDCOMFile.indiList.clear();
		ParseGEDCOMFile.famList.clear();
		
		Family fam1 = new Family();//F M 
		Family fam2 = new Family();//M M
		Family fam3 = new Family();//F F
		
		Individual male1 = new Individual();
		Individual male2 = new Individual();
		Individual female1 = new Individual();
		Individual female2 = new Individual();
		
		male1.setGender("M");
		male2.setGender("M");
		female1.setGender("F");
		female2.setGender("F");

		ParseGEDCOMFile.indiList.put(20001, male1);
		ParseGEDCOMFile.indiList.put(20002, male2);
		ParseGEDCOMFile.indiList.put(20003, female1);
		ParseGEDCOMFile.indiList.put(20004, female2);
		
		fam1.setHusbandId("I20001");//M
		fam1.setWifeId("I20003");//F
		fam2.setHusbandId("I20003");//
		fam2.setWifeId("I20004");
		fam3.setHusbandId("I20001");
		fam3.setWifeId("I20002");


		assertEquals(true,ReportingTool3.CorrectGenderForRole(fam1));
		assertEquals(false,ReportingTool3.CorrectGenderForRole(fam2));
		assertEquals(false,ReportingTool3.CorrectGenderForRole(fam3));

		
	}
	
	
	@Test
	public void testMarriageBeforeDeath(){
		ParseGEDCOMFile.setMap();
		ParseGEDCOMFile.indiList.clear();
		ParseGEDCOMFile.famList.clear();
		
		Family fam1 = new Family(); 
		Family fam2 = new Family();
		Family fam3 = new Family();
		
		fam1.setMarried("01 JAN 2000");
		fam2.setMarried("01 JAN 2000");
		fam3.setMarried("01 JAN 2000");
		
		Individual male1 = new Individual();
		Individual female1 = new Individual();//live
		Individual female2 = new Individual();//dead before
		Individual female3 = new Individual();//dead after
	
		female2.setDeath("01 JAN 1990");
		female3.setDeath("01 JAN 2010");
		
		ParseGEDCOMFile.indiList.put(20001, male1);
		ParseGEDCOMFile.indiList.put(20002, female1);
		ParseGEDCOMFile.indiList.put(20003, female2);
		ParseGEDCOMFile.indiList.put(20004, female3);

		fam1.setHusbandId("I20001");
		fam2.setHusbandId("I20001");
		fam3.setHusbandId("I20001");

		fam1.setWifeId("I20002");
		fam2.setWifeId("I20003");
		fam3.setWifeId("I20004");
		
		assertEquals(true,ReportingTool3.MarriageBeforeDeath(fam1));
		assertEquals(false,ReportingTool3.MarriageBeforeDeath(fam2));
		assertEquals(true,ReportingTool3.MarriageBeforeDeath(fam3));
	
		
	}
	
	@Test
	public void testList(){
		ParseGEDCOMFile.setMap();
		ParseGEDCOMFile.indiList.clear();
		ParseGEDCOMFile.famList.clear();
		
		Individual indi1 = new Individual();
		Individual indi2 = new Individual();
		Individual indi3 = new Individual();
		Individual indi4 = new Individual();
		

		indi1.setId("I30001");
		indi2.setId("I30002");
		indi3.setId("I30003");
		indi4.setId("I30004");
		
		indi1.setBirthday("01 NOV 2017");
		indi2.setBirthday("01 NOV 2016");
		indi3.setBirthday("01 JAN 1990");
		indi4.setBirthday("01 JAN 1990");
		
		indi1.setDeath("03 NOV 2003");
		indi2.setDeath("03 OCT 2017");
		indi3.setDeath("10 OCT 2017");
		indi4.setDeath("03 NOV 2017");
		
		ParseGEDCOMFile.indiList.put(30001, indi1);
		ParseGEDCOMFile.indiList.put(30002, indi2);
		ParseGEDCOMFile.indiList.put(30003, indi3);
		ParseGEDCOMFile.indiList.put(30004, indi4);

		System.out.println(ReportingTool3.listRecentBirths());
		System.out.println(ReportingTool3.listRecentDeaths());
	}
}
