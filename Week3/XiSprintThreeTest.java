import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

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
}
