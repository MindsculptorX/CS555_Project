import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

public class XiSprintThreeTest {

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
		assertEquals(true, ReportingTool.UniqueFirstNameInFamily(fam));
		
		fam.setChildren(new ArrayList<String>(Arrays.asList("I20001","I20002")));
		assertEquals(false, ReportingTool.UniqueFirstNameInFamily(fam));
	}

	
	
	@Test
	public void testListDeceased(){
		ParseGEDCOMFile.setMap();
		ParseGEDCOMFile.indiList.clear();
		
		Individual indi1 = new Individual();
		Individual indi2 = new Individual();
		Individual indi3 = new Individual();
		Individual indi4 = new Individual();
		
		indi2.setDeath("01 SEP 2000");
		indi4.setDeath("01 SEP 2000");

		ParseGEDCOMFile.indiList.put(20001, indi1);
		ParseGEDCOMFile.indiList.put(20002, indi2);
		ParseGEDCOMFile.indiList.put(20003, indi3);
		ParseGEDCOMFile.indiList.put(20004, indi4);

		assertEquals(Arrays.asList(indi2,indi4),ReportingTool.ListDeceased());
		
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
		assertEquals(test,ReportingTool.UniqueId());
		ParseGEDCOMFile.indiList.put(20002, indi2);
		assertEquals(test,ReportingTool.UniqueId());
		ParseGEDCOMFile.famList.put(20001, fam1);
		assertEquals(test,ReportingTool.UniqueId());
		ParseGEDCOMFile.famList.put(20002, fam2);
		test.add("I20001");
		assertEquals(test,ReportingTool.UniqueId());
		
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


		assertEquals(true,ReportingTool.CorrectGenderForMale(fam1));
		assertEquals(false,ReportingTool.CorrectGenderForMale(fam2));
		assertEquals(false,ReportingTool.CorrectGenderForMale(fam3));

		
	}
	
}
