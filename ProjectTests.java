import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTests {
	@Test
	public void testMarriageBeforeDvorced3(){
		Family fam = new Family();
		fam.setDivorced("5 JUN 2015");
		fam.setMarried("2 FEB 2004");
		assertEquals(true,fam.marriageBeforeDivorce());
	}
	@Test
	public void testMarriageBeforeDvorced4(){
		Family fam = new Family();
		fam.setDivorced("5 JUN 2015");
		fam.setMarried("2 FEB 2019");
		assertEquals(false,fam.marriageBeforeDivorce());
	}
	@Test 
	public void testBirthBeforeMarriageOfParents1(){
		Family fam = new Family();
		fam.setMarried("5 JUN 2015");
		Individual indi = new Individual();
		indi.setBirthday("5 JUN 2017");
		assertEquals(true, ParseGEDCOMFile.BirthBeforeMarriageOfParents(indi,fam));
	}
	@Test 
	public void testBirthBeforeMarriageOfParents2(){
		Family fam = new Family();
		fam.setMarried("5 JUN 2015");
		Individual indi = new Individual();
		indi.setBirthday("5 JAN 2016");
		assertEquals(false, ParseGEDCOMFile.BirthBeforeMarriageOfParents(indi,fam));
	}
}