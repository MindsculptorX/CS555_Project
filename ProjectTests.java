import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTests {
	
	@Test
	public void testPassForXi() {
		Individual hus = new Individual();
	    hus.setDeath("10 AUG 2014");
	    Individual wife = new Individual();
	    Family fam = new Family();
	    fam.setDivorced("5 JUN 2014");
	    System.out.println(ParseGEDCOMFile.divorceBeforeDeath(fam, wife, hus));
		assertEquals(true,ParseGEDCOMFile.divorceBeforeDeath(fam, wife, hus).equals("N/AHusband"));
	}
	@Test
	public void testPassForIsValidTag() {
		assertEquals(true, isValid.isTagValid(1, "SEX"));
	}

	@Test
	public void testFailForIsValidTag() {
		assertEquals(false, isValid.isTagValid(2, "WIFE"));
	}

	@Test
	public void testPassForIsValidDate() {
		assertEquals(true, DateComparison.beforeToday("30 JUL 1994"));
	}

	@Test
	public void testFailForIsValidDate() {
		assertEquals(false, DateComparison.beforeToday("31 DEC 2017"));
	}

	@Test
	public void testPassForDateComparison() {
		assertEquals(true, DateComparison.beforeDate("25 MAY 2001", "2016-2-14"));
	}

	@Test
	public void testFailForDateComparison() {
		assertEquals(false, DateComparison.beforeDate("26 MAR 2001", "1997-1-1"));
	}
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