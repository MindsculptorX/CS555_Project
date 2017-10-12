import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTests {
	@Test
	public void testPassForIsValid() {
		assertEquals(true, isValid.dateBeforeToday("30 JUL 1994"));
	}

	@Test
	public void testFailForIsValid() {
		assertEquals(false, isValid.dateBeforeToday("31 DEC 2017"));
	}

	@Test
	public void testPassForIsTagValid() {
		assertEquals(true, isValid.isTagValid(1, "SEX"));
	}

	@Test
	public void testFailForIsTagValid() {
		assertEquals(false, isValid.isTagValid(2, "WIFE"));
	}

}
