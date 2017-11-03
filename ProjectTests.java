import static org.junit.Assert.*;

import java.text.ParseException;
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
//	    System.out.println(ReportingTool.divorceBeforeDeath(fam, wife, hus));
//		assertEquals(true, ReportingTool.divorceBeforeDeath(fam, wife, hus).equals("N/AHusband"));
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
	public void testDateLegitimacy() throws ParseException {
		assertEquals(true, DateComparison.illegitimateDate("5 FEB 2017"));
		assertEquals(false, DateComparison.illegitimateDate("35 FEB 2017"));
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
	public void testPassForBirthBeforeMarriage() {
		Individual person1 = new Individual();
		person1.setBirthday("14 FEB 1986");
		Family family = new Family("E1", "7 JUL 2016", "P1", "L1");
		assertEquals(true, DateComparison.beforeDate(person1.getBirthday(), family.getMarried()));
	}

	@Test
	public void testFailForBirthBeforeMarriage() {
		Individual person1 = new Individual();
		person1.setBirthday("14 FEB 1986");
		Family family = new Family("E1", "7 JUL 1980", "P1", "L1");
		assertEquals(false, DateComparison.beforeDate(person1.getBirthday(), family.getMarried()));
	}

	@Test
	public void testPassForBirthBeforeDeath() {
		Individual person = new Individual();
		person.setBirthday("14 FEB 1986");
		person.setDeath("15 AUG 2016");
		assertEquals(true, DateComparison.beforeDate(person.getBirthday(), person.getDeath()));
	}

	@Test
	public void testFailForBirthBeforeDeath() {
		Individual person = new Individual();
		person.setBirthday("14 FEB 1976");
		person.setDeath("15 AUG 1955");
		assertEquals(false, DateComparison.beforeDate(person.getBirthday(), person.getDeath()));
	}

	@Test
	public void testMarriageBeforeDivorced3(){
		Family fam = new Family();
		fam.setDivorced("5 JUN 2015");
		fam.setMarried("2 FEB 2004");
		assertEquals(true,fam.marriageBeforeDivorce());
	}
	@Test
	public void testMarriageBeforeDivorced4(){
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
		long beforeDay = DateComparison.differentValueDate(indi.getBirthday(), fam.getMarried());
		assertEquals(true, beforeDay>270);
	}
	@Test
	public void testBirthBeforeMarriageOfParents2(){
		Family fam = new Family();
		fam.setMarried("5 JUN 2015");
		Individual indi = new Individual();
		indi.setBirthday("5 JAN 2016");
		long beforeDay = DateComparison.differentValueDate(indi.getBirthday(), fam.getMarried());
		assertEquals(false, beforeDay>270);
	}

	@Test
	public void testPassNumberofSiblings(){
	  Family family = new Family();
	  ArrayList<String> children = new ArrayList<String>();
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
	  ArrayList<String> children = new ArrayList<String>();
	  String[] kids = {"Kid1", "Kid2", "Kid3", "Kid4", "Kid5", "Kid6", "Kid7", "Kid8", "Kid9", "Kid10", "Kid11", "Kid12", "Kid13", "Kid14", "Kid15", "Kid16"};
	  children.addAll(Arrays.asList(kids));
	  family.setChildren(children);
	  assertEquals(false, ReportingTool.numberOfSiblings(family));
	}

	@Test
	public void testPassNotMarriedToDescendants() {
		Family family = new Family("F12", "12 DEC 1988", "H1", "W1");
		ArrayList<String> children = new ArrayList<String>();
		String[] kids = {"C1", "C2", "C3"};
		children.addAll(Arrays.asList(kids));
		family.setChildren(children);
		assertEquals(false,  family.getChildren().contains(family.getHusbandId()));
		assertEquals(false,  family.getChildren().contains(family.getWifeId()));
	}

	@Test
	public void testFailNotMarriedToDescendants() {
		Family family = new Family("F12", "12 DEC 1988", "H1", "C2");
		ArrayList<String> children = new ArrayList<String>();
		String[] kids = {"C1", "C2", "C3"};
		children.addAll(Arrays.asList(kids));
		family.setChildren(children);
		assertEquals(false,  family.getChildren().contains(family.getHusbandId()));
		assertEquals(true,  family.getChildren().contains(family.getWifeId()));
	}

	@Test
	public void testPassNotMarriedToSiblings() {
		Family family = new Family("F12", "12 DEC 1988", "R1", "N2");
		ArrayList<String> children = new ArrayList<String>();
		String[] kids = {"N1", "N2", "N3", "N4"};
		children.addAll(Arrays.asList(kids));
		family.setChildren(children);
		assertEquals(false, family.getChildren().contains(family.getHusbandId()));
	}

	@Test
	public void testFailNotMarriedToSiblings() {
		Family family = new Family("F12", "12 DEC 1988", "N4", "N2");
		ArrayList<String> children = new ArrayList<String>();
		String[] kids = {"N1", "N2", "N3", "N4"};
		children.addAll(Arrays.asList(kids));
		family.setChildren(children);
		assertEquals(true, family.getChildren().contains(family.getHusbandId()));
	}
	public static boolean birthBeforeDeathOfparents(Individual child,Individual father,Individual mother){
		if(father.getDeath().equals("N/A") &&  mother.getDeath().equals("N/A")){
			return true;
		}
		else
		if(!father.getDeath().equals("N/A") && DateComparison.beforeDate(child.getBirthday(), father.getDeath())){
			return true;
		}else if(!mother.getDeath().equals("N/A") && DateComparison.beforeDate(child.getBirthday(), mother.getDeath())){
			return true;
		}else{
			return false;
		}
	}
	@Test
	public void testPassBirthBeforeDeathOfParents(){
		Individual child = new Individual();
		child.setBirthday("10 SEP 2007");
		Individual father  = new Individual();
		Individual mother = new Individual();
		assertEquals(true, birthBeforeDeathOfparents(child,father,mother));
	}
	@Test
	public void testFailBirthBeforeDeathOfParents(){
		Individual child = new Individual();
		child.setBirthday("10 SEP 2007");
		Individual father  = new Individual();
		father.setDeath("22 OCT 2004");
		Individual mother = new Individual();
		assertEquals(false, birthBeforeDeathOfparents(child,father,mother));
	}
	public static boolean multiPleBirthLessThan5(Individual[] children){
    	if(children.length<=5){
    		return true;
    	}else{
    		for(int i=0;i<children.length;i++){
        		int sameBirthDay = 0;
    			for(int j=0;j<children.length;j++){
    				if(children[i].getBirthday().equals(children[j].getBirthday())){
    					sameBirthDay+=1;
    					if(sameBirthDay>5){
    						return false;
    					}
    				}
    			}
    		}
    	}
    	return true;
	}
	@Test
	public void testPassMultipleBirthsLessThan5(){
		Individual kid1 = new Individual();
		kid1.setBirthday("10 SEP 2007");
		Individual kid2 = new Individual();
		kid2.setBirthday("10 SEP 2005");
		Individual kid3 = new Individual();
		kid3.setBirthday("10 SEP 2007");
		Individual kid4 = new Individual();
		kid4.setBirthday("10 SEP 2007");
		Individual kid5 = new Individual();
		kid5.setBirthday("10 SEP 2007");
		Individual kid6 = new Individual();
		kid6.setBirthday("10 SEP 2007");
		Individual[] children = {kid1,kid2,kid3,kid4,kid5,kid6};
		assertEquals(true, multiPleBirthLessThan5(children));	
	}
	
	@Test
	public void testFailMultipleBirthsLessThan5(){
		Individual kid1 = new Individual();
		kid1.setBirthday("10 SEP 2007");
		Individual kid2 = new Individual();
		kid2.setBirthday("10 SEP 2007");
		Individual kid3 = new Individual();
		kid3.setBirthday("10 SEP 2007");
		Individual kid4 = new Individual();
		kid4.setBirthday("10 SEP 2007");
		Individual kid5 = new Individual();
		kid5.setBirthday("10 SEP 2007");
		Individual kid6 = new Individual();
		kid6.setBirthday("10 SEP 2007");
		Individual[] children = {kid1,kid2,kid3,kid4,kid5,kid6};
		assertEquals(false, multiPleBirthLessThan5(children));	
	}

	@Test
	public void testPassSiblingsSpacing() {
		ParseGEDCOMFile.setMap();
		Individual sibling1 = new Individual();
		Individual sibling2 = new Individual();
		sibling1.setId("N60001");
		sibling1.setBirthday("12 DEC 2000");
		sibling1.setFamcId("F50001");
		
		sibling2.setId("N60002");
		sibling2.setBirthday("20 DEC 2000");
		sibling2.setFamcId("F50001");
		Family family = new Family("F50001", "12 DEC 1988", "N60003", "N60004");
		ArrayList<String> children = new ArrayList<String>();
		String[] kids = {"N60001", "N60002"};
		children.addAll(Arrays.asList(kids));
		family.setChildren(children);

		ParseGEDCOMFile.famList.put(50001, family);
		ParseGEDCOMFile.indiList.put(60001, sibling1);
		ParseGEDCOMFile.indiList.put(60002, sibling2);
		
		assertEquals(true, ReportingTool.SiblingsSpacing(sibling2));

	}
	@Test
	public void testFailSiblingsSpacing() {
		ParseGEDCOMFile.setMap();
		Individual sibling1 = new Individual();
		Individual sibling2 = new Individual();
		sibling1.setId("N60001");
		sibling1.setBirthday("12 DEC 2000");
		sibling1.setFamcId("F50001");
		
		sibling2.setId("N60002");
		sibling2.setBirthday("12 DEC 2000");
		sibling2.setFamcId("F50001");
		Family family = new Family("F50001", "12 DEC 1988", "N60003", "N60004");
		ArrayList<String> children = new ArrayList<String>();
		String[] kids = {"N60001", "N60002"};
		children.addAll(Arrays.asList(kids));
		family.setChildren(children);

		ParseGEDCOMFile.famList.put(50001, family);
		ParseGEDCOMFile.indiList.put(60001, sibling1);
		ParseGEDCOMFile.indiList.put(60002, sibling2);
		
		assertEquals(false, ReportingTool.SiblingsSpacing(sibling2));

	}
	
	@Test
	public void testPassMarriageBeforeFourteen() {
		ParseGEDCOMFile.setMap();
		Individual indi = new Individual();
		indi.setId("N60001");
		indi.setFamsId("F50001");
		indi.setBirthday("12 DEC 1995");
		Family fam = new Family();
		fam.setMarried("12 DEC 2000");
		fam.setHusbandId("N60001");
		ParseGEDCOMFile.famList.put(50001, fam);
		
		assertEquals(true, ReportingTool.MarriageBeforeFourteen(indi));
	}
	
	@Test
	public void testFailMarriageBeforeFourteen() {
		ParseGEDCOMFile.setMap();
		Individual indi = new Individual();
		indi.setId("N60001");
		indi.setFamsId("F50001");
		indi.setBirthday("12 DEC 1975");
		Family fam = new Family();
		fam.setMarried("12 DEC 2000");
		fam.setHusbandId("N60001");
		ParseGEDCOMFile.famList.put(50001, fam);
		
		assertEquals(false, ReportingTool.MarriageBeforeFourteen(indi));
	}
}
