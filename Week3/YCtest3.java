import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Test;

public class YCtest3 {
	@Test
	public void CorrespondingEntriesPass(){
		Individual child = new Individual();
		Individual husband = new Individual();
		Individual wife = new Individual();
		husband.setId("I3");
		husband.setName("Irvin /Bean/");
		husband.setGender("M");
		husband.setFamsId("F1");
		wife.setId("I4");
		wife.setName("Fox /Bean/");
		wife.setGender("F");
		wife.setFamsId("F1");
		child.setId("I1");
		child.setFamcId("F1");
		Family fam = new Family();
		fam.setId("F1");
		ArrayList<String> children = new ArrayList();
		children.add("I1");
		children.add("I2");
		fam.setChildren(children);
		fam.setHusbandId(husband.getId());
		fam.setHusbandName(husband.getName());
		fam.setWifeId(wife.getId());
		fam.setWifeName(wife.getName());
		assertEquals(true, CorrespondingEntries(child, fam));
		assertEquals(true, CorrespondingEntries(husband, fam));
		assertEquals(true, CorrespondingEntries(wife, fam));	
	}
	
	@Test
	public void CorrespondingEntriesFail(){
		Individual child = new Individual();
		Individual husband = new Individual();
		Individual wife = new Individual();
		husband.setId("I3");
		husband.setName("Irvin /Bean/");
		husband.setGender("M");
		husband.setFamsId("F1");
		wife.setId("I4");
		wife.setName("Fox /Bean/");
		wife.setGender("F");
		wife.setFamsId("F1");
		child.setId("I1");
		child.setFamcId("F1");
		Family fam = new Family();
		fam.setId("F1");
		ArrayList<String> children = new ArrayList();
		children.add("I5");
		children.add("I2");
		fam.setChildren(children);
		fam.setHusbandId("I1");
		fam.setHusbandName(husband.getName());
		fam.setWifeId(wife.getId());
		fam.setWifeName("AAA/BBB");
		assertEquals(false, CorrespondingEntries(child, fam));
		assertEquals(false, CorrespondingEntries(husband, fam));
		assertEquals(false, CorrespondingEntries(wife, fam));	
	}
	
	public boolean CorrespondingEntries(Individual indi,Family fam){
		if(!indi.getFamcId().equals("N/A")){
			ArrayList<String> children = fam.getChildren();
			if(!children.contains(indi.getId())){
				return false;
			}
	    	}
	    	if(!indi.getFamsId().equals("N/A")){
	    		if(indi.getGender().equals("M")){
	    			if(!(fam.getHusbandId().equals(indi.getId()) && fam.getHusbandName().equals(indi.getName()))){
	    				return false;
	    			}
	    		}else if(indi.getGender().equals("F")){
	    			if(!(fam.getWifeId().equals(indi.getId()) && fam.getWifeName().equals(indi.getName()))){
	    				return false;
	    			}
	    		}
	    	}
	    	return true;
	}
}
