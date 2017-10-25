import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class ProjectTests {
	
	@Test
	public void testPassForXi() {
		Individual hus = new Individual();
	    hus.setDeath("10 AUG 2014");
	    Individual wife = new Individual();
	    Family fam = new Family();
	    fam.setDivorced("5 JUN 2014");
	    System.out.println(ReportingTool.divorceBeforeDeath(fam, wife, hus));
		assertEquals(true, ReportingTool.divorceBeforeDeath(fam, wife, hus).equals("N/AHusband"));
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
		assertEquals(true, ReportingTool.BirthBeforeMarriageOfParents(indi,fam));
	}
	@Test 
	public void testBirthBeforeMarriageOfParents2(){
		Family fam = new Family();
		fam.setMarried("5 JUN 2015");
		Individual indi = new Individual();
		indi.setBirthday("5 JAN 2016");
		assertEquals(false, ReportingTool.BirthBeforeMarriageOfParents(indi,fam));
	}
	
	@Test
	public void testPassNumberofSiblings(){
	  Family family = new Family();
	  ArrayList<String> children = family.getChildren();
	  children.add("Kid1");
	  children.add("Kid2");
	  children.add("Kid3");
	  children.add("Kid4");
	  family.setChildren(children);
	  assertEquals(true, ReportingTool.numberOfSiblings(family));
	}

	@Test
	public void testFailNumberofSiblings(){
	  Family family = new Family();
	  ArrayList<String> children = family.getChildren();
	  String[] kids = {"Kid1", "Kid2", "Kid3", "Kid4", "Kid5", "Kid6", "Kid7", "Kid8", "Kid9", "Kid10", "Kid11", "Kid12", "Kid13", "Kid14", "Kid15", "Kid16"};
	  children.addAll(Arrays.asList(kids));
	  family.setChildren(children);
	  assertEquals(false, ReportingTool.numberOfSiblings(family));
	}

}